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

import java.util.ArrayList;
import java.util.Collection;

import org.jatakasource.testcube.model.security.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

public class AuthenticationUtils {

	private static final String ROLE_ADMIN = "ROLE_ADMIN";
	private static final String ROLE_USER = "ROLE_USER";
	public static final int SLAT_SIZE = 10;

	/**
	 * Accepts list of Roles and return array[] of ACEGI GrantedAuthorityImpl
	 */
	public static Collection<GrantedAuthority> toGrantedAuthority(User user) {
		if (user.getAuthorities() != null && user.getAuthorities().size() > 0) {
			return user.getAuthorities();
		}

		Collection<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();

		if (user.isAdministrator()) {
			grantedAuthorities.add(new SimpleGrantedAuthority(ROLE_ADMIN));
		} else {
			grantedAuthorities.add(new SimpleGrantedAuthority(ROLE_USER));
		}

		return grantedAuthorities;
	}

	public static Authentication getAuthentication() {
		return SecurityContextHolder.getContext().getAuthentication();
	}

}
