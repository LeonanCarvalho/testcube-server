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
import org.jatakasource.testcube.model.product.CategoryPojo;

@Entity
@Table(name = "CUB_CATEGORIES", uniqueConstraints = { @UniqueConstraint(name = "UC_NAME_PRODUCT", columnNames = { Category.FIELD_NAME, Category.COLUMN_PRODUCT }),
		@UniqueConstraint(name = "UC_DESC_PRODUCT", columnNames = { Category.FIELD_DESC, Category.COLUMN_PRODUCT }) })
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Category extends CategoryPojo {
	private static final long serialVersionUID = 306211600473149541L;

	private static final String[][] UNIQUE_CONSTRAINTS = new String[][] { new String[] { Component.FIELD_NAME, Component.FIELD_PRODUCT },
			new String[] { Component.FIELD_DESC, Component.FIELD_PRODUCT } };

	public Category() {

	}

	public Category(Long id) {
		setId(id);
	}

	@Id
	@SequenceGenerator(name = "CUB_CATEGORIES_SEQ", sequenceName = "CUB_CATEGORIES_SEQ", initialValue = 0)
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "CUB_CATEGORIES_SEQ")
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
	@ForeignKey(name = "FK_CATEGORIES_PRODUCTS")
	@Index(name = "IDX_CATEGORIES_PRODUCTS")
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
