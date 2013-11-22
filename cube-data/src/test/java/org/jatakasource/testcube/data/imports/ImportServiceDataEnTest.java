package org.jatakasource.testcube.data.imports;

import java.util.Locale;

import org.jatakasource.testcube.data.system.parameters.DBParameterReader;
import org.jatakasource.testcube.data.system.testcase.casepriority.CasePriorityReader;
import org.jatakasource.testcube.data.system.testcase.casestatus.CaseStatusReader;
import org.jatakasource.testcube.data.system.testcase.runstatus.RunStatusReader;
import org.jatakasource.testcube.data.system.testplan.plantype.PlanTypeReader;
import org.jatakasource.testcube.data.system.users.UserReader;
import org.jatakasource.testcube.model.parameters.DBParameter;
import org.jatakasource.testcube.model.security.User;
import org.jatakasource.testcube.model.testcase.CasePriority;
import org.jatakasource.testcube.model.testcase.CaseStatus;
import org.jatakasource.testcube.model.testplan.PlanType;
import org.jatakasource.testcube.model.testrun.RunStatus;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;

public class ImportServiceDataEnTest extends ImportCsvServiceTest {

	@Test
	@Rollback
	public void testImportCsvEn() {
		importCsvFile(UserReader.class, User.class, new Locale("en", "US"));
		importCsvFile(DBParameterReader.class, DBParameter.class, new Locale("en", "US"));
		importCsvFile(CaseStatusReader.class, CaseStatus.class, new Locale("en", "US"));
		importCsvFile(CasePriorityReader.class, CasePriority.class, new Locale("en", "US"));
		importCsvFile(RunStatusReader.class, RunStatus.class, new Locale("en", "US"));
		importCsvFile(PlanTypeReader.class, PlanType.class, new Locale("en", "US"));
	}
}
