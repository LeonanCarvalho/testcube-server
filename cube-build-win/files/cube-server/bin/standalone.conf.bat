rem ### -*- batch file -*- ######################################################
rem #                                                                          ##
rem #  TestCube Bootstrap Script Configuration                                    ##
rem #                                                                          ##
rem #############################################################################

rem #
rem # This batch file is executed by testcube-server.bat to initialize the environment
rem # variables that testcube-server.bat uses. It is recommended to use this file to
rem # configure these variables, rather than modifying testcube-server.bat itself.
rem #

rem Uncomment the following line to disable manipulation of JAVA_OPTS (JVM parameters)
rem set PRESERVE_JAVA_OPTS=true

if not "x%JAVA_OPTS%" == "x" (
  echo "JAVA_OPTS already set in environment; overriding default settings with values: %JAVA_OPTS%"
  goto JAVA_OPTS_SET
)

rem #
rem # Specify the location of the Java home directory (it is recommended that
rem # this always be set). If set, then "%JAVA_HOME%\bin\java" will be used as
rem # the Java VM executable; otherwise, "%JAVA%" will be used (see below).
rem #
rem set "JAVA_HOME=C:\opt\jdk1.6.0_23"

rem #
rem # Specify the exact Java VM executable to use - only used if JAVA_HOME is
rem # not set. Default is "java".
rem #
rem set "JAVA=C:\opt\jdk1.6.0_23\bin\java"

rem #
rem # Specify options to pass to the Java VM. Note, there are some additional
rem # options that are always passed by run.bat.
rem #

rem # Remote debug parameters
rem set "DEBUG_OPTS=-Xdebug -Xrunjdwp:transport=dt_socket,address=1044,server=y,suspend=y"

rem # JVM memory allocation pool parameters - modify as appropriate.
set "JAVA_OPTS=%DEBUG_OPTS% -Xms64M -Xmx512M -XX:MaxPermSize=256M"

rem # Reduce the RMI GCs to once per hour for Sun JVMs.
set "JAVA_OPTS=%JAVA_OPTS% -Dsun.rmi.dgc.client.gcInterval=3600000 -Dsun.rmi.dgc.server.gcInterval=3600000 -Djava.net.preferIPv4Stack=true"

rem # Wait time for shoutdown event.
if "x%SHUTDOWN_WAIT%" == "x" (
  set "SHUTDOWN_WAIT=10"
)

rem # Set server name
if "x%SERVER_NAME%" == "x" (
  set "SERVER_NAME=testcube-server"
)

rem # Set Jetty properties
if "x%JETTY_OPTS%" == "x" (
  set JETTY_OPTS= -Djetty.home="%DIRNAME%.." -Djetty.log="%DIRNAME%..\logs"
)

rem # Set Jetty properties
if "x%JATAKA_OPTS%" == "x" (
  set JATAKA_OPTS= -Dorg.jatakasource.common.instance.basedir="%DIRNAME%.." ^
-Dorg.jatakasource.common.instance.sysconfig="%DIRNAME%..\config\sys.properties" ^
-Dorg.jatakasource.common.instance.dbconfig="%DIRNAME%..\config\db.properties" ^
-Dorg.jatakasource.testcube.environment.properties="file:\\\%DIRNAME%..\config\environment.properties" ^
-Dorg.jatakasource.testcube.log4j.properties="file:\\\%DIRNAME%..\config\log4j.xml" ^
-Dorg.jatakasource.common.jmx.url="%DIRNAME%..\tmp\work" ^
-Dehcache.disk.store.dir="%DIRNAME%..\tmp\cache" ^
-Djava.rmi.server.hostname="127.0.0.1"
)

rem # Set Jetty configiration file
if "x%JETTY_CONF%" == "x" (
  set "JETTY_CONF=%DIRNAME%..\config\jetty.xml"
)

rem # Set Jetty configiration file
rem #if "x%STARTUP_LOG%" == "x" (
rem #  set "STARTUP_LOG=%DIRNAME%..\logs\boot.log"
rem #)

:JAVA_OPTS_SET