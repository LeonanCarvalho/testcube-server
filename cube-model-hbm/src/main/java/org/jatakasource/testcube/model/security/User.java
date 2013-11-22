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
package org.jatakasource.testcube.model.security;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.jatakasource.common.model.IDomainObject;
import org.jatakasource.common.model.security.UserPojo;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * Entity implementation class for Entity: User Represents a user
 */
@Entity
@Table(name = "CUB_USERS")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class User extends UserPojo implements UserDetails, IDomainObject<Long> {
	private static final long serialVersionUID = -1181257203921809659L;

	private static final String[][] UNIQUE_CONSTRAINTS = new String[][] { new String[] { User.FIELD_USERNAME } };

	public static final String FIELD_USERNAME = "username";
	public static final String FIELD_FIRSTNAME = "firstName";
	public static final String FIELD_LASTNAME = "lastName";
	public static final String FIELD_ADMINISTRATOR = "administrator";
	public static final String FIELD_ENABLED = "enabled";
	public static final String FIELD_CREDENTIALS_NON_EXPIRED = "credentialsNonExpired";
	public static final String FIELD_ACCOUNT_NON_LOCKED = "accountNonLocked";
	public static final String FIELD_ACCOUNT_NON_EXPIRED = "accountNonExpired";

	public static final int FIELD_PERSONAL_ID_LENGTH = 20;
	public static final int FIELD_PASSWORD_LENGTH = 100;
	public static final int FIELD_PASSWORD_SLAT_LENGTH = 50;

	private Collection<GrantedAuthority> grantedAuthority;

	public User() {
		super();
	}

	public User(Long id) {
		super(id);
	}

	@Id
	@SequenceGenerator(name = "CUB_USERS_SEQ", sequenceName = "CUB_USERS_SEQ", initialValue = 0)
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "CUB_USERS_SEQ")
	public Long getId() {
		return super.getId();
	}

	public void setId(Long id) {
		super.setId(id);
	}

	@Column(unique = true, length = FIELD_NAME_LENGTH, nullable = false)
	public String getUsername() {
		return super.getUsername();
	}

	@Override
	public void setUsername(String username) {
		super.setUsername(username);
	}

	@Column(length = FIELD_NAME_LENGTH, nullable = false)
	public String getFirstName() {
		return super.getFirstName();
	}

	@Override
	public void setFirstName(String firstName) {
		super.setFirstName(firstName);
	}

	@Column(length = FIELD_NAME_LENGTH, nullable = false)
	public String getLastName() {
		return super.getLastName();
	}

	@Override
	public void setLastName(String lastName) {
		super.setLastName(lastName);
	}

	@Column(length = FIELD_PERSONAL_ID_LENGTH)
	public String getPersonalId() {
		return super.getPersonalId();
	}

	@Override
	public void setPersonalId(String personalId) {
		super.setPersonalId(personalId);
	}

	@Override
	@Column(length = FIELD_PASSWORD_LENGTH, nullable = false)
	public String getPassword() {
		return super.getPassword();
	}

	@Override
	public void setPassword(String password) {
		super.setPassword(password);
	}

	@Transient
	@Override
	public String getConfirmpassword() {
		return super.getConfirmpassword();
	}

	@Override
	public void setConfirmpassword(String confirmpassword) {
		super.setConfirmpassword(confirmpassword);
	}

	@Override
	@Column
	public boolean isAccountNonExpired() {
		return super.isAccountNonExpired();
	}

	@Override
	public void setAccountNonExpired(boolean accountNonExpired) {
		super.setAccountNonExpired(accountNonExpired);
	}

	@Override
	@Column
	public boolean isAccountNonLocked() {
		return super.isAccountNonLocked();
	}

	@Override
	public void setAccountNonLocked(boolean accountNonLocked) {
		super.setAccountNonLocked(accountNonLocked);
	}

	@Override
	@Column
	public boolean isCredentialsNonExpired() {
		return super.isCredentialsNonExpired();
	}

	@Override
	public void setCredentialsNonExpired(boolean credentialsNonExpired) {
		super.setCredentialsNonExpired(credentialsNonExpired);
	}

	@Override
	@Column(name = "isEnabled")
	public boolean isEnabled() {
		return super.isEnabled();
	}

	@Override
	public void setEnabled(boolean enabled) {
		super.setEnabled(enabled);
	}

	@Column
	public boolean isAdministrator() {
		return super.isAdministrator();
	}

	@Override
	public void setAdministrator(boolean administrator) {
		super.setAdministrator(administrator);
	}

	@Column(length = FIELD_PASSWORD_SLAT_LENGTH, nullable = false)
	public String getPasswordSlat() {
		return super.getPasswordSlat();
	}

	@Override
	public void setPasswordSlat(String passwordSlat) {
		super.setPasswordSlat(passwordSlat);
	}

	@Override
	@Transient
	public Collection<GrantedAuthority> getAuthorities() {
		return grantedAuthority;
	}

	public void setAuthorities(Collection<GrantedAuthority> grantedAuthority) {
		this.grantedAuthority = grantedAuthority;
	}

	@Override
	public String[][] uniqueConstraints() {
		return UNIQUE_CONSTRAINTS;
	}
}
