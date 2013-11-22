#!/bin/bash

get_full_path() {
    # Absolute path to this script, e.g. /home/user/bin/foo.sh
    SCRIPT=$(readlink -f $0)

    if [ ! -d ${SCRIPT} ]; then
        # Absolute path this script is in, thus /home/user/bin
        SCRIPT=`dirname $SCRIPT`
    fi

    ( cd "${SCRIPT}" ; pwd )
}

. "$(get_full_path ./)/common/common.vars"
. "$(get_full_path ./)/common/common.inc"
. "$(get_full_path ./)/common/db-common.inc"
. "$(get_full_path ./)/common/pgsql/pgsql-util.inc"
. "$(get_full_path ./)/common/pgsql/pgsql-schema-util.inc"

SUBST_VARS="DB_TYPE DB_URL DB_HOST DB_PORT DB_NAME DB_INSTANCE_PASSWORD DB_SID DB_DRIVER DB_HIBERNATE_DIALECT \ 
            SERVER_INSTANCE SERVER_INSTANCE_DESC SERVER_ENCODING WAR_FILE_NAME \
            SERVER_CACHE SERVER_CONF SERVER_STATE SERVER_LOGS SERVER_SHARE SERVER_USER"

revert() {
    if [ -z "${SUCCESS}" -a -z "${INSTANCE_EXISTS}" -a "${ACTION}" = "create" ]; then
        print_debug "Reverting last instance command ..."
        _remove_instance 1
    fi
}

handle_schema() {
    print_debug "Creating Database Schema/User ..."
    case "${DB_TYPE}" in
        pgsql)
            pgsql_schema_create
            ;;
        orcl)
            orcl_schema_create
            ;;
    esac    
        
    if [ -z "${SCHEMA_ONLY}" ]; then
        print_debug "Creating Database tables ..."
        "${SERVER_BIN}"/_initialize-db-data.sh create || die "Failed on create data (/tmp/maintenance.log)"
    fi
    
    if [ -z "${NO_INIT_DATA}" ]; then
        print_debug "Populate default tables with required data ..."
        "${SERVER_BIN}"/_initialize-db-data.sh initialize ${SERVER_ENCODING} || die "Failed on initializing data (/tmp/maintenance.log)"
    fi
}

setup_instance() {
    print_debug "Creating Instance directories ..."
    create_dirs ${SERVER_USER} \
        "${SERVER_CACHE}" \
        "${SERVER_LOGS}" \
        "${SERVER_CONF}" \
        "${SERVER_CONF}/maintenance" \
        "${SERVER_STATE}" \
        "${SERVER_RUN}"
        
    setup_jetty
}

setup_jetty() {
   print_debug "Creating jetty directories ..."
   
   create_dirs ${SERVER_USER} \
        "${SERVER_LOGS}"/.jetty \
        "${SERVER_STATE}/.jetty" \
        "${SERVER_STATE}/.jetty/webapps" 
}

setup_web() {
    print_debug "Deploying WEB application to server ..."
    
    create_link "${SERVER_SHARE}/webapps/cube-web.war" "${WAR_FILE_NAME}" 
}

substitute_files() {
    print_debug "Substitute configuration files ..."
    set_db_variables

    common_substitute "${SUBST_VARS}" 1 "${SERVER_SHARE}/etc/db.properties.in" "${SERVER_CONF}/db.properties" || die "${SERVER_CONF}/db.properties"
    common_substitute "${SUBST_VARS}" 1 "${SERVER_SHARE}/etc/log4j.xml.in" "${SERVER_CONF}/log4j.xml" || die "${SERVER_CONF}/log4j.xml"
    common_substitute "${SUBST_VARS}" 1 "${SERVER_SHARE}/etc/environment.properties.in" "${SERVER_CONF}/environment.properties" || die "${SERVER_CONF}/environment.properties" 
    common_substitute "${SUBST_VARS}" 1 "${SERVER_SHARE}/etc/sys.properties.in" "${SERVER_CONF}/sys.properties" || die "${SERVER_CONF}/sys.properties"
    common_substitute "${SUBST_VARS}" 1 "${SERVER_SHARE}/etc/server.version.in" "${SERVER_CONF}/server.version" || die "${SERVER_CONF}/server.version"
    common_substitute "${SUBST_VARS}" 1 "${SERVER_SHARE}/etc/standalone/jetty.xml.in" "${SERVER_CONF}/jetty.xml" || die "${SERVER_CONF}/jetty.xml"    
}

