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
package org.jatakasource.testcube.web.xml.users;

import org.apache.commons.lang.StringUtils;
import org.jatakasource.common.model.security.IUser;
import org.jatakasource.common.model.security.UserPojo;
import org.jatakasource.testcube.model.security.User;
import org.jatakasource.web.xml.rendered.XMLRendererImpl;
import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "USER")
public class UserRendered extends XMLRendererImpl<IUser> {

	public UserRendered() {
		super(new User());
	}

	public UserRendered(UserPojo user) {
		super(user);
	}

	@Attribute(required = false)
	public Long getId() {
		return getDelegated().getId();
	}

	@Attribute(required = false)
	public void setId(Long id) {
		getDelegated().setId(id);
	}

	@Attribute(required = false)
	public String getPersonalId() {
		return getDelegated().getPersonalId();
	}

	@Attribute(required = false)
	public void setPersonalId(String personalId) {
		getDelegated().setPersonalId(personalId);
	}

	@Element(name = "FIRST_NAME", data = true)
	public String getFirstName() {
		return getDelegated().getFirstName();
	}

	@Element(name = "FIRST_NAME", data = true)
	public void setFirstName(String firstName) {
		getDelegated().setFirstName(firstName);
	}

	@Element(name = "LAST_NAME", data = true)
	public String getLastName() {
		return getDelegated().getLastName();
	}

	@Element(name = "LAST_NAME", data = true)
	public void setLastName(String lastName) {
		getDelegated().setLastName(lastName);
	}

	@Element(name = "USER_NAME", data = true, required = true)
	public String getUsername() {
		return getDelegated().getUsername();
	}

	@Element(name = "USER_NAME", data = true, required = true)
	public void setUsername(String username) {
		getDelegated().setUsername(username);
	}

	@Element(name = "PASSWORD", data = true, required = false)
	public String getPassword() {
		return StringUtils.EMPTY;
	}

	@Element(name = "PASSWORD", data = true, required = false)
	public void setPassword(String password) {
		getDelegated().setPassword(password);
	}

	@Element(name = "CONFIRMPASSWORD", data = true, required = false)
	public String getConfirmpassword() {
		return StringUtils.EMPTY;
	}

	@Element(name = "CONFIRMPASSWORD", data = true, required = false)
	public void setConfirmpassword(String confirmpassword) {
		getDelegated().setConfirmpassword(confirmpassword);
	}

	@Attribute
	public boolean isAccountNonExpired() {
		return getDelegated().isAccountNonExpired();
	}

	@Attribute
	public void setAccountNonExpired(boolean accountNonExpired) {
		getDelegated().setAccountNonExpired(accountNonExpired);
	}

	@Attribute
	public boolean isAccountNonLocked() {
		return getDelegated().isAccountNonLocked();
	}

	@Attribute
	public void setAccountNonLocked(boolean accountNonLocked) {
		getDelegated().setAccountNonLocked(accountNonLocked);
	}

	@Attribute
	public boolean isAdministrator() {
		return getDelegated().isAdministrator();
	}

	@Attribute
	public void setAdministrator(boolean administrator) {
		getDelegated().setAdministrator(administrator);
	}

	@Attribute
	public boolean isCredentialsNonExpired() {
		return getDelegated().isCredentialsNonExpired();
	}

	@Attribute
	public void setCredentialsNonExpired(boolean credentialsNonExpired) {
		getDelegated().setCredentialsNonExpired(credentialsNonExpired);
	}

	@Attribute
	public boolean isEnabled() {
		return getDelegated().isEnabled();
	}

	@Attribute
	public void setEnabled(boolean enabled) {
		getDelegated().setEnabled(enabled);
	}

}
