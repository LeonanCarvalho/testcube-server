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
package org.jatakasource.testcube.dao.security;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.jatakasource.common.dao.BaseHibernateDao;
import org.jatakasource.common.model.paging.Paging;
import org.jatakasource.common.model.paging.Sorting;
import org.jatakasource.common.model.security.IUser;
import org.jatakasource.testcube.dao.security.UserDao;
import org.jatakasource.testcube.model.security.User;
import org.springframework.stereotype.Repository;

@Repository
public class HibernateUserDao extends BaseHibernateDao<IUser, Long> implements UserDao {

	public HibernateUserDao() {
		super(User.class);
	}

	@Override
	public IUser findByUserName(String userName) {
		Criteria criteria = getCurrentSession().createCriteria(User.class);
		criteria.add(Restrictions.eq(User.FIELD_USERNAME, userName));

		return (IUser) criteria.uniqueResult();
	}

	
	@Override
	@SuppressWarnings("unchecked")
	public List<IUser> findAll(Paging paging, Sorting sorting, String keyword) {
		Criteria criteria = getCurrentSession().createCriteria(User.class);

		if (StringUtils.isNotEmpty(keyword)) {
			criteria.add(Restrictions.or(
					Restrictions.or(Restrictions.ilike(User.FIELD_USERNAME, keyword.toLowerCase(), MatchMode.ANYWHERE),
							Restrictions.ilike(User.FIELD_FIRSTNAME, keyword.toLowerCase(), MatchMode.ANYWHERE)),
					Restrictions.ilike(User.FIELD_LASTNAME, keyword.toLowerCase(), MatchMode.ANYWHERE)));
		}

		addPaging(criteria, paging);
		addSorting(criteria, sorting);

		return criteria.list();
	}

	@Override
	public boolean isLastAdmin(Long id) {
		Criteria criteria = getCurrentSession().createCriteria(User.class);
		criteria.add(Restrictions.ne(User.FIELD_ID, id));
		criteria.add(Restrictions.eq(User.FIELD_ADMINISTRATOR, true));
		criteria.add(Restrictions.eq(User.FIELD_ENABLED, true));
		criteria.add(Restrictions.eq(User.FIELD_ACCOUNT_NON_EXPIRED, true));
		criteria.add(Restrictions.eq(User.FIELD_ACCOUNT_NON_LOCKED, true));
		criteria.add(Restrictions.eq(User.FIELD_CREDENTIALS_NON_EXPIRED, true));
		criteria.setProjection(Projections.rowCount());

		return getIntegerValue(criteria.list().get(0)).intValue() == 0;
	}

	public boolean exists(String username) {
		Criteria criteria = getCurrentSession().createCriteria(User.class);
		criteria.add(Restrictions.eq(User.FIELD_USERNAME, username));
		criteria.setProjection(Projections.rowCount());
		
		return getIntegerValue(criteria.list().get(0)).intValue() > 0;
	}
}
