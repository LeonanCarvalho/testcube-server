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
package org.jatakasource.testcube.data.system.users;

import org.jatakasource.common.data.DataReader;
import org.jatakasource.testcube.model.security.User;

public class UserReader extends DataReader<User> {
	private User user;

	public UserReader() {
		user = new User();
	}

	public UserReader(User user) {
		this.user = user;
	}

	public User getDelegated() {
		return user;
	}

	public String getUsername() {
		return user.getUsername();
	}

	public void setUsername(String username) {
		user.setUsername(username);
	}

	public String getPassword() {
		return user.getPassword();
	}

	public void setPassword(String password) {
		user.setPassword(password);
	}

	public String getPasswordSlat() {
		return user.getPasswordSlat();
	}

	public void setPasswordSlat(String passwordSlat) {
		user.setPasswordSlat(passwordSlat);
	}

	public String getFirstName() {
		return user.getFirstName();
	}

	public void setFirstName(String firstName) {
		user.setFirstName(firstName);
	}

	public String getLastName() {
		return user.getLastName();
	}

	public void setLastName(String lastName) {
		user.setLastName(lastName);
	}

	public boolean isAccountNonExpired() {
		return user.isAccountNonExpired();
	}

	public void setAccountNonExpired(boolean accountNonExpired) {
		user.setAccountNonExpired(accountNonExpired);
	}

	public boolean isAccountNonLocked() {
		return user.isAccountNonLocked();
	}

	public void setAccountNonLocked(boolean accountNonLocked) {
		user.setAccountNonLocked(accountNonLocked);
	}

	public boolean isAdministrator() {
		return user.isAdministrator();
	}

	public void setAdministrator(boolean administrator) {
		user.setAdministrator(administrator);
	}

	public boolean isCredentialsNonExpired() {
		return user.isCredentialsNonExpired();
	}

	public void setCredentialsNonExpired(boolean credentialsNonExpired) {
		user.setCredentialsNonExpired(credentialsNonExpired);
	}

	public boolean isEnabled() {
		return user.isEnabled();
	}

	public void setEnabled(boolean enabled) {
		user.setEnabled(enabled);
	}
}
