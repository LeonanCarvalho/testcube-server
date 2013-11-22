package org.jatakasource.testcube.dao.testrun;

import org.jatakasource.testcube.dao.SpringDaoTest;
import org.jatakasource.testcube.dao.testrun.RunStatusDao;
import org.jatakasource.testcube.model.testrun.IRunStatus;
import org.jatakasource.testcube.model.testrun.RunStatus;
import org.jatakasource.testcube.model.testrun.RunStatusEnum;
import org.springframework.beans.factory.annotation.Autowired;

public class RunStatusDaoTest extends SpringDaoTest<Long, RunStatusDao, IRunStatus> {

	@Autowired
	private RunStatusDao RunStatusDao;

	@Override
	public RunStatusDao getDao() {
		return RunStatusDao;
	}

	@Override
	public IRunStatus[] getValidEntities() {
		return new IRunStatus[] { getValidRunStatus() };
	}

	@Override
	public IRunStatus[] getInvalidEntities() {
		IRunStatus runStatus = (IRunStatus) getValidEntity();

		runStatus.setName(null);
		runStatus.setDescription(null);

		return new IRunStatus[] { runStatus };
	}

	@Override
	public void change(IRunStatus entity) {
		entity.setDescription("TEST RUNSTATUS UPDATE");
	}

	public static IRunStatus getValidRunStatus() {
		RunStatus runStatus = new RunStatus();

		runStatus.setName("TEST RUNSTATUS");
		runStatus.setDescription("TEST RUNSTATUS");
		runStatus.setStatus(RunStatusEnum.IDLE);

		return runStatus;
	}

}
