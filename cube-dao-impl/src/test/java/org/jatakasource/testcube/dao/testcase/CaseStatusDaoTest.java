package org.jatakasource.testcube.dao.testcase;

import org.jatakasource.testcube.dao.SpringDaoTest;
import org.jatakasource.testcube.dao.testcase.CaseStatusDao;
import org.jatakasource.testcube.model.testcase.CaseStatus;
import org.jatakasource.testcube.model.testcase.ICaseStatus;
import org.springframework.beans.factory.annotation.Autowired;

public class CaseStatusDaoTest extends SpringDaoTest<Long, CaseStatusDao, ICaseStatus> {

	@Autowired
	private CaseStatusDao CaseStatusDao;

	@Override
	public CaseStatusDao getDao() {
		return CaseStatusDao;
	}

	@Override
	public ICaseStatus[] getValidEntities() {
		return new ICaseStatus[] { getValidCaseStatus() };
	}

	@Override
	public ICaseStatus[] getInvalidEntities() {
		ICaseStatus caseStatus = (ICaseStatus) getValidEntity();

		caseStatus.setName(null);
		caseStatus.setDescription(null);

		return new ICaseStatus[] { caseStatus };
	}

	@Override
	public void change(ICaseStatus entity) {
		entity.setDescription("TEST CASESTATUS UPDATE");
	}

	public static CaseStatus getValidCaseStatus() {
		CaseStatus caseStatus = new CaseStatus();

		caseStatus.setName("TEST CASESTATUS");
		caseStatus.setDescription("TEST CASESTATUS");
		caseStatus.setOrderWeight(0);

		return caseStatus;
	}

}
