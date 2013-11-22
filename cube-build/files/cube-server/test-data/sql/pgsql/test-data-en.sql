-- Test USERS
INSERT INTO CUB_USERS (id,username,password,passwordSlat,firstName,lastName,accountNonExpired,accountNonLocked,administrator,credentialsNonExpired,isenabled)
VALUES (nextval('CUB_USERS_SEQ'),'anderson','qdcdylHvvMM6t+MzrBMEPNay6Vc=','1234567890','Anderson','Walker',true,true,true,true,true);
INSERT INTO CUB_USERS (id,username,password,passwordSlat,firstName,lastName,accountNonExpired,accountNonLocked,administrator,credentialsNonExpired,isenabled)
VALUES (nextval('CUB_USERS_SEQ'),'thomas','qdcdylHvvMM6t+MzrBMEPNay6Vc=','1234567890','Thomas','Allen',true,true,true,true,true);
INSERT INTO CUB_USERS (id,username,password,passwordSlat,firstName,lastName,accountNonExpired,accountNonLocked,administrator,credentialsNonExpired,isenabled)
VALUES (nextval('CUB_USERS_SEQ'),'smith','qdcdylHvvMM6t+MzrBMEPNay6Vc=','1234567890','Smith','Green',true,true,true,true,true);
INSERT INTO CUB_USERS (id,username,password,passwordSlat,firstName,lastName,accountNonExpired,accountNonLocked,administrator,credentialsNonExpired,isenabled)
VALUES (nextval('CUB_USERS_SEQ'),'linda','qdcdylHvvMM6t+MzrBMEPNay6Vc=','1234567890','Linda','Adams',true,true,true,true,true);
INSERT INTO CUB_USERS (id,username,password,passwordSlat,firstName,lastName,accountNonExpired,accountNonLocked,administrator,credentialsNonExpired,isenabled)
VALUES (nextval('CUB_USERS_SEQ'),'jones','qdcdylHvvMM6t+MzrBMEPNay6Vc=','1234567890','Jones','Baker',true,true,true,true,true);
INSERT INTO CUB_USERS (id,username,password,passwordSlat,firstName,lastName,accountNonExpired,accountNonLocked,administrator,credentialsNonExpired,isenabled)
VALUES (nextval('CUB_USERS_SEQ'),'barbara','qdcdylHvvMM6t+MzrBMEPNay6Vc=','1234567890','Barbara','Nelson',true,true,true,true,true);
INSERT INTO CUB_USERS (id,username,password,passwordSlat,firstName,lastName,accountNonExpired,accountNonLocked,administrator,credentialsNonExpired,isenabled)
VALUES (nextval('CUB_USERS_SEQ'),'davis','qdcdylHvvMM6t+MzrBMEPNay6Vc=','1234567890','Davis','Campbell',true,true,true,true,true);
INSERT INTO CUB_USERS (id,username,password,passwordSlat,firstName,lastName,accountNonExpired,accountNonLocked,administrator,credentialsNonExpired,isenabled)
VALUES (nextval('CUB_USERS_SEQ'),'moore','qdcdylHvvMM6t+MzrBMEPNay6Vc=','1234567890','Moore','Parker',true,true,true,true,true);
INSERT INTO CUB_USERS (id,username,password,passwordSlat,firstName,lastName,accountNonExpired,accountNonLocked,administrator,credentialsNonExpired,isenabled)
VALUES (nextval('CUB_USERS_SEQ'),'sandra','qdcdylHvvMM6t+MzrBMEPNay6Vc=','1234567890','Sandra','Edwards',true,true,true,true,true);
INSERT INTO CUB_USERS (id,username,password,passwordSlat,firstName,lastName,accountNonExpired,accountNonLocked,administrator,credentialsNonExpired,isenabled)
VALUES (nextval('CUB_USERS_SEQ'),'jhon','qdcdylHvvMM6t+MzrBMEPNay6Vc=','1234567890','Jhon','Lennon',true,true,true,true,true);

