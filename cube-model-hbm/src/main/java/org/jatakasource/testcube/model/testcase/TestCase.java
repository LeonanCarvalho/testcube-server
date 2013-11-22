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

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.ForeignKey;
import org.hibernate.annotations.Index;
import org.hibernate.annotations.Type;
import org.jatakasource.testcube.model.product.Category;
import org.jatakasource.testcube.model.security.User;
import org.jatakasource.testcube.model.testplan.TestPlan;

@Entity
@Table(name = "CUB_TESTCASES", uniqueConstraints = { @UniqueConstraint(name = "UC_NAME_TESTPLAN", columnNames = { TestCase.FIELD_NAME, TestCase.COLUMN_TESTPLAN }) })
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class TestCase extends TestCasePojo {
	private static final long serialVersionUID = -7757273990577559594L;

	public static final String COLUMN_TESTPLAN = "testplan_id";
	public static final String FIELD_TESTPLAN = "testPlan";
	public static final String FIELD_TESTPLAN_ALIAS = "tp";
	public static final String FIELD_CATEGORY = "category";
	public static final String FIELD_CATEGORY_ALIAS = "ca";

	
	private static final String[][] UNIQUE_CONSTRAINTS = new String[][] { new String[] { TestCase.FIELD_NAME, TestCase.FIELD_TESTPLAN } };

	@Id
	@SequenceGenerator(name = "CUB_TESTCASES_SEQ", sequenceName = "CUB_TESTCASES_SEQ", initialValue = 0)
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "CUB_TESTCASES_SEQ")
	@Override
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
	@ForeignKey(name = "FK_TESTCASES_TESTPLAN")
	@Index(name = "IDX_TESTCASES_TESTPLAN")
	public TestPlan getTestPlan() {
		return (TestPlan) super.getTestPlan();
	}

	public void setTestPlan(TestPlan testPlan) {
		super.setTestPlan(testPlan);
	}

	@Override
	@Column(updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	public Date getCreatedDate() {
		return super.getCreatedDate();
	}

	@Override
	public void setCreatedDate(Date createdDate) {
		super.setCreatedDate(createdDate);
	}

	@Override
	@ManyToOne(fetch = FetchType.EAGER)
	@Fetch(FetchMode.SELECT)
	@ForeignKey(name = "FK_ASSIGNEE_USERS")
	@Index(name = "IDX_ASSIGNEE_USERS")
	public User getAssignee() {
		return (User) super.getAssignee();
	}

	public void setAssignee(User assignee) {
		super.setAssignee(assignee);
	}

	@Override
	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(updatable = false)
	@Fetch(FetchMode.SELECT)
	@ForeignKey(name = "FK_CREATEDBY_USERS")
	@Index(name = "IDX_CREATEDBY_USERS")
	public User getCreatedBy() {
		return (User) super.getCreatedBy();
	}

	public void setCreatedBy(User createdBy) {
		super.setCreatedBy(createdBy);
	}

	@Override
	@ManyToOne(fetch = FetchType.EAGER)
	@Fetch(FetchMode.SELECT)
	@ForeignKey(name = "FK_TESTCASES_PRIORITY")
	@Index(name = "IDX_TESTCASES_PRIORITY")
	public CasePriority getPriority() {
		return (CasePriority) super.getPriority();
	}

	public void setPriority(CasePriority priority) {
		super.setPriority(priority);
	}

	@Override
	@ManyToOne(fetch = FetchType.EAGER)
	@Fetch(FetchMode.SELECT)
	@ForeignKey(name = "FK_TESTCASES_CATEGORY")
	@Index(name = "IDX_TESTCASES_CATEGORY")
	public Category getCategory() {
		return (Category) super.getCategory();
	}

	public void setCategory(Category category) {
		super.setCategory(category);
	}

	@Override
	@Column(length = 4)
	public String getEstimatedTime() {
		return super.getEstimatedTime();
	}

	@Override
	public void setEstimatedTime(String estimatedTime) {
		super.setEstimatedTime(estimatedTime);
	}

	@Override
	@Column(length = FIELD_DESC_LENGTH)
	public String getBugs() {
		return super.getBugs();
	}

	@Override
	public void setBugs(String bugs) {
		super.setBugs(bugs);
	}

	@Override
	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@Fetch(FetchMode.SELECT)
	@ForeignKey(name = "FK_TESTCASES_STATUS")
	@Index(name = "IDX_TESTCASES_STATUS")
	public CaseStatus getStatus() {
		return (CaseStatus) super.getStatus();
	}

	public void setStatus(CaseStatus status) {
		super.setStatus(status);
	}

	@Override
	@Column(length = FIELD_DESC_LENGTH)
	public String getLabels() {
		return super.getLabels();
	}

	@Override
	public void setLabels(String labels) {
		super.setLabels(labels);
	}

	@Override
	@Column(length = FIELD_DESC_LENGTH)
	public String getRequirements() {
		return super.getRequirements();
	}

	@Override
	public void setRequirements(String requirements) {
		super.setRequirements(requirements);
	}

	@Lob
	@Column
	@Type(type = "org.hibernate.type.StringClobType")
	@Override
	public String getSetup() {
		return super.getSetup();
	}

	@Override
	public void setSetup(String setup) {
		super.setSetup(setup);
	}

	@Lob
	@Column
	@Type(type = "org.hibernate.type.StringClobType")
	@Override
	public String getAction() {
		return super.getAction();
	}

	@Override
	public void setAction(String action) {
		super.setAction(action);
	}

	@Lob
	@Type(type = "org.hibernate.type.StringClobType")
	@Column
	@Override
	public String getExpected() {
		return super.getExpected();
	}

	@Override
	public void setExpected(String expected) {
		super.setExpected(expected);
	}

	@Override
	@SuppressWarnings("unchecked")
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "CUB_TESTCASES_ATTACHMENTS")
	@Fetch(FetchMode.SELECT)
	public List<CaseAttachment> getAttachments() {
		return super.getAttachments();
	}

	@Override
	public <T extends CaseAttachmentPojo> void setAttachments(List<T> attachments) {
		super.setAttachments(attachments);
	}

	@Override
	public String[][] uniqueConstraints() {
		return UNIQUE_CONSTRAINTS;
	}
}
