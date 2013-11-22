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

main() {
	is_root_user
	
	parse_db_properties "${SERVER_CONF}/db.properties" 
	verify_database_server	
	check_db_is_alive

	case "${DB_TYPE}" in
		pgsql) 	
            pgsql_load_file "${DB_NAME}" "${DB_HOST}" "${DB_NAME}" "${SQL_FILE_NAME}"
            ;;
		orcl)
			orcl_load_file "${DBA_USER}" "${DB_NAME}" \
				"${DB_INSTANCE_PASSWORD}" "${DB_HOST}" \
				"${DB_PORT}" "${DB_SID}" "${SQL_FILE_NAME}"
			;;
	esac
	
}

check_db_is_alive() {
    print_debug "Verifying database is alive ..."
    if [ "${DB_TYPE}" = "pgsql" ]; then
        pgsql_is_alive || die "Please start pgsql"
    fi
    
    if [ "${DB_TYPE}" = "orcl" ]; then
        orcl_is_alive
        if [ $? -ne 0 ];  then
            die "Please start Oracle"
        fi
    fi
}

while [ -n "$1" ]; do
        v="${1#*=}"
        case "$1" in
                --instance=*)
					export SERVER_INSTANCE="${v}"
					;;
                --fileName=*)
					export SQL_FILE_NAME="${v}"
                    ;;   
                --help|*)
                        cat <<__EOF__
Usage: $0
        --instance=instance - instance to use
        --fileName=fileName - SQL file path
__EOF__
                        exit 1
        esac
        shift
done

[ -n "${SERVER_INSTANCE}" ] || die "Please specify instance"
[ -n "${SQL_FILE_NAME}" ] || die "Please specify sql file path"

. "$(get_full_path ./)/server.vars"

main

exit 0