-- Test PRODUCTS
INSERT INTO CUB_PRODUCTS (id,name,description,enabled) VALUES (nextval('CUB_PRODUCTS_SEQ'),'Test Cube','Testcube is a test case management tool designed to integrate & track enterprise-wide Test Cases.',true);
INSERT INTO CUB_PRODUCTS (id,name,description,enabled) VALUES (nextval('CUB_PRODUCTS_SEQ'),'Jataka CRM','Open source CRM tool.',true);
INSERT INTO CUB_PRODUCTS (id,name,description,enabled) VALUES (nextval('CUB_PRODUCTS_SEQ'),'Jataka Framework','Open source J2EE/Flex framework.',true);

-- Test COMPONENTS
INSERT INTO CUB_COMPONENTS (id,name,description,product_id) VALUES (nextval('CUB_COMPONENTS_SEQ'),'TestCube server','Java/J2EE Server',1);
INSERT INTO CUB_COMPONENTS (id,name,description,product_id) VALUES (nextval('CUB_COMPONENTS_SEQ'),'JatakaCRM server','Java/J2EE Server',2);
INSERT INTO CUB_COMPONENTS (id,name,description,product_id) VALUES (nextval('CUB_COMPONENTS_SEQ'),'Flex','Flex',3);
INSERT INTO CUB_COMPONENTS (id,name,description,product_id) VALUES (nextval('CUB_COMPONENTS_SEQ'),'Java','Java',3);

-- Test ENVIRONMENTS
INSERT INTO CUB_ENVIRONMENTS (id,name,description,product_id,active) VALUES (nextval('CUB_ENVIRONMENTS_SEQ'),'Linux - Redhat - el5.x86_64','Red Hat Enterprise Linux Server release 5.6',1,true);
INSERT INTO CUB_ENVIRONMENTS (id,name,description,product_id,active) VALUES (nextval('CUB_ENVIRONMENTS_SEQ'),'Linux - CentOS - el5.x86_64','CentOS release 5.8',1,true);
INSERT INTO CUB_ENVIRONMENTS (id,name,description,product_id,active) VALUES (nextval('CUB_ENVIRONMENTS_SEQ'),'Linux - Fedora - fc15.x86_64','Fedora release 15 (Lovelock)',1,true);
INSERT INTO CUB_ENVIRONMENTS (id,name,description,product_id,active) VALUES (nextval('CUB_ENVIRONMENTS_SEQ'),'Windows 7 Profetional 32-bit','Windows 7 Profetional 32-bit',1,true);
INSERT INTO CUB_ENVIRONMENTS (id,name,description,product_id,active) VALUES (nextval('CUB_ENVIRONMENTS_SEQ'),'Windows 7 Profetional 64-bit','Windows 7 Profetional 64-bit',1,true);

INSERT INTO CUB_ENVIRONMENTS (id,name,description,product_id,active) VALUES (nextval('CUB_ENVIRONMENTS_SEQ'),'Linux - Redhat - el5.x86_64','Red Hat Enterprise Linux Server release 5.6',2,true);
INSERT INTO CUB_ENVIRONMENTS (id,name,description,product_id,active) VALUES (nextval('CUB_ENVIRONMENTS_SEQ'),'Linux - CentOS - el5.x86_64','CentOS release 5.8',2,true);
INSERT INTO CUB_ENVIRONMENTS (id,name,description,product_id,active) VALUES (nextval('CUB_ENVIRONMENTS_SEQ'),'Linux - Fedora - fc15.x86_64','Fedora release 15 (Lovelock)',2,true);
INSERT INTO CUB_ENVIRONMENTS (id,name,description,product_id,active) VALUES (nextval('CUB_ENVIRONMENTS_SEQ'),'Windows 7 Profetional 32-bit','Windows 7 Profetional 32-bit',2,true);
INSERT INTO CUB_ENVIRONMENTS (id,name,description,product_id,active) VALUES (nextval('CUB_ENVIRONMENTS_SEQ'),'Windows 7 Profetional 64-bit','Windows 7 Profetional 64-bit',2,true);

