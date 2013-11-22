#!/bin/bash

# StartUp script for TestCube server

while [ -n "$1" ]; do
    v="${1#*=}"
    case "$1" in
        --instance=*)
            export SERVER_INSTANCE="${v}"
            ;;
        --help|*)
        if [ "--help" != "$1" ] ; then
              unexpected_arg="unexpected argument: $1"
        fi
            cat <<__EOF__
$unexpected_arg
Usage: $0
        --instance=instance - instance to use
__EOF__
            exit 1
    esac
    shift
done

[ -n "${SERVER_INSTANCE}" ] || echo "Please specify instance"

# TestCube server config file
if [ -f /etc/sysconfig/testcube-${SERVER_INSTANCE} ]; then
    . /etc/sysconfig/testcube-${SERVER_INSTANCE}
fi

[ -n "${TESTCUBE_PID}" ] || echo "Please specify pid"

# echo "Loading class path from ${SERVER_BIN}/classpath.inc"

# Load class path config
if [ -f ${SERVER_BIN}/classpath.inc ]; then
    . ${SERVER_BIN}/classpath.inc
fi

# echo "Starting java /usr/bin/java ${JAVA_OPTS} ${JETTY_OPTS} -cp \"${STANDALONE_CLASSPATH}\" org.jatakasource.testcube.standalone.main.Main ${JETTY_CONF}"

export CLASSPATH=${STANDALONE_CLASSPATH}

exec -a ${SERVER_NAME} /usr/bin/java ${JAVA_OPTS} ${JETTY_OPTS} org.jatakasource.testcube.standalone.main.Main ${JETTY_CONF} >> "${STARTUP_LOG}" 2>&1 &
echo $! >$TESTCUBE_PID
