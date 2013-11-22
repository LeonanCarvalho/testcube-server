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
package org.jatakasource.testcube.model;

import org.jatakasource.testcube.model.product.IProductRelated;
import org.jatakasource.testcube.model.product.ProductPojo;

public abstract class ProductRelatedPojo extends NameDescriptionPojo<Long> implements IProductRelated {
	private static final long serialVersionUID = -3332196886425878260L;
	public static final String COLUMN_PRODUCT = "product_id";
	
	public static final String FIELD_PRODUCT = "product";
	
	private Long id;
	private ProductPojo product;

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public ProductPojo getProduct() {
		return product;
	}

	@Override
	public void setProduct(ProductPojo product) {
		this.product = product;
	}
}
