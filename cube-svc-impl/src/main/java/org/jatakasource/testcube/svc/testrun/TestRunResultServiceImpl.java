/**
 * TestCube is an enterprise Test management tool.
 * Copyright (C) 2011 JatakaSource Ltd.
 *
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * TestCube is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with TestCube.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.jatakasource.testcube.svc.testrun;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.jatakasource.common.dao.BaseDao;
import org.jatakasource.common.model.paging.Paging;
import org.jatakasource.common.model.paging.Sorting;
import org.jatakasource.common.svc.CRUDServiceImpl;
import org.jatakasource.testcube.dao.testcase.TestCaseDao;
import org.jatakasource.testcube.dao.testrun.RunStatusDao;
import org.jatakasource.testcube.dao.testrun.TestRunDao;
import org.jatakasource.testcube.dao.testrun.TestRunResultDao;
import org.jatakasource.testcube.model.testcase.ITestCase;
import org.jatakasource.testcube.model.testcase.TestCase;
import org.jatakasource.testcube.model.testrun.ITestRunResult;
import org.jatakasource.testcube.model.testrun.RunStatus;
import org.jatakasource.testcube.model.testrun.RunStatusEnum;
import org.jatakasource.testcube.model.testrun.RunStatusPerDate;
import org.jatakasource.testcube.model.testrun.TestRun;
import org.jatakasource.testcube.model.testrun.TestRunResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestRunResultServiceImpl extends CRUDServiceImpl<ITestRunResult, Long> implements TestRunResultService {

	@Autowired
	private TestRunDao testRunDao;
	@Autowired
	private TestRunResultDao testRunResultDao;
	@Autowired
	private RunStatusDao testRunStatusDao;
	@Autowired
	private TestCaseDao testCaseDao;
	@Autowired
	private RunStatusService runStatusService;

	@Override
	public BaseDao<ITestRunResult, Long> getDao() {
		return testRunResultDao;
	}

	@Override
	public List<ITestRunResult> getAll(Paging paging, Sorting sorting, Long testRunId, Long runStatusId) {
		return getAll(paging, sorting, null, testRunId, runStatusId);
	}

	@Override
	public List<ITestRunResult> getAll(Paging paging, Sorting sorting, String keyword, Long testRunId, Long runStatusId) {
		TestRun testRun = null;
		RunStatus runStatus = null;

		if (testRunId != null)
			testRun = (TestRun) testRunDao.getById(testRunId);
		if (runStatusId != null)
			runStatus = (RunStatus) testRunStatusDao.getById(runStatusId);

		return testRunResultDao.findAll(paging, sorting, keyword, testRun != null ? testRun : null, runStatus != null ? runStatus : null);
	}

	public List<ITestRunResult> getAll(Paging paging, Sorting sorting, Long testPlanId) {
		List<ITestCase> cases = testCaseDao.findAll(paging, sorting, null, testPlanId);
		List<ITestRunResult> results = new ArrayList<ITestRunResult>();

		if (CollectionUtils.isNotEmpty(cases)) {
			for (ITestCase testCase : cases) {
				results.add(new TestRunResult((TestCase) testCase, (RunStatus) runStatusService.get(RunStatusEnum.IDLE)));
			}
		}

		return results;
	}

	@Override
	public List<RunStatusPerDate> getStatusByDay() {
		return testRunResultDao.getStatusByDay();
	}

}
