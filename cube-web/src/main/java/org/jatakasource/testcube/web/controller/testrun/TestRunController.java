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
package org.jatakasource.testcube.web.controller.testrun;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.jatakasource.common.model.security.UserPojo;
import org.jatakasource.common.svc.CRUDService;
import org.jatakasource.testcube.model.testrun.ITestRun;
import org.jatakasource.testcube.model.testrun.RunStatus;
import org.jatakasource.testcube.model.testrun.RunStatusEnum;
import org.jatakasource.testcube.model.testrun.RunStatusPojo;
import org.jatakasource.testcube.model.testrun.TestRun;
import org.jatakasource.testcube.model.testrun.TestRunStatisticsPojo;
import org.jatakasource.testcube.svc.security.UserService;
import org.jatakasource.testcube.svc.testrun.RunStatusService;
import org.jatakasource.testcube.svc.testrun.TestRunService;
import org.jatakasource.testcube.web.controller.ApplicationMessagesController;
import org.jatakasource.testcube.web.controller.GeneralProperties;
import org.jatakasource.testcube.web.xml.testrun.TestRunGridParameters;
import org.jatakasource.testcube.web.xml.testrun.TestRunRendered;
import org.jatakasource.testcube.web.xml.testrun.TestRunRenderedList;
import org.jatakasource.testcube.web.xml.testrun.TestRunStatisticsRendered;
import org.jatakasource.testcube.web.xml.testrun.TestRunWithStatisticsRendered;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TestRunController extends ApplicationMessagesController<ITestRun, Long> {
	private static final Logger logger = Logger.getLogger(TestRunController.class);

	@Autowired
	private TestRunService testRunService;
	@Autowired
	private RunStatusService runStatusService;
	@Autowired
	private UserService userService;

	@Override
	protected CRUDService<ITestRun, Long> getCrudService() {
		return testRunService;
	}

	@Override
	protected String getModelName() {
		return getMessages().getMessage(TestRunProperties.class.getName() + "." + TestRunProperties.MODEL_NAME.name());
	}

	@RequestMapping(value = PROTECTED_SERVICE + "/testrun/all")
	protected ModelAndView all(@RequestParam(value = GRID_PARAMETERS, required = true) String xmlGridParameters) {
		logger.trace("Request for all test-runs with parameters " + xmlGridParameters + "!!!");
		return search(xmlGridParameters);
	}

	@RequestMapping(value = PROTECTED_SERVICE + "/testrun/search")
	protected ModelAndView search(@RequestParam(value = GRID_PARAMETERS, required = true) String xmlGridParameters) {
		logger.trace("Request for search test-runs with parameters " + xmlGridParameters + "!!!");

		TestRunGridParameters gridParameters = null;
		String keyword = null;
		Long productId = null;
		Long testPlanId = null;

		if (StringUtils.isNotEmpty(xmlGridParameters)) {
			gridParameters = getGridParameters(xmlGridParameters, TestRunGridParameters.class);
			keyword = gridParameters.getKeyword();
			productId = gridParameters.getProductId();
			testPlanId = gridParameters.getTestPlanId();
		}

		PagingAndSorting pagingAndSorting = getPagingAndSorting(gridParameters);

		// Return all runs according to keyword search
		List<ITestRun> runs = testRunService.getAll(pagingAndSorting.getPaging(), pagingAndSorting.getSorting(), keyword, productId, testPlanId);

		// Extract id from the original objects
		List<Long> ids = getIds(runs);

		// Calculate run statistics - grouped by id
		Map<Long, List<TestRunStatisticsRendered>> statistics = getStatisticsMap(testRunService.getStatistics(ids));

		// Convert all users to UsersRendered
		TestRunRenderedList testRunRenderedList = getAsRenderer(runs, TestRunWithStatisticsRendered.class, ITestRun.class, TestRunRenderedList.class, gridParameters);

		// Merge TestRunStatisticsRendered into TestRunWithStatisticsRendered
		testRunRenderedList = mergeStatistics(testRunRenderedList, statistics);

		return getXMLViewer(testRunRenderedList);
	}

	/**
	 * Merge TestRunStatisticsRendered into TestRunWithStatisticsRendered
	 */
	protected TestRunRenderedList mergeStatistics(TestRunRenderedList testRunRenderedList, Map<Long, List<TestRunStatisticsRendered>> statistics) {
		if (testRunRenderedList != null && CollectionUtils.isNotEmpty(testRunRenderedList.getInnerList()) && statistics != null) {
			for (TestRunWithStatisticsRendered testRunStat : testRunRenderedList.getInnerList()) {
				testRunStat.setStatistics(statistics.get(testRunStat.getId()));
			}
		}

		return testRunRenderedList;
	}

	/**
	 * Convert List<TestRunStatisticsPojo> to List<TestRunStatisticsRendered>, <br/>
	 * and Group the result according to TestRun id.
	 */
	protected Map<Long, List<TestRunStatisticsRendered>> getStatisticsMap(List<TestRunStatisticsPojo> statistics) {
		Map<Long, List<TestRunStatisticsRendered>> runStatistics = new HashMap<Long, List<TestRunStatisticsRendered>>();

		if (CollectionUtils.isNotEmpty(statistics)) {
			for (TestRunStatisticsPojo stat : statistics) {
				if (!runStatistics.containsKey(stat.getTestRun().getId())) {
					runStatistics.put(stat.getTestRun().getId(), new ArrayList<TestRunStatisticsRendered>());
				}

				runStatistics.get(stat.getTestRun().getId()).add(new TestRunStatisticsRendered(stat));
			}
		}

		return runStatistics;
	}

	@Override
	@RequestMapping(value = PROTECTED_SERVICE + "/testrun/delete")
	protected ModelAndView delete(@RequestParam(value = CRUD_PARAMETERS, required = true) Long id) {
		return super.delete(id);
	}

	@RequestMapping(value = PROTECTED_SERVICE + "/testrun/update")
	protected ModelAndView update(@RequestParam(value = CRUD_PARAMETERS, required = true) String xmlCrudParameters) {
		return super.update(xmlCrudParameters, TestRunRendered.class, TestRun.class);
	}

	@RequestMapping(value = PROTECTED_SERVICE + "/testrun/create")
	protected ModelAndView create(@RequestParam(value = CRUD_PARAMETERS, required = true) String xmlCrudParameters) {
		return super.create(xmlCrudParameters, TestRunRendered.class, TestRun.class);
	}

	@RequestMapping(value = PROTECTED_SERVICE + "/testrun/start")
	protected ModelAndView start(@RequestParam(value = CRUD_PARAMETERS, required = true) Long id) {
		try {
			TestRun testRun = (TestRun) testRunService.get(id);
			testRun.setStatus((RunStatus) runStatusService.get(RunStatusEnum.RUNNING));
			testRunService.update(testRun);

			return getXMLViewer(getInfo(getMessages().getMessage(GeneralProperties.class.getName() + "." + GeneralProperties.INFO_TITLE.name()),
					getMessages().getMessage(TestRunProperties.class.getName() + "." + TestRunProperties.START_SUCCEED.name(), getModelName())));
		} catch (Exception e) {
			return handleException(e, TestRunProperties.class.getName() + "." + TestRunProperties.START_FAILED.name());
		}
	}

	@RequestMapping(value = PROTECTED_SERVICE + "/testrun/stop")
	protected ModelAndView stop(@RequestParam(value = CRUD_PARAMETERS, required = true) Long id) {
		try {
			TestRun testRun = (TestRun) testRunService.get(id);
			testRun.setStatus((RunStatus) runStatusService.get(RunStatusEnum.STOPPED));
			testRunService.update(testRun);

			return getXMLViewer(getInfo(getMessages().getMessage(GeneralProperties.class.getName() + "." + GeneralProperties.INFO_TITLE.name()),
					getMessages().getMessage(TestRunProperties.class.getName() + "." + TestRunProperties.STOP_SUCCEED.name(), getModelName())));
		} catch (Exception e) {
			return handleException(e, TestRunProperties.class.getName() + "." + TestRunProperties.STOP_FAILED.name());
		}

	}

	@Override
	protected ITestRun beforeCreate(ITestRun model) {
		return setDate(setUser(setStatus(model)));
	}

	private ITestRun setUser(ITestRun testCase) {
		testCase.setCreatedBy((UserPojo) userService.getCurrentUser());

		return testCase;
	}

	private ITestRun setDate(ITestRun testCase) {
		testCase.setCreatedDate(new Date());

		return testCase;
	}

	private ITestRun setStatus(ITestRun testCase) {
		testCase.setStatus((RunStatusPojo) runStatusService.get(RunStatusEnum.IDLE));

		return testCase;
	}
}
