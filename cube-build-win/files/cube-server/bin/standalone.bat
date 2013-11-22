@echo off
rem -------------------------------------------------------------------------
rem TestCube Bootstrap Script for Windows
rem -------------------------------------------------------------------------

rem $Id$

@if not "%ECHO%" == ""  echo %ECHO%
@if "%OS%" == "Windows_NT" setlocal

if "%OS%" == "Windows_NT" (
  set "DIRNAME=%~dp0%"
) else (
  set DIRNAME=.\
)

rem Read an optional configuration file.
if "x%STANDALONE_CONF%" == "x" (
   set "STANDALONE_CONF=%DIRNAME%standalone.conf.bat"
)
if exist "%STANDALONE_CONF%" (
   echo Calling "%STANDALONE_CONF%"
   call "%STANDALONE_CONF%" %*
) else (
   echo Config file not found "%STANDALONE_CONF%"
)

pushd %DIRNAME%..

if "%OS%" == "Windows_NT" (
  set "PROGNAME=%~nx0%"
) else (
  set "PROGNAME=standalone.bat"
)

rem Setup TestCube specific properties
set JAVA_OPTS=-Dprogram.name=%PROGNAME% %JAVA_OPTS%

if "x%JAVA_HOME%" == "x" (
  set  JAVA=java
  echo JAVA_HOME is not set. Unexpected results may occur.
  echo Set JAVA_HOME to the directory of your local JDK to avoid this message.
) else (
  set "JAVA=%JAVA_HOME%\bin\java"
)

if not "%PRESERVE_JAVA_OPTS%" == "true" (
  rem Add -client to the JVM options, if supported (32 bit VM), and not overriden
  echo "%JAVA_OPTS%" | findstr /I \-server > nul
  if errorlevel == 1 (
    "%JAVA%" -client -version 2>&1 | findstr /I /C:"Client VM" > nul
    if not errorlevel == 1 (
      set "JAVA_OPTS=-client %JAVA_OPTS%"
    )
  )

  rem Add compressed oops, if supported (64 bit VM), and not overriden
  echo "%JAVA_OPTS%" | findstr /I "\-XX:\-UseCompressedOops \-client" > nul
  if errorlevel == 1 (
    "%JAVA%" -XX:+UseCompressedOops -version > nul 2>&1
    if not errorlevel == 1 (
      set "JAVA_OPTS=-XX:+UseCompressedOops %JAVA_OPTS%"
    )
  )

  rem Add tiered compilation, if supported (64 bit VM), and not overriden
  echo "%JAVA_OPTS%" | findstr /I "\-XX:\-TieredCompilation \-client" > nul
  if errorlevel == 1 (
    "%JAVA%" -XX:+TieredCompilation -version > nul 2>&1
    if not errorlevel == 1 (
      set "JAVA_OPTS=-XX:+TieredCompilation %JAVA_OPTS%"
    )
  )
)

rem Setup TestCube specific properties
set CLASSPATH="%DIRNAME%..\lib\*"

echo ===============================================================================
echo.
echo   TestCube Standalone Bootstrap Environment
echo.
echo   SERVER_NAME: %SERVER_NAME%
echo.
echo   JETTY_OPTS: %JETTY_OPTS%
echo.
echo   JATAKA_OPTS:%JATAKA_OPTS%
echo.
echo   JETTY_CONF   %JETTY_CONF%
echo.
echo   JAVA:        %JAVA%
echo.
echo   JAVA_OPTS:   %JAVA_OPTS%
echo.
echo   CLASSPATH:   %CLASSPATH%
echo.
echo ===============================================================================
echo.
:RESTART
"%JAVA%" -cp %CLASSPATH% %JAVA_OPTS% %JETTY_OPTS% %JATAKA_OPTS% org.jatakasource.testcube.standalone.main.Main "%JETTY_CONF%" %*

if ERRORLEVEL 10 goto RESTART

:END
if "x%NOPAUSE%" == "x" pause

:END_NO_PAUSE