INSERT INTO CUB_ENVIRONMENTS (id,name,description,product_id,active) VALUES (nextval('CUB_ENVIRONMENTS_SEQ'),'Linux - Redhat - el5.x86_64','Red Hat Enterprise Linux Server release 5.6',3,true);
INSERT INTO CUB_ENVIRONMENTS (id,name,description,product_id,active) VALUES (nextval('CUB_ENVIRONMENTS_SEQ'),'Linux - CentOS - el5.x86_64','CentOS release 5.8',3,true);
INSERT INTO CUB_ENVIRONMENTS (id,name,description,product_id,active) VALUES (nextval('CUB_ENVIRONMENTS_SEQ'),'Linux - Fedora - fc15.x86_64','Fedora release 15 (Lovelock)',3,true);
INSERT INTO CUB_ENVIRONMENTS (id,name,description,product_id,active) VALUES (nextval('CUB_ENVIRONMENTS_SEQ'),'Windows 7 Profetional 32-bit','Windows 7 Profetional 32-bit',3,true);
INSERT INTO CUB_ENVIRONMENTS (id,name,description,product_id,active) VALUES (nextval('CUB_ENVIRONMENTS_SEQ'),'Windows 7 Profetional 64-bit','Windows 7 Profetional 64-bit',3,true);


-- Test Builds
INSERT INTO CUB_BUILDS (id,name,description,product_id,active) VALUES (nextval('CUB_BUILDS_SEQ'),'1','Build Number 1',1,true);
INSERT INTO CUB_BUILDS (id,name,description,product_id,active) VALUES (nextval('CUB_BUILDS_SEQ'),'2','Build Number 2',1,true);
INSERT INTO CUB_BUILDS (id,name,description,product_id,active) VALUES (nextval('CUB_BUILDS_SEQ'),'3','Build Number 3',1,true);
INSERT INTO CUB_BUILDS (id,name,description,product_id,active) VALUES (nextval('CUB_BUILDS_SEQ'),'4','Build Number 4',1,true);
INSERT INTO CUB_BUILDS (id,name,description,product_id,active) VALUES (nextval('CUB_BUILDS_SEQ'),'5','Build Number 5',1,true);
INSERT INTO CUB_BUILDS (id,name,description,product_id,active) VALUES (nextval('CUB_BUILDS_SEQ'),'6','Build Number 6',1,true);
INSERT INTO CUB_BUILDS (id,name,description,product_id,active) VALUES (nextval('CUB_BUILDS_SEQ'),'7','Build Number 7',1,true);

INSERT INTO CUB_BUILDS (id,name,description,product_id,active) VALUES (nextval('CUB_BUILDS_SEQ'),'1','Build Number 1',2,true);
INSERT INTO CUB_BUILDS (id,name,description,product_id,active) VALUES (nextval('CUB_BUILDS_SEQ'),'2','Build Number 2',2,true);
INSERT INTO CUB_BUILDS (id,name,description,product_id,active) VALUES (nextval('CUB_BUILDS_SEQ'),'3','Build Number 3',2,true);
INSERT INTO CUB_BUILDS (id,name,description,product_id,active) VALUES (nextval('CUB_BUILDS_SEQ'),'4','Build Number 4',2,true);
INSERT INTO CUB_BUILDS (id,name,description,product_id,active) VALUES (nextval('CUB_BUILDS_SEQ'),'5','Build Number 5',2,true);
INSERT INTO CUB_BUILDS (id,name,description,product_id,active) VALUES (nextval('CUB_BUILDS_SEQ'),'6','Build Number 6',2,true);

INSERT INTO CUB_BUILDS (id,name,description,product_id,active) VALUES (nextval('CUB_BUILDS_SEQ'),'1','Build Number 1',3,true);
INSERT INTO CUB_BUILDS (id,name,description,product_id,active) VALUES (nextval('CUB_BUILDS_SEQ'),'2','Build Number 2',3,true);
INSERT INTO CUB_BUILDS (id,name,description,product_id,active) VALUES (nextval('CUB_BUILDS_SEQ'),'3','Build Number 3',3,true);
INSERT INTO CUB_BUILDS (id,name,description,product_id,active) VALUES (nextval('CUB_BUILDS_SEQ'),'4','Build Number 4',3,true);
INSERT INTO CUB_BUILDS (id,name,description,product_id,active) VALUES (nextval('CUB_BUILDS_SEQ'),'5','Build Number 5',3,true);
INSERT INTO CUB_BUILDS (id,name,description,product_id,active) VALUES (nextval('CUB_BUILDS_SEQ'),'6','Build Number 6',3,true);


