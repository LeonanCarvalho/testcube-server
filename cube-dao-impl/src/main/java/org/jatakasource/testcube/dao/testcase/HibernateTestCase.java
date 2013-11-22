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
package org.jatakasource.testcube.dao.testcase;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.jatakasource.common.dao.BaseHibernateDao;
import org.jatakasource.common.model.paging.Paging;
import org.jatakasource.common.model.paging.Sorting;
import org.jatakasource.testcube.model.product.Category;
import org.jatakasource.testcube.model.product.Product;
import org.jatakasource.testcube.model.testcase.ITestCase;
import org.jatakasource.testcube.model.testcase.TestCase;
import org.jatakasource.testcube.model.testplan.ITestPlan;
import org.jatakasource.testcube.model.testplan.TestPlan;
import org.springframework.stereotype.Repository;

@Repository
public class HibernateTestCase extends BaseHibernateDao<ITestCase, Long> implements TestCaseDao {

	public HibernateTestCase() {
		super(TestCase.class);
	}

	public List<ITestCase> findAll(Paging paging, Sorting sorting, String keyword) {
		return findAll(paging, sorting, keyword, null, null);
	}

	public List<ITestCase> findAll(Paging paging, Sorting sorting, String keyword, Long testPlanId) {
		return findAll(paging, sorting, keyword, null, testPlanId);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<ITestCase> findAll(Paging paging, Sorting sorting, String keyword, Long productId, Long testPlanId) {
		Criteria criteria = getCurrentSession().createCriteria(TestCase.class);
		criteria.createAlias(TestCase.FIELD_CATEGORY, TestCase.FIELD_CATEGORY_ALIAS, JoinType.LEFT_OUTER_JOIN);

		if (StringUtils.isNotEmpty(keyword)) {
			criteria.add(Restrictions.or(
					Restrictions.or(Restrictions.ilike(TestCase.FIELD_NAME, keyword.toLowerCase(), MatchMode.ANYWHERE),
							Restrictions.ilike(TestCase.FIELD_DESC, keyword.toLowerCase(), MatchMode.ANYWHERE)),
					Restrictions.ilike(TestCase.FIELD_CATEGORY_ALIAS + "." + Category.FIELD_NAME, keyword.toLowerCase(), MatchMode.ANYWHERE)));
		}

		if (productId != null) {
			criteria.createAlias(TestCase.FIELD_TESTPLAN, TestCase.FIELD_TESTPLAN_ALIAS);
			criteria.add(Restrictions.eq(TestCase.FIELD_TESTPLAN_ALIAS + "." + TestPlan.FIELD_PRODUCT + "." + Product.FIELD_ID, productId));
		}

		if (testPlanId != null) {
			criteria.add(Restrictions.eq(TestCase.FIELD_TESTPLAN + "." + TestCase.FIELD_ID, testPlanId));
		}

		addPaging(criteria, paging);
		addSorting(criteria, sorting);

		return criteria.list();
	}

	@Override
	public Boolean isNameUniqe(ITestCase testcase, ITestPlan testPlan) {
		Criteria criteria = getCurrentSession().createCriteria(TestCase.class);
		
		if (testPlan != null) {
			criteria.add(Restrictions.eq(TestCase.FIELD_TESTPLAN + "." + TestPlan.FIELD_ID, testPlan.getId()));
		}
		
		if (testcase != null) {
			criteria.add(Restrictions.eq(TestCase.FIELD_NAME, testcase.getName()));
		}
		
		criteria.setProjection(Projections.rowCount());
		Integer counter = getIntegerValue(criteria.list().get(0));
		
		return counter == null || counter == 0;
	}

}