#
# Usage:
#
# Add service configuration files
# Default implementation is according to redhat standards
# 
create_service_conf(){
    print_debug "Substitute service configuration files ..."

    common_substitute "${SUBST_VARS}" 1 "${SERVER_SHARE}/etc/logrotate.d/testcube.logrotate.in" "/etc/logrotate.d/testcube.${SERVER_INSTANCE}" || die "/etc/logrotate.d/testcube-${SERVER_INSTANCE}"
    common_substitute "${SUBST_VARS}" 1 "${SERVER_SHARE}/etc/conf.d/testcube.conf.d" "${SYS_CONFIG_DIR}/testcube-${SERVER_INSTANCE}" || die "${SYS_CONFIG_DIR}/testcube-${SERVER_INSTANCE}"
    common_substitute "${SUBST_VARS}" 1 "${SERVER_SHARE}/etc/init.d/${INITD_CONFIG_TMPL}" "${INITD_CONFIG_DIR}/testcube-${SERVER_INSTANCE}${INITD_CONFIG_EXTE}" || die "${INITD_CONFIG_DIR}/testcube-${SERVER_INSTANCE}"
    
    # Support for OS which remove /var/run/* on reboot
    if [ -d /etc/tmpfiles.d ]; then 
        common_substitute "${SUBST_VARS}" 1 "${SERVER_SHARE}/etc/tmpfiles.d/testcube.conf.in" "/etc/tmpfiles.d/testcube-${SERVER_INSTANCE}.conf" || die "/etc/tmpfiles.d/testcube-${SERVER_INSTANCE}.conf"
    fi
    
    chmod 755 "${SYS_CONFIG_DIR}/testcube-${SERVER_INSTANCE}"
    chmod 755 "${INITD_CONFIG_DIR}/testcube-${SERVER_INSTANCE}${INITD_CONFIG_EXTE}"
}

#
# Usage:
#
# Remove service configuration files
# Default implementation is according to redhat standards
# 
remove_service_conf(){
    print_debug "Removing service configuration files ..."
    
    rm -f "/etc/logrotate.d/testcube-${SERVER_INSTANCE}"
    rm -f "${SYS_CONFIG_DIR}/testcube-${SERVER_INSTANCE}"
    rm -f "${INITD_CONFIG_DIR}/testcube-${SERVER_INSTANCE}${INITD_CONFIG_EXTE}"
    rm -f "/etc/tmpfiles.d/testcube-${SERVER_INSTANCE}"
}

## Copy single file
copy_file() {
    print_debug "Copy file $1 to $2"
    local source="$1" 
    local dest="$2"
    
    cp -f "${source}" "${dest}" || die "Error copying configuration files"
    
    chown -R ${SERVER_USER}:${SERVER_USER} "${dest}" || die "chown to '${SERVER_USER}' failed '${dest}'"
    chmod -R 0775 "${dest}" || die "chmod to 664 failed '${dest}'"
}

# Copy all files - exclude .in files to dest
copy_sys_conf_files() {
    print_debug "Copy instance configuration files ..."
  
    local source="$1"
    local dest="$2"
    
    find "${source}" -maxdepth 1 -type f -not -name '*.in' | xargs -i cp -f "{}" "${dest}" || die "Error copying configuration files"
    
    chown -R ${SERVER_USER}:${SERVER_USER} "${dest}" || die "chown to '${SERVER_USER}' failed '${dest}'"
    chmod -R 0775 "${dest}" || die "chmod to 664 failed '${dest}'"
}

check_db_is_alive() {
    print_debug "Verifying database is alive ..."
    if [ "${DB_TYPE}" = "pgsql" -a -z "${NO_DB_ROLLBACK}" ]; then
        pgsql_is_alive || die "Please start pgsql"
    fi
    
    if [ "${DB_TYPE}" = "orcl" -a -z "${NO_DB_ROLLBACK}" ]; then
        orcl_is_alive
        if [ $? -ne 0 ];  then
            NO_DB_ROLLBACK=1
            die "Please start Oracle"
        fi
    fi
}
  
