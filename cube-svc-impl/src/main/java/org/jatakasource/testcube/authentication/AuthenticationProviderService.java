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

import org.jatakasource.common.authentication.PasswordEncoders;
import org.jatakasource.testcube.svc.security.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;

public class AuthenticationProviderService extends org.springframework.security.authentication.dao.DaoAuthenticationProvider {
	@Autowired
	private UserService userService;
	@Autowired
	private PasswordEncoders encoders;

	/*
	 * @Override Spring implementation Add additional password validation here,
	 * Ex: password count or validation date
	 */
	@Override
	protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
		super.additionalAuthenticationChecks(userDetails, authentication);
	}

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		return super.authenticate(authentication);
	}

	protected UserService getUserService() {
		return userService;
	}

	protected void setUserService(UserService userService) {
		this.userService = userService;
	}

	protected PasswordEncoders getEncoders() {
		return encoders;
	}

	protected void setEncoders(PasswordEncoders encoders) {
		this.encoders = encoders;
	}

	@Override
	public PasswordEncoder getPasswordEncoder() {
		return super.getPasswordEncoder();
	}

	public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
		super.setPasswordEncoder(passwordEncoder);
	}
	
}
