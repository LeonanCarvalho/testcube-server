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
package org.jatakasource.testcube.dao.testplan;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.jatakasource.common.dao.BaseHibernateDao;
import org.jatakasource.common.model.paging.Paging;
import org.jatakasource.common.model.paging.Sorting;
import org.jatakasource.testcube.dao.testplan.TestPlanDao;
import org.jatakasource.testcube.model.testplan.ITestPlan;
import org.jatakasource.testcube.model.testplan.TestPlan;
import org.springframework.stereotype.Repository;

@Repository
public class HibernateTestPlan extends BaseHibernateDao<ITestPlan, Long> implements TestPlanDao {

	public HibernateTestPlan() {
		super(TestPlan.class);
	}

	public List<ITestPlan> findAll(Paging paging, Sorting sorting, String keyword) {
		return findAll(paging, sorting, keyword, null);
	}

	@SuppressWarnings("unchecked")
	public List<ITestPlan> findAll(Paging paging, Sorting sorting, String keyword, Long productId) {
		Criteria criteria = getCurrentSession().createCriteria(TestPlan.class);

		if (StringUtils.isNotEmpty(keyword)) {
			criteria.add(Restrictions.or(Restrictions.ilike(TestPlan.FIELD_NAME, keyword.toLowerCase(), MatchMode.ANYWHERE),
					Restrictions.ilike(TestPlan.FIELD_DESC, keyword.toLowerCase(), MatchMode.ANYWHERE)));

		}
		
		if (productId != null) {
			criteria.add(Restrictions.eq(TestPlan.FIELD_PRODUCT + "." + TestPlan.FIELD_ID, productId));
		}

		criteria = addPaging(criteria, paging);
		criteria = addSorting(criteria, sorting);

		return criteria.list();
	}

}
