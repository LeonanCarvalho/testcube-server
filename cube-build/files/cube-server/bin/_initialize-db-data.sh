#!/bin/bash

#################################################################
# The Following scripts must be preloaded                       #
# . "$(get_full_path ./)/common/common.vars"                           #
# . "$(get_full_path ./)/common/common.inc"                            #
# . "$(get_full_path ./)/common/server.vars"                           #
#################################################################

action="$1"
locale="$2"
[ -z "${action}" ] && die "Missing action (create|initialize|upgrade|reset)"
[ "${action}" = "initialize" -a -z "${locale}" ] && die "Missing Locale (en_US)"
[ -z "${SERVER_INSTANCE}" ] && die "Please set SERVER_INSTANCE"

. "${SERVER_BIN}/classpath.inc"

#remote debug parameters 
#export DEBUG_OPTS="-Xdebug -Xrunjdwp:transport=dt_socket,address=1044,server=y,suspend=y"

${JAVA_HOME}/bin/java ${DEBUG_OPTS} \
        -cp "${DB_MAINTENANCE_CLASSPATH}" \
        -Dlog4j.configuration="file://${SERVER_CONF}/maintenance/log4j.xml" \
        -Dehcache.disk.store.dir="/tmp" \
        -Dorg.jatakasource.testcube.log4j.properties="file://${SERVER_CONF}/maintenance/log4j.xml" \
        -Dorg.jatakasource.testcube.environment.properties="file://${SERVER_CONF}/enviorment.properties" \
        -Dorg.jatakasource.common.maintenance.db.properties="file://${SERVER_CONF}/db.properties" \
        -Dorg.jatakasource.testcube.database.hibernate.hbm2ddl.auto="${action}" \
        org.jatakasource.common.data.DBMaintenance \
        "${action}" "${locale}"