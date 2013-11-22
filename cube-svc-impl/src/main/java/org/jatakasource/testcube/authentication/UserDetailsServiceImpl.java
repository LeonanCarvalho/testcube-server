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
package org.jatakasource.testcube.authentication;

import org.apache.log4j.Logger;
import org.jatakasource.common.authentication.AuthenticationMessages;
import org.jatakasource.common.properties.MessageSourceUtils;
import org.jatakasource.testcube.database.dbcp.NoDBConnectionException;
import org.jatakasource.testcube.model.security.User;
import org.jatakasource.testcube.svc.security.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.dao.DataAccessException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.transaction.CannotCreateTransactionException;

/**
 * Spring bean defined at common-svc-security-context.xml
 */
public class UserDetailsServiceImpl implements UserDetailsService {
	private static final long serialVersionUID = -3149932713849013031L;
	private static final Logger logger = Logger.getLogger(UserDetailsServiceImpl.class);

	@Autowired
	private UserService userService;
	@Autowired
	@Qualifier(value = "authenticationMessageSource")
	private ResourceBundleMessageSource authenticationMessageSource;

	@Override
	public UserDetails loadUserByUsername(String userName) throws AuthenticationException, DataAccessException {
		User user = null;

		try {
			// Return member from DB and populate roles.
			user = (User) userService.getUserByUserName(userName);

			if (user == null) {
				if (logger.isDebugEnabled()) {
					logger.debug("User name " + userName + " is missing in database !!!");
				}
				throw new BadCredentialsException(MessageSourceUtils.getMessage(authenticationMessageSource, AuthenticationMessages.class,
						AuthenticationMessages.AUTHENTICATION_FAILED.name()));
			}

			user.setAuthorities(AuthenticationUtils.toGrantedAuthority((User) user));

			logger.trace("User: " + user.getUsername() + " grantedAuthorities: " + user.getAuthorities());
		} catch (CannotCreateTransactionException e) {

			logger.error("No connection to the database. Exception: " + e.getMessage());
			if (logger.isDebugEnabled()) {
				logger.debug("No connection to the database. Exception: " + e.getMessage(), e);
			}

			throw new NoDBConnectionException("No connection to the database. Exception: ", e);
		}
		return user;
	}
}