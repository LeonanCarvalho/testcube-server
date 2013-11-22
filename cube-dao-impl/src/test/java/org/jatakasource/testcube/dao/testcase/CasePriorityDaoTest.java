package org.jatakasource.testcube.dao.testcase;

import org.jatakasource.testcube.dao.SpringDaoTest;
import org.jatakasource.testcube.dao.testcase.CasePriorityDao;
import org.jatakasource.testcube.model.testcase.CasePriority;
import org.jatakasource.testcube.model.testcase.ICasePriority;
import org.springframework.beans.factory.annotation.Autowired;

public class CasePriorityDaoTest extends SpringDaoTest<Long, CasePriorityDao, ICasePriority> {

	@Autowired
	private CasePriorityDao casePriorityDao;

	@Override
	public CasePriorityDao getDao() {
		return casePriorityDao;
	}

	@Override
	public ICasePriority[] getValidEntities() {
		return new ICasePriority[] { getValidCasePriority() };
	}

	@Override
	public ICasePriority[] getInvalidEntities() {
		ICasePriority casePriority = (ICasePriority) getValidEntity();

		casePriority.setName(null);
		casePriority.setDescription(null);

		return new ICasePriority[] { casePriority };
	}

	@Override
	public void change(ICasePriority entity) {
		entity.setDescription("TEST CASEPRIORITY UPDATE");
	}

	public ICasePriority getValidCasePriority() {
		CasePriority casePriority = new CasePriority();

		casePriority.setName("TEST CASEPRIORITY");
		casePriority.setDescription("TEST CASEPRIORITY");
		casePriority.setOrderWeight(0);

		return casePriority;
	}

}
