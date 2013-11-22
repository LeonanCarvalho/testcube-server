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
package org.jatakasource.testcube.web.xml.product;

import org.jatakasource.testcube.model.product.IVersion;
import org.jatakasource.testcube.model.product.ProductPojo;
import org.jatakasource.testcube.model.product.Version;
import org.jatakasource.testcube.web.xml.NameDescriptionRendered;
import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "VERSION")
public class VersionRendered extends NameDescriptionRendered<IVersion> {
	public VersionRendered() {
		super(new Version());
	}

	public VersionRendered(IVersion delegated) {
		super(delegated);
	}

	@Attribute(required = true)
	public int getOrder() {
		return getDelegated().getOrderWeight();
	}

	@Attribute(required = true)
	public void setOrder(int order) {
		getDelegated().setOrderWeight(order);
	}

	@Element(name = "PRODUCT", required = true)
	public ProductRendered getProduct() {
		return new ProductRendered(getDelegated().getProduct());
	}

	@Element(name = "PRODUCT", required = true)
	public void setProduct(ProductRendered product) {
		getDelegated().setProduct((ProductPojo) product.getDelegated());
	}

}
