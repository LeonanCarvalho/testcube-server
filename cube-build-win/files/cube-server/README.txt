
                          Jataka Testcube

  What is it?
  -----------

  Jataka Testcube is a Web-based test case management tool designed to integrate & track enterprise-wide Test Cases. 
  TestCube includes everything needed to manage the test process, it can save testers the hassle of installing 
  separate applications that are necessary for the testing process.

  Documentation
  -------------

  The most up-to-date documentation can be found at http://www.jatakasource.org/resources/.

  Release Notes
  -------------

  The full list of changes can be found at http://code.google.com/p/jataka-testcube/issues/list?can=7&q=label%3AMilestone-Release1.1&colspec=ID+Type+Status+Priority+Milestone+Owner+Summary&cells=tiles

  System Requirements
  -------------------

  JDK:
    1.6 or above.
  Memory:
    No minimum requirement.
  Disk:
    No minimum requirement. Approximately 100MB will be used for your local DB,
    however this will vary depending on usage.
  Operating System:
    No minimum requirement. Tested on Windows XP, Windows 7, Fedora Core and CentOS/Redhat 5.8, 6.2.

  Installing Jataka Testcube
  ----------------

  1) Run msi/rpm installer:
     Unix-based Operating Systems (Linux)
       run ./install.sh script accrding to your distro.
     Windows 2000/XP/7
       run msi installer

  2) Manually create postgres database/username "testcube_server".
     Using command line:
	  Create User:
	    "C:\Program Files\PostgreSQL\9.2\bin\psql" -U postgres -h localhost -d template1 -c "CREATE USER \"testcube_server\" CREATEDB CREATEUSER" --no-align -q -t -v ON_ERROR_STOP=1
     Create DB:
	    "C:\Program Files\PostgreSQL\9.2\bin\psql" -U postgres -h localhost -d template1 -c "CREATE DATABASE \"testcube_server\" OWNER \"testcube_server\"" --no-align -q -t -v ON_ERROR_STOP=1
	 Grant DB Option:
	    "C:\Program Files\PostgreSQL\9.2\bin\psql" -U postgres -h localhost -d template1 -c "GRANT ALL PRIVILEGES ON DATABASE \"testcube_server\" TO \"testcube_server\"" --no-align -q -t -v ON_ERROR_STOP=1
	    "C:\Program Files\PostgreSQL\9.2\bin\psql" -U postgres -h localhost -d template1 -c "GRANT ALL ON DATABASE \"testcube_server\" TO \"testcube_server\"" --no-align -q -t -v ON_ERROR_STOP=1
	
     Using pgAdmin III
	  Create User: testcube_server
	  Create DB:testcube_server
      
      
     Unix-based Operating Systems (Linux)
	 Refer to http://code.google.com/p/jataka-testcube/wiki/InstallationAndConfiguration
	 
  3) Restore databse schema.

     Using command line:
	"C:\Program Files\PostgreSQL\9.2\bin\pg_restore" -d testcube_server  -U testcube_server testcube_server.backup
     Using pgAdmin III:
	restore testcube_server.backup file using testcube_server user.
	 
	 Unix-based Operating Systems (Linux)
	 Refer to http://code.google.com/p/jataka-testcube/wiki/InstallationAndConfiguration	
	 
  4) Make sure port 8080 is not in use. if port 8080 is already in use, change default port under config/jetty.xml.
  
  5) Run bin/standalone.bat 
     
	 Unix-based Operating Systems (Linux)
	 Refer to http://code.google.com/p/jataka-testcube/wiki/InstallationAndConfiguration
  
  
  For complete documentation, see Read http://code.google.com/p/jataka-testcube/wiki/WindowInstallation

  Licensing
  ---------

  Please see the file called License.rtf

  Jataka Testcube URLS
  ----------

  Home Page:          http://www.jatakasource.org
  Downloads:          http://www.jatakasource.org/downloads/
  Release Notes:      http://code.google.com/p/jataka-testcube/issues/list?can=7&q=label%3AMilestone-Release1.1&colspec=ID+Type+Status+Priority+Milestone+Owner+Summary&cells=tiles
  Source Code:        http://jataka-commons.googlecode.com/svn/trunk/
  Issue Tracking:     http://code.google.com/p/jataka-testcube/issues/list
  Wiki:               http://code.google.com/p/jataka-testcube/w/list
