# Copyright (c) 2011, JatakaSource
# All rights reserved.
#
%define _topdir	 	${basedir}/target	 
%define buildroot   %{_topdir}/%{name}-%{version}-root
%define _sourcedir  %{_topdir}
%define __jar_repack 0

Summary:	TestCube (Test case management tool) Server
Name:		cube-server
Version:	${version}
Release:	${buildNumber}
License:	GNU GPL v3
Group:		Applications/System
Source0:	cube-build-${version}.tar.gz
Source1:	jataka-common-build-${version}.tar.gz
Packager:	Yaniv ... <yaniv@jatakasource.org>
Vendor:		JatakaSource LTD
URL:		http://www.jatakasource.org
BuildRoot:	%{buildroot}
BuildArch:	noarch
Requires:	logrotate
%if %{_vendor} == "redhat"
Requires:	java-1.6.0-openjdk >= 1.6.0
%endif
%description
Testcube is a test case management tool designed to integrate & track enterprise-wide Test Cases.  
 
%prep

# Extract source0 to cube-build-${version}
%setup -n cube-build-${version}

# Extract source1 to jataka-common-build-1.1.0 and then cd to cube-build-${version}
%setup -T -D -a 1 -n cube-build-${version}

%install
rm -fr "${RPM_BUILD_ROOT}"
DESTDIR="${RPM_BUILD_ROOT}" \
	./install/bin/redhat/install.sh

%clean
rm -rf "${RPM_BUILD_ROOT}"

%pre
%{_sbindir}/groupadd -r testcube 2> /dev/null || :
%{_sbindir}/useradd -c "Jataka TestCube" -g testcube \
    -s /sbin/nologin -r -d / testcube 2> /dev/null || :  
 
%files
%defattr(-,root,root)
/usr

%changelog
