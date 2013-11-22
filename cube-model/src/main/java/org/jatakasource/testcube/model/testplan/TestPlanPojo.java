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
package org.jatakasource.testcube.model.testplan;

import java.util.Date;
import java.util.List;

import org.jatakasource.common.model.security.UserPojo;
import org.jatakasource.testcube.model.ProductRelatedPojo;
import org.jatakasource.testcube.model.product.VersionPojo;

public class TestPlanPojo extends ProductRelatedPojo implements ITestPlan {
	private static final long serialVersionUID = 6974159548796984926L;

	private Date createdDate;
	private UserPojo createdBy;
	private PlanTypePojo planType;
	private VersionPojo productVersion;

	private String document;
	private List<PlanAttachmentPojo> attachments;

	@Override
	public Date getCreatedDate() {
		return createdDate;
	}

	@Override
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	@Override
	public UserPojo getCreatedBy() {
		return createdBy;
	}

	@Override
	public void setCreatedBy(UserPojo createdBy) {
		this.createdBy = createdBy;
	}

	@Override
	public PlanTypePojo getPlanType() {
		return planType;
	}

	@Override
	public void setPlanType(PlanTypePojo planType) {
		this.planType = planType;
	}

	@Override
	public VersionPojo getProductVersion() {
		return productVersion;
	}

	@Override
	public void setProductVersion(VersionPojo productVersion) {
		this.productVersion = productVersion;
	}

	@Override
	public String getDocument() {
		return document;
	}

	@Override
	public void setDocument(String document) {
		this.document = document;
	}

	@Override
	@SuppressWarnings("unchecked")
	public <T extends PlanAttachmentPojo> List<T> getAttachments() {
		return (List<T>) attachments;
	}

	@Override
	@SuppressWarnings("unchecked")
	public <T extends PlanAttachmentPojo> void setAttachments(List<T> attachments) {
		this.attachments = (List<PlanAttachmentPojo>) attachments;
	}
}
