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
package org.jatakasource.testcube.model.testrun;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.ForeignKey;
import org.hibernate.annotations.Index;
import org.hibernate.annotations.Type;
import org.jatakasource.testcube.model.product.Build;
import org.jatakasource.testcube.model.product.Environment;
import org.jatakasource.testcube.model.product.Version;
import org.jatakasource.testcube.model.security.User;
import org.jatakasource.testcube.model.testplan.TestPlan;

@Entity
@Table(name = "CUB_TESTRUNS")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class TestRun extends TestRunPojo {
	private static final long serialVersionUID = 7141328925130209722L;

	public static final String COLUMN_TESTPLAN = "testplan_id";
	public static final String FIELD_TESTPLAN = "testPlan";
	public static final String FIELD_TESTPLAN_ALIAS = "tp";

	public TestRun() {

	}

	public TestRun(Long id) {
		super.setId(id);
	}

	@Id
	@SequenceGenerator(name = "CUB_TESTRUNS_SEQ", sequenceName = "CUB_TESTRUNS_SEQ", initialValue = 0)
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "CUB_TESTRUNS_SEQ")
	@Override
	public Long getId() {
		return super.getId();
	}

	@Override
	public void setId(Long id) {
		super.setId(id);
	}

	@Override
	@Column(length = FIELD_DESC_LENGTH, nullable = false)
	@Index(name = "IDX_NAME")
	public String getName() {
		return super.getName();
	}

	@Lob
	@Column
	@Type(type = "org.hibernate.type.StringClobType")
	@Override
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
	@ForeignKey(name = "FK_TESTRUNS_TESTPLAN")
	@Index(name = "IDX_TESTRUNS_TESTPLAN")
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
	@Fetch(FetchMode.SELECT)
	@ForeignKey(name = "FK_PRODUCT_VERSION")
	@Index(name = "IDX_PRODUCT_VERSION")
	public Version getProductVersion() {
		return (Version) super.getProductVersion();
	}

	public void setProductVersion(Version productVersion) {
		super.setProductVersion(productVersion);
	}

	@Override
	@ManyToOne(fetch = FetchType.EAGER)
	@Fetch(FetchMode.SELECT)
	@ForeignKey(name = "FK_PRODUCT_BUILD")
	@Index(name = "IDX_PRODUCT_BUILD")
	public Build getProductBuild() {
		return (Build) super.getProductBuild();
	}

	public void setProductBuild(Build productBuild) {
		super.setProductBuild(productBuild);
	}

	@Override
	@ManyToOne(fetch = FetchType.EAGER)
	@Fetch(FetchMode.SELECT)
	@ForeignKey(name = "FK_PRODUCT_ENVIRONMENT")
	@Index(name = "IDX_PRODUCT_ENVIRONMENT")
	public Environment getProductEnvironment() {
		return (Environment) super.getProductEnvironment();
	}

	public void setProductEnvironment(Environment productEnvironment) {
		super.setProductEnvironment(productEnvironment);
	}

	@Override
	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@Fetch(FetchMode.SELECT)
	@ForeignKey(name = "FK_RUN_STATUS")
	@Index(name = "IDX_RUN_STATUS")
	public RunStatus getStatus() {
		return (RunStatus) super.getStatus();
	}

	public void setStatus(RunStatus status) {
		super.setStatus(status);
	}

	@Override
	@SuppressWarnings("unchecked")
	@OneToMany(mappedBy = "testRun", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@Fetch(FetchMode.SELECT)
	// Setting this FetchMode to JOIN will duplicate rows when querying for TestRun.
	public List<TestRunResult> getResults() {
		return super.getResults();
	}

	@Override
	public <T extends TestRunResultPojo> void setResults(List<T> results) {
		super.setResults(results);
	}
}