-- Test Categories
INSERT INTO CUB_CATEGORIES (id,name,description,product_id) VALUES (nextval('CUB_CATEGORIES_SEQ'),'DB','Any Database issue',1);
INSERT INTO CUB_CATEGORIES (id,name,description,product_id) VALUES (nextval('CUB_CATEGORIES_SEQ'),'RPM Build','Linux RMP build',1);
INSERT INTO CUB_CATEGORIES (id,name,description,product_id) VALUES (nextval('CUB_CATEGORIES_SEQ'),'Installation','Installation & configuration scripts',1);
INSERT INTO CUB_CATEGORIES (id,name,description,product_id) VALUES (nextval('CUB_CATEGORIES_SEQ'),'Flex UI','Flex 4 UI related issues',1);
INSERT INTO CUB_CATEGORIES (id,name,description,product_id) VALUES (nextval('CUB_CATEGORIES_SEQ'),'Administrator Section','Administrator Section',1);

INSERT INTO CUB_CATEGORIES (id,name,description,product_id) VALUES (nextval('CUB_CATEGORIES_SEQ'),'DB','Any Database issue',2);
INSERT INTO CUB_CATEGORIES (id,name,description,product_id) VALUES (nextval('CUB_CATEGORIES_SEQ'),'RPM Build','Linux RMP build',2);
INSERT INTO CUB_CATEGORIES (id,name,description,product_id) VALUES (nextval('CUB_CATEGORIES_SEQ'),'Installation','Installation & configuration scripts',2);
INSERT INTO CUB_CATEGORIES (id,name,description,product_id) VALUES (nextval('CUB_CATEGORIES_SEQ'),'Flex UI','Flex 4 UI related issues',2);
INSERT INTO CUB_CATEGORIES (id,name,description,product_id) VALUES (nextval('CUB_CATEGORIES_SEQ'),'Administrator Section','Administrator Section',2);


-- Test Versions
INSERT INTO CUB_VERSIONS (id,name,description,product_id,orderWeight) VALUES (nextval('CUB_VERSIONS_SEQ'),'1.0.0','1.0.0',1,100);
INSERT INTO CUB_VERSIONS (id,name,description,product_id,orderWeight) VALUES (nextval('CUB_VERSIONS_SEQ'),'1.1.0','1.1.0',1,200);
INSERT INTO CUB_VERSIONS (id,name,description,product_id,orderWeight) VALUES (nextval('CUB_VERSIONS_SEQ'),'2.0.0','2.0.0',1,300);

INSERT INTO CUB_VERSIONS (id,name,description,product_id,orderWeight) VALUES (nextval('CUB_VERSIONS_SEQ'),'1.0.0','1.0.0',2,100);
INSERT INTO CUB_VERSIONS (id,name,description,product_id,orderWeight) VALUES (nextval('CUB_VERSIONS_SEQ'),'1.1.0','1.1.0',2,200);
INSERT INTO CUB_VERSIONS (id,name,description,product_id,orderWeight) VALUES (nextval('CUB_VERSIONS_SEQ'),'2.0.0','2.0.0',2,300);

INSERT INTO CUB_VERSIONS (id,name,description,product_id,orderWeight) VALUES (nextval('CUB_VERSIONS_SEQ'),'1.0.0','1.0.0',3,100);
INSERT INTO CUB_VERSIONS (id,name,description,product_id,orderWeight) VALUES (nextval('CUB_VERSIONS_SEQ'),'1.1.0','1.1.0',3,200);
INSERT INTO CUB_VERSIONS (id,name,description,product_id,orderWeight) VALUES (nextval('CUB_VERSIONS_SEQ'),'2.0.0','2.0.0',3,300);

-- Test Plans
begin;  
INSERT INTO CUB_TESTPLANS (id, name, description, product_id, createddate, createdby_id, plantype_id, productversion_id) VALUES (nextval('CUB_TESTPLANS_SEQ'),'TestCube Full test','TestCube Full test', 1, now(), 1, 4, 1);
CREATE TEMPORARY TABLE CUB_TESTPLANS_DOCS (
	document text
) ON COMMIT DROP;
COPY CUB_TESTPLANS_DOCS ("document") FROM '/usr/share/jatakasource/testcube/server/test-data/resources/test-plans/product-full-test.html';
UPDATE CUB_TESTPLANS SET document=(SELECT array_to_string(array_agg(document), cast (E'\n' as text)) from  CUB_TESTPLANS_DOCS) WHERE id=currval('CUB_TESTPLANS_SEQ');
commit;

