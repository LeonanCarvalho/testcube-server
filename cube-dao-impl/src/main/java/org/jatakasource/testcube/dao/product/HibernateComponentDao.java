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
package org.jatakasource.testcube.dao.product;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.jatakasource.common.dao.BaseHibernateDao;
import org.jatakasource.common.model.paging.Paging;
import org.jatakasource.common.model.paging.Sorting;
import org.jatakasource.testcube.dao.product.ComponentDao;
import org.jatakasource.testcube.model.product.Component;
import org.jatakasource.testcube.model.product.IComponent;
import org.jatakasource.testcube.model.product.Product;
import org.springframework.stereotype.Repository;

@Repository
public class HibernateComponentDao extends BaseHibernateDao<IComponent, Long> implements ComponentDao {

	public HibernateComponentDao() {
		super(Component.class);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<IComponent> findAll(Paging paging, Sorting sorting, String keyword) {
		Criteria criteria = getCurrentSession().createCriteria(Component.class);

		if (StringUtils.isNotEmpty(keyword)) {
			criteria.add(Restrictions.or(Restrictions.ilike(Product.FIELD_NAME, keyword.toLowerCase(), MatchMode.ANYWHERE),
					Restrictions.ilike(Product.FIELD_DESC, keyword.toLowerCase(), MatchMode.ANYWHERE)));

		}

		addPaging(criteria, paging);
		addSorting(criteria, sorting);

		return criteria.list();
	}

}
