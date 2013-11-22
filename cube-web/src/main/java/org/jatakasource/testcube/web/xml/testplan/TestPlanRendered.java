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
package org.jatakasource.testcube.web.xml.testplan;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.jatakasource.testcube.model.product.ProductPojo;
import org.jatakasource.testcube.model.product.VersionPojo;
import org.jatakasource.testcube.model.testplan.ITestPlan;
import org.jatakasource.testcube.model.testplan.PlanAttachmentPojo;
import org.jatakasource.testcube.model.testplan.PlanTypePojo;
import org.jatakasource.testcube.web.xml.NameDescriptionRendered;
import org.jatakasource.testcube.web.xml.product.ProductRendered;
import org.jatakasource.testcube.web.xml.product.VersionRendered;
import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "TESTPLAN")
public class TestPlanRendered extends NameDescriptionRendered<ITestPlan> {

	@Attribute(required = false)
	public Date getCreatedDate() {
		return getDelegated().getCreatedDate();
	}

	@Attribute(required = false)
	public void setCreatedDate(Date createdDate) {
		getDelegated().setCreatedDate(createdDate);
	}

	@Element(name = "CREATED_BY", required = false)
	public String getCreatedBy() {
		return getDelegated().getCreatedBy() != null ? getDelegated().getCreatedBy().getUsername() : StringUtils.EMPTY;
	}

	@Element(name = "CREATED_BY", required = false)
	public void setCreatedBy(String createdBy) {
		// EMPTY XML method
	}

	@Element(name = "PRODUCT", required = true)
	public ProductRendered getProduct() {
		return new ProductRendered(getDelegated().getProduct());
	}

	@Element(name = "PRODUCT", required = true)
	public void setProduct(ProductRendered product) {
		getDelegated().setProduct((ProductPojo) product.getDelegated());
	}

	@Element(name = "PLAN_TYPE", required = true)
	public PlanTypeRendered getPlanType() {
		return new PlanTypeRendered(getDelegated().getPlanType());
	}

	@Element(name = "PLAN_TYPE", required = true)
	public void setPlanType(PlanTypeRendered planType) {
		getDelegated().setPlanType((PlanTypePojo) planType.getDelegated());
	}

	@Element(name = "PRODUCT_VERSION", required = true)
	public VersionRendered getProductVersion() {
		return new VersionRendered(getDelegated().getProductVersion());
	}

	@Element(name = "PRODUCT_VERSION", required = true)
	public void setProductVersion(VersionRendered productVersion) {
		getDelegated().setProductVersion((VersionPojo) productVersion.getDelegated());
	}

	public <T extends PlanAttachmentPojo> List<T> getAttachments() {
		return getDelegated().getAttachments();
	}

	public <T extends PlanAttachmentPojo> void setAttachments(List<T> attachments) {
		getDelegated().setAttachments(attachments);
	}

	@Element(name = "DOCUMENT", required = false, data = false)
	public String getDocument() {
		return getDelegated().getDocument();
	}

	@Element(name = "DOCUMENT", required = false, data = false)
	public void setDocument(String document) {
		getDelegated().setDocument(document);
	}
}