begin;
INSERT INTO CUB_TESTPLANS (id, name, description, product_id, createddate, createdby_id, plantype_id, productversion_id) VALUES (nextval('CUB_TESTPLANS_SEQ'),'TestCube Sanity tests','TestCube Sanity tests', 1, now(), 1, 4, 1);
CREATE TEMPORARY TABLE CUB_TESTPLANS_DOCS (
	document text
) ON COMMIT DROP;
COPY CUB_TESTPLANS_DOCS ("document") FROM '/usr/share/jatakasource/testcube/server/test-data/resources/test-plans/product-sanity-test.html';
UPDATE CUB_TESTPLANS SET document=(SELECT array_to_string(array_agg(document), cast (E'\n' as text)) from  CUB_TESTPLANS_DOCS) WHERE id=currval('CUB_TESTPLANS_SEQ');
commit;

begin;
INSERT INTO CUB_TESTPLANS (id, name, description, product_id, createddate, createdby_id, plantype_id, productversion_id) VALUES (nextval('CUB_TESTPLANS_SEQ'),'TestCube Sprint test','TestCube Sprint test', 1, now(), 1, 4, 1);
CREATE TEMPORARY TABLE CUB_TESTPLANS_DOCS (
    document text
) ON COMMIT DROP;
COPY CUB_TESTPLANS_DOCS ("document") FROM '/usr/share/jatakasource/testcube/server/test-data/resources/test-plans/product-sprint-test.html';
UPDATE CUB_TESTPLANS SET document=(SELECT array_to_string(array_agg(document), cast (E'\n' as text)) from  CUB_TESTPLANS_DOCS) WHERE id=currval('CUB_TESTPLANS_SEQ');
commit;

begin;
INSERT INTO CUB_TESTPLANS (id, name, description, product_id, createddate, createdby_id, plantype_id, productversion_id) VALUES (nextval('CUB_TESTPLANS_SEQ'),'TestCube Integration tests','TestCube Integration tests', 1, now(), 1, 4, 1);
CREATE TEMPORARY TABLE CUB_TESTPLANS_DOCS (
    document text
) ON COMMIT DROP;
COPY CUB_TESTPLANS_DOCS ("document") FROM '/usr/share/jatakasource/testcube/server/test-data/resources/test-plans/product-integration-test.html';
UPDATE CUB_TESTPLANS SET document=(SELECT array_to_string(array_agg(document), cast (E'\n' as text)) from  CUB_TESTPLANS_DOCS) WHERE id=currval('CUB_TESTPLANS_SEQ');
commit;


begin;
INSERT INTO CUB_TESTPLANS (id, name, description, product_id, createddate, createdby_id, plantype_id, productversion_id) VALUES (nextval('CUB_TESTPLANS_SEQ'),'Jataka CRM Full test','Jataka CRM Full test', 2, now(), 1, 4, 4);
CREATE TEMPORARY TABLE CUB_TESTPLANS_DOCS (
	document text
) ON COMMIT DROP;
COPY CUB_TESTPLANS_DOCS ("document") FROM '/usr/share/jatakasource/testcube/server/test-data/resources/test-plans/product-full-test.html';
UPDATE CUB_TESTPLANS SET document=(SELECT array_to_string(array_agg(document), cast (E'\n' as text)) from  CUB_TESTPLANS_DOCS) WHERE id=currval('CUB_TESTPLANS_SEQ');
commit;

