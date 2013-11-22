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
import org.jatakasource.testcube.model.testrun.IRunStatus;
import org.jatakasource.testcube.model.testrun.RunStatus;
import org.jatakasource.testcube.model.testrun.RunStatusEnum;
import org.springframework.stereotype.Repository;

@Repository
public class HibernateRunStatus extends BaseHibernateDao<IRunStatus, Long> implements RunStatusDao {

	public HibernateRunStatus() {
		super(RunStatus.class);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<IRunStatus> findAll(Paging paging, Sorting sorting, String keyword) {
		Criteria criteria = getCurrentSession().createCriteria(RunStatus.class);

		if (StringUtils.isNotEmpty(keyword)) {
			criteria.add(Restrictions.or(Restrictions.ilike(Product.FIELD_NAME, keyword.toLowerCase(), MatchMode.ANYWHERE),
					Restrictions.ilike(Product.FIELD_DESC, keyword.toLowerCase(), MatchMode.ANYWHERE)));

		}

		addPaging(criteria, paging);
		addSorting(criteria, sorting);

		return criteria.list();
	}

	@Override
	public IRunStatus find(RunStatusEnum status) {
		Criteria criteria = getCurrentSession().createCriteria(RunStatus.class);
		criteria.add(Restrictions.eq(RunStatus.FIELD_STATUS, status));
		
		return (IRunStatus) criteria.uniqueResult();
	}
	
}
