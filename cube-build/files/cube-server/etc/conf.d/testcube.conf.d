# Configuration file for the TestCube Server service.

# External interface name (default lo)
EXT_INTERFACE=${EXT_INTERFACE:-"lo"}
export EXT_ADDRESS=$( /sbin/ifconfig "${EXT_INTERFACE}" | grep 'inet addr:' | cut -d: -f2 | awk '{ print $1}' )

# Remote debug parameters
#export DEBUG_OPTS="-Xdebug -Xrunjdwp:transport=dt_socket,address=1044,server=y,suspend=y"

# Default JAVA_OPTS
JAVA_OPTS="${DEBUG_OPTS}"
JAVA_OPTS+=" -Xms512m -Xmx1024m -XX:MaxPermSize=256m -Dorg.jboss.resolver.warning=true -Dsun.rmi.dgc.client.gcInterval=3600000 "
JAVA_OPTS+=" -XX:+UseConcMarkSweepGC -XX:+CMSIncrementalMode -XX:MaxGCPauseMillis=400 "
JAVA_OPTS+=" -Dsun.rmi.dgc.server.gcInterval=3600000 -Dsun.lang.ClassLoader.allowArraySyntax=true -Djava.net.preferIPv4Stack=true -server "
JAVA_OPTS+=" -Dcom.jatakasource.testcube.server.host.address=${EXT_ADDRESS} -Djava.rmi.server.hostname=${EXT_ADDRESS} "
export JAVA_OPTS

if [ -z "$SHUTDOWN_WAIT" ]; then
    SHUTDOWN_WAIT=10
fi

# Export server parameters
if [ -z "$SERVER_USER" ]; then
    SERVER_USER=testcube
fi

if [ -z "$SERVER_INSTANCE" ]; then
    SERVER_INSTANCE=@SERVER_INSTANCE@
fi

if [ -z "$SERVER_NAME" ]; then
    export SERVER_NAME=testcube-${SERVER_INSTANCE}
fi

# Cube server class path file
if [ -f /usr/share/jatakasource/testcube/server/bin/server.vars ]; then 
    . /usr/share/jatakasource/testcube/server/bin/server.vars
fi

# Default Jetty Arguments
export JETTY_OPTS=" -Djetty.home=${SERVER_STATE}/.jetty -Djetty.log=${SERVER_LOGS}/.jetty "

# Default Jetty Config File
export JETTY_CONF=${SERVER_CONF}/jetty.xml

# Server boot log
export STARTUP_LOG=${SERVER_LOGS}/boot.log

if [ -z "$TESTCUBE_PID" ]; then
    TESTCUBE_PID=${SERVER_RUN}/${SERVER_NAME}.pid
fi

if [ -z "$TESTCUBE_SCRIPT" ]; then
    TESTCUBE_SCRIPT=${SERVER_BIN}/start-server.sh
fi