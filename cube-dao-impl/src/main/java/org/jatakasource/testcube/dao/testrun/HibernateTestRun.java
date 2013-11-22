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

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.jatakasource.common.dao.BaseHibernateDao;
import org.jatakasource.common.model.paging.Paging;
import org.jatakasource.common.model.paging.Sorting;
import org.jatakasource.testcube.model.product.Product;
import org.jatakasource.testcube.model.testplan.TestPlan;
import org.jatakasource.testcube.model.testrun.ITestRun;
import org.jatakasource.testcube.model.testrun.TestRun;
import org.springframework.stereotype.Repository;

@Repository
public class HibernateTestRun extends BaseHibernateDao<ITestRun, Long> implements TestRunDao {

	public HibernateTestRun() {
		super(TestRun.class);
	}

	@Override
	public List<ITestRun> findAll() {
		return this.findAll(null, null, null);
	}

	public List<ITestRun> findAll(Paging paging, Sorting sorting, String keyword) {
		return findAll(paging, sorting, keyword, null, null);
	}

	public List<ITestRun> findAll(Paging paging, Sorting sorting, String keyword, Long testPlanId) {
		return findAll(paging, sorting, keyword, null, testPlanId);
	}

	@SuppressWarnings("unchecked")
	public List<ITestRun> findAll(Paging paging, Sorting sorting, String keyword, Long productId, Long testPlanId) {
		Criteria criteria = getCurrentSession().createCriteria(TestRun.class);

		if (StringUtils.isNotEmpty(keyword)) {
			criteria.add(Restrictions.or(Restrictions.ilike(TestRun.FIELD_NAME, keyword.toLowerCase(), MatchMode.ANYWHERE),
					Restrictions.ilike(TestRun.FIELD_DESC, keyword.toLowerCase(), MatchMode.ANYWHERE)));

		}

		if (productId != null) {
			criteria.createAlias(TestRun.FIELD_TESTPLAN, TestRun.FIELD_TESTPLAN_ALIAS);
			criteria.add(Restrictions.eq(TestRun.FIELD_TESTPLAN_ALIAS + "." + TestPlan.FIELD_PRODUCT + "." + Product.FIELD_ID, productId));
		}

		if (testPlanId != null) {
			criteria.add(Restrictions.eq(TestRun.FIELD_TESTPLAN + "." + TestRun.FIELD_ID, testPlanId));
		}

		addPaging(criteria, paging);
		addSorting(criteria, sorting);

		return criteria.list();
	}
}