begin;
INSERT INTO CUB_TESTPLANS (id, name, description, product_id, createddate, createdby_id, plantype_id, productversion_id) VALUES (nextval('CUB_TESTPLANS_SEQ'),'Jataka CRM Sanity tests','Jataka CRM Sanity tests', 2, now(), 1, 4, 5);
CREATE TEMPORARY TABLE CUB_TESTPLANS_DOCS (
	document text
) ON COMMIT DROP;
COPY CUB_TESTPLANS_DOCS ("document") FROM '/usr/share/jatakasource/testcube/server/test-data/resources/test-plans/product-sanity-test.html';
UPDATE CUB_TESTPLANS SET document=(SELECT array_to_string(array_agg(document), cast (E'\n' as text)) from  CUB_TESTPLANS_DOCS) WHERE id=currval('CUB_TESTPLANS_SEQ');
commit;

begin;
INSERT INTO CUB_TESTPLANS (id, name, description, product_id, createddate, createdby_id, plantype_id, productversion_id) VALUES (nextval('CUB_TESTPLANS_SEQ'),'Jataka CRM Sprint test','Jataka CRM Sprint test', 2, now(), 1, 4, 5);
CREATE TEMPORARY TABLE CUB_TESTPLANS_DOCS (
    document text
) ON COMMIT DROP;
COPY CUB_TESTPLANS_DOCS ("document") FROM '/usr/share/jatakasource/testcube/server/test-data/resources/test-plans/product-sprint-test.html';
UPDATE CUB_TESTPLANS SET document=(SELECT array_to_string(array_agg(document), cast (E'\n' as text)) from  CUB_TESTPLANS_DOCS) WHERE id=currval('CUB_TESTPLANS_SEQ');
commit;

begin;
INSERT INTO CUB_TESTPLANS (id, name, description, product_id, createddate, createdby_id, plantype_id, productversion_id) VALUES (nextval('CUB_TESTPLANS_SEQ'),'Jataka CRM Integration tests','Jataka CRM Integration tests', 2, now(), 1, 4, 5);
CREATE TEMPORARY TABLE CUB_TESTPLANS_DOCS (
    document text
) ON COMMIT DROP;
COPY CUB_TESTPLANS_DOCS ("document") FROM '/usr/share/jatakasource/testcube/server/test-data/resources/test-plans/product-integration-test.html';
UPDATE CUB_TESTPLANS SET document=(SELECT array_to_string(array_agg(document), cast (E'\n' as text)) from  CUB_TESTPLANS_DOCS) WHERE id=currval('CUB_TESTPLANS_SEQ');
commit;


-- Test Cases
begin;
INSERT INTO CUB_TESTCASES (id, name, description, createddate, testplan_id, assignee_id, createdby_id, priority_id, category_id, status_id) VALUES (nextval('CUB_TESTCASES_SEQ'),'Administration Privileges','Administration Privileges', now(), 1, 1, 1, 3, 4, 2);
CREATE TEMPORARY TABLE CUB_TESTCASES_DOCS (
	document text
) ON COMMIT DROP;
COPY CUB_TESTCASES_DOCS ("document") FROM '/usr/share/jatakasource/testcube/server/test-data/resources/test-cases/administration-privileges.html';
UPDATE CUB_TESTCASES SET action=(SELECT array_to_string(array_agg(document), cast (E'\n' as text)) from  CUB_TESTCASES_DOCS) WHERE id=currval('CUB_TESTCASES_SEQ');
commit;
begin;
CREATE TEMPORARY TABLE CUB_TESTCASES_DOCS (
    document text
) ON COMMIT DROP;
COPY CUB_TESTCASES_DOCS ("document") FROM '/usr/share/jatakasource/testcube/server/test-data/resources/test-cases/administration-privileges-exp.html';
UPDATE CUB_TESTCASES SET expected=(SELECT array_to_string(array_agg(document), cast (E'\n' as text)) from  CUB_TESTCASES_DOCS) WHERE id=currval('CUB_TESTCASES_SEQ');
commit;


