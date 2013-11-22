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
package org.jatakasource.testcube.model.testcase;

import java.util.List;

import org.jatakasource.testcube.model.AssignablePojo;
import org.jatakasource.testcube.model.product.CategoryPojo;
import org.jatakasource.testcube.model.testplan.TestPlanPojo;

public class TestCasePojo extends AssignablePojo<Long> implements ITestCase {
	private static final long serialVersionUID = 541936543237138094L;

	private Long id;
	private TestPlanPojo testPlan;

	private CasePriorityPojo priority;
	private CategoryPojo category;

	private String estimatedTime;
	private String bugs;
	private CaseStatusPojo status;
	private String labels;
	private String requirements;

	private String setup;
	private String action;
	private String expected;

	private List<CaseAttachmentPojo> attachments;

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public TestPlanPojo getTestPlan() {
		return testPlan;
	}

	@Override
	public void setTestPlan(TestPlanPojo testPlan) {
		this.testPlan = testPlan;
	}

	@Override
	public CasePriorityPojo getPriority() {
		return priority;
	}

	@Override
	public void setPriority(CasePriorityPojo priority) {
		this.priority = priority;
	}

	@Override
	public CategoryPojo getCategory() {
		return category;
	}

	@Override
	public void setCategory(CategoryPojo category) {
		this.category = category;
	}

	@Override
	public String getEstimatedTime() {
		return estimatedTime;
	}

	@Override
	public void setEstimatedTime(String estimatedTime) {
		this.estimatedTime = estimatedTime;
	}

	@Override
	public String getBugs() {
		return bugs;
	}

	@Override
	public void setBugs(String bugs) {
		this.bugs = bugs;
	}

	@Override
	public CaseStatusPojo getStatus() {
		return status;
	}

	@Override
	public void setStatus(CaseStatusPojo status) {
		this.status = status;
	}

	@Override
	public String getLabels() {
		return labels;
	}

	@Override
	public void setLabels(String labels) {
		this.labels = labels;
	}

	@Override
	public String getRequirements() {
		return requirements;
	}

	@Override
	public void setRequirements(String requirements) {
		this.requirements = requirements;
	}

	@Override
	public String getSetup() {
		return setup;
	}

	@Override
	public void setSetup(String setup) {
		this.setup = setup;
	}

	@Override
	public String getAction() {
		return action;
	}

	@Override
	public void setAction(String action) {
		this.action = action;
	}

	@Override
	public String getExpected() {
		return expected;
	}

	@Override
	public void setExpected(String expected) {
		this.expected = expected;
	}

	@Override
	@SuppressWarnings("unchecked")
	public <T extends CaseAttachmentPojo> List<T> getAttachments() {
		return (List<T>) attachments;
	}

	@Override
	@SuppressWarnings("unchecked")
	public <T extends CaseAttachmentPojo> void setAttachments(List<T> attachments) {
		this.attachments = (List<CaseAttachmentPojo>) attachments;
	}
}
