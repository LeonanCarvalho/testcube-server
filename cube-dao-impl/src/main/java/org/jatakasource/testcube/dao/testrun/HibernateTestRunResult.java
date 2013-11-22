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
package org.jatakasource.testcube.dao.testrun;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.jatakasource.common.dao.BaseHibernateDao;
import org.jatakasource.common.dao.IInRestrictions;
import org.jatakasource.common.date.DateUtil;
import org.jatakasource.common.model.paging.Paging;
import org.jatakasource.common.model.paging.Sorting;
import org.jatakasource.testcube.model.testcase.TestCase;
import org.jatakasource.testcube.model.testrun.ITestRunResult;
import org.jatakasource.testcube.model.testrun.RunStatus;
import org.jatakasource.testcube.model.testrun.RunStatusEnum;
import org.jatakasource.testcube.model.testrun.RunStatusPerDate;
import org.jatakasource.testcube.model.testrun.TestRun;
import org.jatakasource.testcube.model.testrun.TestRunPojo;
import org.jatakasource.testcube.model.testrun.TestRunResult;
import org.jatakasource.testcube.model.testrun.TestRunStatisticsPojo;
import org.joda.time.DateTime;
import org.springframework.stereotype.Repository;

@Repository
public class HibernateTestRunResult extends BaseHibernateDao<ITestRunResult, Long> implements TestRunResultDao {
	private static final int STATUS_PERDAY_PERIOD = 14;

	public HibernateTestRunResult() {
		super(TestRunResult.class);
	}

	@Override
	public List<ITestRunResult> findAll(Paging paging, Sorting sorting, TestRun testRun, RunStatus status) {
		return findAll(paging, sorting, testRun, status);
	}

