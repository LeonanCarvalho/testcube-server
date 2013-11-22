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
package org.jatakasource.testcube.svc.security;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;
import org.jatakasource.common.authentication.PasswordAlgorithm;
import org.jatakasource.common.authentication.PasswordEncoders;
import org.jatakasource.common.dao.BaseDao;
import org.jatakasource.common.model.security.IUser;
import org.jatakasource.common.svc.CRUDServiceImpl;
import org.jatakasource.testcube.authentication.AuthenticationUtils;
import org.jatakasource.testcube.dao.security.UserDao;
import org.jatakasource.testcube.svc.security.LastAdminException;
import org.jatakasource.testcube.svc.security.PasswordNotMatchException;
import org.jatakasource.testcube.svc.security.UserNameExistsException;
import org.jatakasource.testcube.svc.security.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserServiceImpl extends CRUDServiceImpl<IUser, Long> implements UserService {

	@Autowired
	private UserDao userDao;
	@Autowired
	private PasswordEncoders encoders;

	@Override
	public BaseDao<IUser, Long> getDao() {
		return userDao;
	}

	@Override
	public IUser getUserByUserName(String userName) {
		return userDao.findByUserName(userName);
	}

	public IUser getCurrentUser() {
		Authentication authentication = AuthenticationUtils.getAuthentication();
		if (authentication != null) {
			IUser user = (IUser) authentication.getPrincipal();

			return user;
		}

		return null;
	}

	@Override
	public IUser remove(IUser user) {
		if (isLastAdmin(user, false)) {
			throw new LastAdminException();
		}

		return super.remove(user);
	}

	@Override
	public IUser update(IUser user) {
		IUser oldUser = get(user.getId());

		if (StringUtils.isNotEmpty(user.getPassword())) {
			setPassword(user, user.getPassword(), user.getConfirmpassword());
		} else {
			user.setPassword(oldUser.getPassword());
			user.setPasswordSlat(oldUser.getPasswordSlat());
		}

		if (isLastAdmin(user, true)) {
			throw new LastAdminException();
		}

		userDao.getCurrentSession().evict(oldUser);

		return super.update(user);
	}

	@Override
	public IUser add(IUser pojo) {
		setPassword(pojo, pojo.getPassword(), pojo.getConfirmpassword());

		if (exists(pojo.getUsername())) {
			throw new UserNameExistsException();
		}

		IUser user = super.add(pojo);

		return user;
	}

	private void setPassword(IUser user, String password, String confirmPassword) {
		if (password == null || !password.equals(confirmPassword)) {
			throw new PasswordNotMatchException();
		}

		String salt = RandomStringUtils.random(AuthenticationUtils.SLAT_SIZE);
		password = encoders.getPasswordEncoder(PasswordAlgorithm.SHA1).encodePassword(password, salt);
		user.setPassword(password);
		user.setPasswordSlat(salt);
	}

	public void resetPassword(Long id, String password, String confirmPassword) {
		IUser user = get(id);

		setPassword(user, password, confirmPassword);

		update(user);
	}

	public boolean exists(String username) {
		return userDao.exists(username);
	}

	/**
	 * Check rather we can modify/delete this administrator
	 * 
	 * @param user
	 *            User to check
	 * @param checkDetachedState
	 *            On delete, detached values are not relevant
	 */
	private boolean isLastAdmin(IUser user, boolean checkDetachedState) {
		boolean lastAdmin = userDao.isLastAdmin(user.getId());

		// On delete don't check detached state
		if (!checkDetachedState) {
			return lastAdmin;
		}

		return lastAdmin
				&& (user.isAdministrator() == false || user.isEnabled() == false || user.isAccountNonExpired() == false || user.isAccountNonLocked() == false || user
						.isCredentialsNonExpired() == false);

	}
}