begin;
INSERT INTO CUB_TESTCASES (id, name, description, createddate, testplan_id, assignee_id, createdby_id, priority_id, category_id, status_id) VALUES (nextval('CUB_TESTCASES_SEQ'),'Non-Administration Privileges','Administration Privileges', now(), 1, 1, 1, 3, 4, 2);
CREATE TEMPORARY TABLE CUB_TESTCASES_DOCS (
    document text
) ON COMMIT DROP;
COPY CUB_TESTCASES_DOCS ("document") FROM '/usr/share/jatakasource/testcube/server/test-data/resources/test-cases/non-administration-privileges.html';
UPDATE CUB_TESTCASES SET action=(SELECT array_to_string(array_agg(document), cast (E'\n' as text)) from  CUB_TESTCASES_DOCS) WHERE id=currval('CUB_TESTCASES_SEQ');
commit;
begin;
CREATE TEMPORARY TABLE CUB_TESTCASES_DOCS (
    document text
) ON COMMIT DROP;
COPY CUB_TESTCASES_DOCS ("document") FROM '/usr/share/jatakasource/testcube/server/test-data/resources/test-cases/non-administration-privileges-exp.html';
UPDATE CUB_TESTCASES SET expected=(SELECT array_to_string(array_agg(document), cast (E'\n' as text)) from  CUB_TESTCASES_DOCS) WHERE id=currval('CUB_TESTCASES_SEQ');
commit;

INSERT INTO CUB_TESTCASES (id, name, description, createddate, testplan_id, assignee_id, createdby_id, priority_id, category_id, status_id) VALUES (nextval('CUB_TESTCASES_SEQ'),'CRUD - Users','CRUD Users - Administration section', now(), 1, 1, 1, 3, 5, 2);
INSERT INTO CUB_TESTCASES (id, name, description, createddate, testplan_id, assignee_id, createdby_id, priority_id, category_id, status_id) VALUES (nextval('CUB_TESTCASES_SEQ'),'CRUD - Products','CRUD Products - Administration section', now(), 1, 1, 1, 3, 5, 2);
INSERT INTO CUB_TESTCASES (id, name, description, createddate, testplan_id, assignee_id, createdby_id, priority_id, category_id, status_id) VALUES (nextval('CUB_TESTCASES_SEQ'),'CRUD - Components','CRUD Components - Administration section', now(), 1, 1, 1, 3, 5, 2);
INSERT INTO CUB_TESTCASES (id, name, description, createddate, testplan_id, assignee_id, createdby_id, priority_id, category_id, status_id) VALUES (nextval('CUB_TESTCASES_SEQ'),'CRUD - Categories','CRUD Categories - Administration section', now(), 1, 1, 1, 3, 5, 2);
INSERT INTO CUB_TESTCASES (id, name, description, createddate, testplan_id, assignee_id, createdby_id, priority_id, category_id, status_id) VALUES (nextval('CUB_TESTCASES_SEQ'),'CRUD - Environments','CRUD Environments - Administration section', now(), 1, 1, 1, 3, 5, 2);
INSERT INTO CUB_TESTCASES (id, name, description, createddate, testplan_id, assignee_id, createdby_id, priority_id, category_id, status_id) VALUES (nextval('CUB_TESTCASES_SEQ'),'CRUD - Builds','CRUD Builds - Administration section', now(), 1, 1, 1, 3, 5, 2);
INSERT INTO CUB_TESTCASES (id, name, description, createddate, testplan_id, assignee_id, createdby_id, priority_id, category_id, status_id) VALUES (nextval('CUB_TESTCASES_SEQ'),'CRUD - Versions','CRUD Versions - Administration section', now(), 1, 1, 1, 3, 5, 2);
INSERT INTO CUB_TESTCASES (id, name, description, createddate, testplan_id, assignee_id, createdby_id, priority_id, category_id, status_id) VALUES (nextval('CUB_TESTCASES_SEQ'),'CRUD - Priorities','CRUD Priorities - Administration section', now(), 1, 1, 1, 3, 5, 2);
INSERT INTO CUB_TESTCASES (id, name, description, createddate, testplan_id, assignee_id, createdby_id, priority_id, category_id, status_id) VALUES (nextval('CUB_TESTCASES_SEQ'),'CRUD - Test Case Statuses','CRUD Test Case Statuses - Administration section', now(), 1, 1, 1, 3, 5, 2);
INSERT INTO CUB_TESTCASES (id, name, description, createddate, testplan_id, assignee_id, createdby_id, priority_id, category_id, status_id) VALUES (nextval('CUB_TESTCASES_SEQ'),'CRUD - Test Run Statuses','CRUD Test Run Statuses - Administration section', now(), 1, 1, 1, 3, 5, 2);
INSERT INTO CUB_TESTCASES (id, name, description, createddate, testplan_id, assignee_id, createdby_id, priority_id, category_id, status_id) VALUES (nextval('CUB_TESTCASES_SEQ'),'CRUD - Plan Type','CRUD Plan Type - Administration section', now(), 1, 1, 1, 3, 5, 2);