	@Override
	public List<ITestRunResult> findAll(Paging paging, Sorting sorting, TestRun testRun) {
		return findAll(paging, sorting, testRun, null);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<ITestRunResult> findAll(Paging paging, Sorting sorting, String keyword, TestRun testRun, RunStatus status) {
		Criteria criteria = getCurrentSession().createCriteria(TestRunResult.class);

		if (keyword != null) {
			criteria.createAlias(TestRunResult.FIELD_TESTCASE, TestRunResult.FIELD_TESTCASE_ALIAS);

			if (StringUtils.isNotEmpty(keyword)) {
				criteria.add(Restrictions.or(Restrictions.ilike(TestRunResult.FIELD_TESTCASE_ALIAS + "." + TestCase.FIELD_NAME, keyword.toLowerCase(), MatchMode.ANYWHERE),
						Restrictions.ilike(TestRunResult.FIELD_TESTCASE_ALIAS + "." + TestCase.FIELD_DESC, keyword.toLowerCase(), MatchMode.ANYWHERE)));
			}
		}

		if (testRun != null) {
			criteria.add(Restrictions.eq(TestRunResult.FIELD_TEST_RUN, testRun));
		}

		if (status != null) {
			criteria.add(Restrictions.and(Restrictions.eq(TestRunResult.FIELD_STATUS, status)));
		}

		addPaging(criteria, paging);
		addSorting(criteria, sorting);

		return criteria.list();
	}

	@Override
	public List<ITestRunResult> findAll(Paging paging, Sorting sorting, String arg2) {
		return findAll(paging, sorting, null, null);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TestRunStatisticsPojo> getStatistics(List<Long> ids) {
		return getInRestrictions(ids, new IInRestrictions() {

			@Override
			public <X, ID> List<X> getSingleBlock(List<ID> ids) {
				Criteria criteria = getCurrentSession().createCriteria(TestRunResult.class);

				ProjectionList projectionList = Projections.projectionList().add(Projections.rowCount());

				projectionList.add(Projections.groupProperty(TestRunResult.FIELD_TEST_RUN));
				projectionList.add(Projections.groupProperty(TestRunResult.FIELD_STATUS));

				criteria.setProjection(projectionList);
				criteria.add(Restrictions.in(TestRunResult.FIELD_TEST_RUN + "." + TestRun.FIELD_ID, ids));

				List<Object[]> results = criteria.list();
				List<TestRunStatisticsPojo> resultStatistics = new ArrayList<TestRunStatisticsPojo>();

				if (CollectionUtils.isNotEmpty(results)) {
					for (Object[] result : results) {
						resultStatistics.add(new TestRunStatisticsPojo((Long) result[0], (TestRunPojo) result[1], (RunStatus) result[2]));
					}
				}

				return (List<X>) resultStatistics;
			}
		});

	}

	@SuppressWarnings("unchecked")
	public List<RunStatusPerDate> getStatusByDay() {
		StringBuffer HQL = new StringBuffer("SELECT ");
		// Select status
		HQL.append(TestRunResult.FIELD_STATUS_ALIAS + "." + RunStatus.FIELD_STATUS).append(",");
		// Count by status
		HQL.append(" COUNT(").append(TestRunResult.FIELD_STATUS_ALIAS + "." + RunStatus.FIELD_STATUS + "),");
		// Select Year Month Day
		HQL.append(" YEAR(" + TestRunResult.FIELD_UPDATEDDATE_ALIAS + "), MONTH(" + TestRunResult.FIELD_UPDATEDDATE_ALIAS + "), DAY(" + TestRunResult.FIELD_UPDATEDDATE_ALIAS + ")");
		HQL.append(" FROM ").append(TestRunResult.class.getSimpleName()).append(" result");

		// Only in the last month
		HQL.append(" WHERE " + TestRunResult.FIELD_UPDATEDDATE_ALIAS + ">:" + TestRunResult.FIELD_UPDATEDDATE);

		HQL.append(" GROUP BY ").append(TestRunResult.FIELD_STATUS_ALIAS + "." + RunStatus.FIELD_STATUS).append(",");
		HQL.append(" YEAR(" + TestRunResult.FIELD_UPDATEDDATE_ALIAS + "), MONTH(" + TestRunResult.FIELD_UPDATEDDATE_ALIAS + "), DAY(" + TestRunResult.FIELD_UPDATEDDATE_ALIAS + ")");

		Query query = getCurrentSession().createQuery(HQL.toString());
		// Only in the last month
		query.setDate(TestRunResult.FIELD_UPDATEDDATE, DateUtil.minusDays(new Date(), STATUS_PERDAY_PERIOD));

		List<Object[]> results = query.list();
		List<RunStatusPerDate> statisrics = new ArrayList<RunStatusPerDate>();
		Map<Date, RunStatusPerDate> statisticsMap = initMap(STATUS_PERDAY_PERIOD);
		RunStatusPerDate runStatusperDay = null;

		for (Object[] result : results) {
			RunStatusEnum status = (RunStatusEnum) result[0];
			long count = getIntegerValue(result[1]);
			int year = getIntegerValue(result[2]);
			int month = getIntegerValue(result[3]);
			int day = getIntegerValue(result[4]);

			// Add Statistics to map if absent
			if (!statisticsMap.containsKey(getDateKey(result))) {
				statisticsMap.put(getDateKey(result), new RunStatusPerDate(new DateTime(year, month, day, 0, 0, 0).toDate()));
			}

			runStatusperDay = statisticsMap.get(getDateKey(result));

			switch (status) {
			case IDLE:
				runStatusperDay.plusIdle(count);
				break;
			case PASSED:
				runStatusperDay.plusPassed(count);
				break;
			case FAILED:
				runStatusperDay.plusFailed(count);
				break;

			default:
				break;
			}

		}

		statisrics.addAll(statisticsMap.values());

		// Sort the result according to date
		Collections.sort(statisrics, PER_DAY_COMPARATOR);

		return statisrics;
	}

	private static Comparator<RunStatusPerDate> PER_DAY_COMPARATOR = new Comparator<RunStatusPerDate>() {

		@Override
		public int compare(RunStatusPerDate date1, RunStatusPerDate date2) {
			DateTime dateTime1 = new DateTime(date1.getDate());
			DateTime dateTime2 = new DateTime(date2.getDate());

			return dateTime1.compareTo(dateTime2);
		}
	};

	private Map<Date, RunStatusPerDate> initMap(int days) {
		Map<Date, RunStatusPerDate> statusMap = new HashMap<Date, RunStatusPerDate>();

		Date date = DateUtil.minusDays(getDateKey(new Date()), days - 1);
		for (int i = 1; i <= days; i++) {
			date = DateUtil.plusDays(date, 1);
			statusMap.put(date, new RunStatusPerDate(date));
		}

		return statusMap;
	}

	private Date getDateKey(Object[] result) {
		int year = getIntegerValue(result[2]);
		int month = getIntegerValue(result[3]);
		int day = getIntegerValue(result[4]);

		return new DateTime(year, month, day, 0, 0, 0).toDate();
	}

	private Date getDateKey(Date date) {
		DateTime today = new DateTime(new Date());

		return new DateTime(today.getYear(), today.getMonthOfYear(), today.getDayOfMonth(), 0, 0, 0).toDate();
	}
}