create_instance() {
    print_debug "Begin create instance ..."
    [ -n "${INSTANCE_EXISTS}" ] && die "Instance already exists"
     
    [ -z "${SKIP_DB}" ] && check_db_is_alive

    setup_instance
    substitute_files
    create_service_conf
    
    copy_sys_conf_files "${SERVER_SHARE}/etc" "${SERVER_CONF}"
    copy_sys_conf_files "${SERVER_SHARE}/etc/maintenance" "${SERVER_CONF}/maintenance"
    
    [ -z "${SKIP_DB}" ] && handle_schema

    # Deploy to application server
    setup_web
        
    WEB_APP_RESTART=0
}

remove_instance() {
    [ -z "${INSTANCE_EXISTS}" ] && die "Instance does not exist"
        
    _remove_instance "${DO_DESTRUCTIVE}"
    
    WEB_APP_RESTART=1
}

_remove_instance() {
    print_debug "Begin remove instance script ..."
    local do_destructive="$1"
    set_db_variables
    
    remove_war
        
    if [ "${do_destructive}" = 1 ]; then
        [ -z "${SKIP_DB}" ] && check_db_is_alive && parse_db_properties "${SERVER_CONF}/db.properties" 

        if [ "${DB_TYPE}" = "pgsql" -a -z "${NO_DB_ROLLBACK}" -a -z "${SKIP_DB}" ]; then
            print_debug "Removing postgres schema ..."
            pgsql_schema_drop
         fi 
        
        if [ "${DB_TYPE}" = "orcl" -a -z "${NO_DB_ROLLBACK}" -a -z "${SKIP_DB}" ]; then
            print_debug "Removing oracle schema ..."
            orcl_schema_drop
        fi
        
        rm -rf "${SERVER_STATE}"
        rm -rf "${SERVER_CONF}"
        rm -rf "${SERVER_LOGS}"
        rm -rf "${SERVER_CACHE}"
        
        remove_service_conf
    fi
}

remove_war(){
    print_debug "Removing WAR file from web server ..."
    
    # Extract war path from server.version
    war_file_path=$(grep "^.*instance.webserver.deploy.folder=.*" "${SERVER_CONF}/server.version") 
    war_file_path=${war_file_path##*=}
    
    # Unlink WAR from web server
    rm -f "${war_file_path}"
    
    print_debug "${war_file_path} was removed from web server."
}

instance_exists() {
    print_debug "Check if instance exists ..."
    INSTANCE_EXISTS=1
    db_type "${SERVER_CONF}"
}

isi_print_msg() {
    print_debug "Checking if restarting web server is required ..."
    [ "${WEB_APP_RESTART}" == "1" ] && echo "NOTICE: restarting the server is recommended to apply new web application setting !"
    
    if [ ${SUBST_NEW_FILES} -gt 0 ]; then
        echo "* IMPORTANT: ${SUBST_NEW_FILES} config file(s) need updating."
        echo "FILES: ${SUBST_NEW_FILES_NAMES}"
    fi
}

main() {
    print_debug "Begin main method ..."
    [ -d "${SERVER_CONF}" ] && instance_exists
    verify_database_server
    
    case "${ACTION}" in
        create)
            create_instance
            ;;
        update)
            update_instance
            ;;
        remove)
            remove_instance
            ;;  
        attach) 
            attach_instance
            ;;  
    esac 

    isi_print_msg
    SUCCESS=1
}

##########################################################
# Script body 
##########################################################

is_root_user

export SERVER_USER="${SERVER_USER:-testcube}"

# Set defaults 
DO_DESTRUCTIVE=0