-- Test Run
INSERT INTO CUB_TESTRUNS (id, name, description, testplan_id, createddate, createdby_id, assignee_id, productversion_id, productbuild_id, productenvironment_id, status_id) VALUES (nextval('CUB_TESTRUNS_SEQ'),'TestCube 1.1.0 RC1','TestCube 1.1.0 RC1', 1, now(), 1, 1, 1, 1, 1, 1);
INSERT INTO CUB_TESTRUN_RESULTS(id, testrun_id, testcase_id, status_id, updateddate, createddate) VALUES (nextval('CUB_TESTRUN_RESULTS_SEQ'), currval('CUB_TESTRUNS_SEQ'), 1, 1, now(), now());
INSERT INTO CUB_TESTRUN_RESULTS(id, testrun_id, testcase_id, status_id, updateddate, createddate) VALUES (nextval('CUB_TESTRUN_RESULTS_SEQ'), currval('CUB_TESTRUNS_SEQ'), 2, 1, now(), now());
INSERT INTO CUB_TESTRUN_RESULTS(id, testrun_id, testcase_id, status_id, updateddate, createddate) VALUES (nextval('CUB_TESTRUN_RESULTS_SEQ'), currval('CUB_TESTRUNS_SEQ'), 3, 1, now(), now());
INSERT INTO CUB_TESTRUN_RESULTS(id, testrun_id, testcase_id, status_id, updateddate, createddate) VALUES (nextval('CUB_TESTRUN_RESULTS_SEQ'), currval('CUB_TESTRUNS_SEQ'), 4, 1, now(), now());
INSERT INTO CUB_TESTRUN_RESULTS(id, testrun_id, testcase_id, status_id, updateddate, createddate) VALUES (nextval('CUB_TESTRUN_RESULTS_SEQ'), currval('CUB_TESTRUNS_SEQ'), 5, 1, now(), now());
INSERT INTO CUB_TESTRUN_RESULTS(id, testrun_id, testcase_id, status_id, updateddate, createddate) VALUES (nextval('CUB_TESTRUN_RESULTS_SEQ'), currval('CUB_TESTRUNS_SEQ'), 6, 1, now(), now());
INSERT INTO CUB_TESTRUN_RESULTS(id, testrun_id, testcase_id, status_id, updateddate, createddate) VALUES (nextval('CUB_TESTRUN_RESULTS_SEQ'), currval('CUB_TESTRUNS_SEQ'), 7, 1, now(), now());
INSERT INTO CUB_TESTRUN_RESULTS(id, testrun_id, testcase_id, status_id, updateddate, createddate) VALUES (nextval('CUB_TESTRUN_RESULTS_SEQ'), currval('CUB_TESTRUNS_SEQ'), 8, 1, now(), now());
INSERT INTO CUB_TESTRUN_RESULTS(id, testrun_id, testcase_id, status_id, updateddate, createddate) VALUES (nextval('CUB_TESTRUN_RESULTS_SEQ'), currval('CUB_TESTRUNS_SEQ'), 9, 1, now(), now());
INSERT INTO CUB_TESTRUN_RESULTS(id, testrun_id, testcase_id, status_id, updateddate, createddate) VALUES (nextval('CUB_TESTRUN_RESULTS_SEQ'), currval('CUB_TESTRUNS_SEQ'), 10, 1, now(), now());
INSERT INTO CUB_TESTRUN_RESULTS(id, testrun_id, testcase_id, status_id, updateddate, createddate) VALUES (nextval('CUB_TESTRUN_RESULTS_SEQ'), currval('CUB_TESTRUNS_SEQ'), 11, 1, now(), now());
INSERT INTO CUB_TESTRUN_RESULTS(id, testrun_id, testcase_id, status_id, updateddate, createddate) VALUES (nextval('CUB_TESTRUN_RESULTS_SEQ'), currval('CUB_TESTRUNS_SEQ'), 12, 1, now(), now());
