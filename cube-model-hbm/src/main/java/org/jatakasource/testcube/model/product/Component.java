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
package org.jatakasource.testcube.model.product;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.ForeignKey;
import org.hibernate.annotations.Index;
import org.jatakasource.common.model.security.UserPojo;
import org.jatakasource.testcube.model.product.ComponentPojo;
import org.jatakasource.testcube.model.security.User;

@Entity
@Table(name = "CUB_COMPONENTS", uniqueConstraints = { @UniqueConstraint(name = "UC_NAME_PRODUCT", columnNames = { Component.FIELD_NAME, Component.COLUMN_PRODUCT }),
		@UniqueConstraint(name = "UC_DESC_PRODUCT", columnNames = { Component.FIELD_DESC, Component.COLUMN_PRODUCT }) })
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Component extends ComponentPojo {
	private static final long serialVersionUID = 5773702548710484319L;
	
	private static final String[][] UNIQUE_CONSTRAINTS = new String[][] { new String[] { Component.FIELD_NAME, Component.FIELD_PRODUCT }, 
		new String[] { Component.FIELD_DESC, Component.FIELD_PRODUCT }};
	
	@Id
	@SequenceGenerator(name = "CUB_COMPONENTS_SEQ", sequenceName = "CUB_COMPONENTS_SEQ", initialValue = 0)
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "CUB_COMPONENTS_SEQ")
	public Long getId() {
		return super.getId();
	}

	@Override
	public void setId(Long id) {
		super.setId(id);
	}

	@Override
	@Column(length = FIELD_NAME_LENGTH, nullable = false)
	@Index(name = "IDX_NAME")
	public String getName() {
		return super.getName();
	}

	@Override
	public void setName(String name) {
		super.setName(name);
	}

	@Override
	@Column(length = FIELD_DESC_LENGTH, nullable = false)
	@Index(name = "IDX_DESCRIPTION")
	public String getDescription() {
		return super.getDescription();
	}

	@Override
	public void setDescription(String description) {
		super.setDescription(description);
	}

	@Override
	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@Fetch(FetchMode.SELECT)
	@ForeignKey(name = "FK_COMPONENTS_PRODUCTS")
	@Index(name = "IDX_COMPONENTS_PRODUCTS")
	public Product getProduct() {
		return (Product) super.getProduct();
	}

	public void setProduct(Product product) {
		super.setProduct(product);
	}

	@Override
	@ManyToOne(fetch = FetchType.EAGER)
	@Fetch(FetchMode.SELECT)
	@ForeignKey(name = "FK_COMPONENTS_USERS")
	@Index(name = "IDX_COMPONENTS_USERS")
	public User getDefaultAssignee() {
		return (User) super.getDefaultAssignee();
	}

	public void setDefaultAssignee(User defaultAssignee) {
		super.setDefaultAssignee(defaultAssignee);
	}

	@Override
	@SuppressWarnings("unchecked")
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "CUB_COMPONENTS_CC_LIST")
	@Fetch(FetchMode.SELECT)
	public List<User> getDefaultCCList() {
		return super.getDefaultCCList();
	}

	@Override
	public <T extends UserPojo> void setDefaultCCList(List<T> defaultCCList) {
		super.setDefaultCCList(defaultCCList);
	}
	
	@Override
	public String[][] uniqueConstraints() {
		return UNIQUE_CONSTRAINTS;
	}
}