while [ -n "$1" ]; do
    v="${1#*=}"
    case "$1" in
        --action=*)
            ACTION="${v}"
            case "${ACTION}" in
                create|update|remove|attach) ;;
                *) die "Illegal action"
            esac
            ;;
        --instance=*)
            export SERVER_INSTANCE="${v}"
            ;;
        --instance-desc=*)
            export SERVER_INSTANCE_DESC="${v}"
            ;;
        --destructive)
            DO_DESTRUCTIVE=1
            ;;
        --skip-db)
            SKIP_DB=1
            ;;
        --schema-only)
            SCHEMA_ONLY=1
            ;;
        --no-init-data)
            NO_INIT_DATA=1
            ;;
        --init-data-locale=*)
            SERVER_ENCODING="${v}"
            ;;
        --db-type=*)
            export DB_TYPE="${v}"
            case "${DB_TYPE}" in
                pgsql|orcl) ;;
                *) die "Unsupported db type $DB_TYPE"
            esac
            ;;
        --db-host=*)
            export DB_HOST="${v}"
            ;;
        --db-port=*)
            export DB_PORT="${v}"
            ;;
        --db-user=*)
            export OS_DB_USER="${v}"
            ;;
        --dba-pass=*)
            export DBA_PASSWORD="${v}"
            ;;
        --dba-user=*)
            export DBA_USER="${v}"
            ;;
        --db-sid=*)
            export DB_SID="${v}"
            ;;
        --db-sqlplus-sid=*)
            export SQLPLUS_SID="${v}"
            ;;
        --debug)
            DEBUG=1
            print_debug "Debug is ON..."
            ;;  
        --help|*)
        if [ "--help" != "$1" ] ; then    
              unexpected_arg="unexpected argument: $1"
        fi
            cat <<__EOF__
$unexpected_arg         
Usage: $0
        --instance=instance - instance to use
        --instance-desc     - instance description  
        --action=           - action to perform
                create        - create a new instance
                remove        - remove existing instance  
        --destructive       - Remove instance completely
        --skip-db           - ignore any database operation 
                              (if specified, --schema-only, --no-init-data and --init-data-locale are ignored)  
        --schema-only       - Create the object definitions (schema), not tables.
        --no-init-data      - Create the schema & tables definitions, not init data.
        --init-data-locale  - Init data language locale (en_US)         
        --db-type=          - Database
                pgsql          - Postgres SQL
                orcl           - Oracle g11
        --db-host=host      - DB host
        --db-port=port      - DB port
        --db-user=username  - OS DB Username
        --dba-user=password - DB Administrator User
        --dba-pass=password - DB Administrator Password
        --db-sid=sid        - ORCL sid
        --db-sqlplus-sid=sid- ORCL sid for sqlplus commands
        --debug             - prints debug statment when such are avilable in the script
__EOF__
            exit 1
    esac
    shift
done

[ -n "${SERVER_INSTANCE}" ] || die "Please specify instance"
[ -z "$(echo "${SERVER_INSTANCE}" | sed 's/[a-zA-Z0-9]//g')" ] || die "Instance contains illegal character(s)"
[ -n "${ACTION}" ] || die "Please specify action"
[ "${ACTION}" = "create" -a -n "${SERVER_INSTANCE:10}" ] && die "Instance max length is 10 characters" 
[ "${ACTION}" = "create" -a -z "${DB_TYPE}" ] && die "Please specify db type"
[ -z "${SERVER_ENCODING}" ] && SERVER_ENCODING="en_US"
[ -z "${DB_NAME}" ] && export DB_NAME="testcube_${SERVER_INSTANCE}"

# Defaults according to redhat. install/uninstall scripts will override this.
[ -z "${SYS_CONFIG_DIR}" ] && export SYS_CONFIG_DIR="/etc/sysconfig"
[ -z "${INITD_CONFIG_DIR}" ] && export INITD_CONFIG_DIR="/etc/init.d"
[ -z "${INITD_CONFIG_TMPL}" ] && export INITD_CONFIG_TMPL="testcube.init.d.redhat"

. "$(get_full_path ./)/server.vars"

# This section must be called only after server.vars is loaded
[ -z "${WAR_FILE_NAME}" ] && export WAR_FILE_NAME="${SERVER_STATE}/.jetty/webapps/testcube-${SERVER_INSTANCE}.war"

trap revert 0
umask 0022
main
exit 0