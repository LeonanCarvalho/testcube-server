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

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
import org.jatakasource.testcube.model.product.VersionPojo;

@Entity(name = "CUB_VERSIONS")
@Table(name = "CUB_VERSIONS", uniqueConstraints = { @UniqueConstraint(name = "UC_NAME_PRODUCT", columnNames = { Version.FIELD_NAME, Version.COLUMN_PRODUCT }),
		@UniqueConstraint(name = "UC_ORDER_PRODUCT", columnNames = { Version.FIELD_ORDER, Version.COLUMN_PRODUCT }) })
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Version extends VersionPojo {
	private static final long serialVersionUID = 3741652467639841752L;
	
	private static final String[][] UNIQUE_CONSTRAINTS = new String[][] { new String[] { Version.FIELD_NAME, Version.FIELD_PRODUCT }, 
		new String[] { Version.FIELD_ORDER, Version.FIELD_PRODUCT }};
	
	public Version() {

	}

	public Version(Long id) {
		this.setId(id);
	}

	@Id
	@SequenceGenerator(name = "CUB_VERSIONS_SEQ", sequenceName = "CUB_VERSIONS_SEQ", initialValue = 0)
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "CUB_VERSIONS_SEQ")
	public Long getId() {
		return super.getId();
	}

	@Override
	public void setId(Long id) {
		super.setId(id);
	}

	/**
	 * Version name is not unique in order to allow multiple names for different products.
	 */
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
	@Column(nullable = false)
	public int getOrderWeight() {
		return super.getOrderWeight();
	}

	@Override
	public void setOrderWeight(int orderWeight) {
		super.setOrderWeight(orderWeight);
	}

	@Override
	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@Fetch(FetchMode.SELECT)
	@ForeignKey(name = "FK_VERSIONS_PRODUCTS")
	@Index(name = "IDX_VERSIONS_PRODUCTS")
	public Product getProduct() {
		return (Product) super.getProduct();
	}

	public void setProduct(Product product) {
		super.setProduct(product);
	}

	@Override
	public String[][] uniqueConstraints() {
		return UNIQUE_CONSTRAINTS;
	}
}
