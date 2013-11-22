package org.jatakasource.testcube.dao.testplan;

import org.jatakasource.testcube.dao.SpringDaoTest;
import org.jatakasource.testcube.dao.testplan.PlanTypeDao;
import org.jatakasource.testcube.model.testplan.IPlanType;
import org.jatakasource.testcube.model.testplan.PlanType;
import org.springframework.beans.factory.annotation.Autowired;

public class PlanTypeDaoTest extends SpringDaoTest<Long, PlanTypeDao, IPlanType> {

	@Autowired
	private PlanTypeDao PlanTypeDao;

	@Override
	public PlanTypeDao getDao() {
		return PlanTypeDao;
	}

	@Override
	public IPlanType[] getValidEntities() {
		return new IPlanType[] { getValidPlanType() };
	}

	@Override
	public IPlanType[] getInvalidEntities() {
		IPlanType planType = (IPlanType) getValidEntity();

		planType.setName(null);
		planType.setDescription(null);

		return new IPlanType[] { planType };
	}

	@Override
	public void change(IPlanType entity) {
		entity.setDescription("TEST PLANTYPE UPDATE");
	}

	public IPlanType getValidPlanType() {
		PlanType planType = new PlanType();

		planType.setName("TEST PLANTYPE");
		planType.setDescription("TEST PLANTYPE");

		return planType;
	}

}
