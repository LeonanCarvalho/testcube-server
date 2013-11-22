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

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
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
import org.jatakasource.testcube.model.security.User;
import org.jatakasource.testcube.model.testcase.TestCase;

@Entity
@Table(name = "CUB_TESTRUN_RESULTS")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class TestRunResult extends TestRunResultPojo {
	private static final long serialVersionUID = -8489178426499616909L;

	public static final String COLUMN_TESTRUN = "testrun_id";
	public static final String COLUMN_TESTCASE = "testcase_id";
	public static final String COLUMN_STATUS = "status_id";

	public static final String FIELD_TEST_RUN = "testRun";
	public static final String FIELD_STATUS = "status";
	public static final String FIELD_TESTCASE = "testcase";
	public static final String FIELD_UPDATEDDATE = "updatedDate";
	public static final String FIELD_UPDATEDDATE_ALIAS = "result.updatedDate";
	public static final String FIELD_STATUS_ALIAS = "result.status";
	

	public static final String FIELD_TESTCASE_ALIAS = "tc";

	public TestRunResult() {

	}

	public TestRunResult(TestCase testcase, RunStatus status) {
		setTestcase(testcase);
		setStatus(status);
		setCreatedDate(new Date());
		setUpdatedDate(new Date());
	}

	@Id
	@SequenceGenerator(name = "CUB_TESTRUN_RESULTS_SEQ", sequenceName = "CUB_TESTRUN_RESULTS_SEQ", initialValue = 0)
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "CUB_TESTRUN_RESULTS_SEQ")
	@Override
	public Long getId() {
		return super.getId();
	}

	@Override
	public void setId(Long id) {
		super.setId(id);
	}

	@Override
	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@Fetch(FetchMode.SELECT)
	@ForeignKey(name = "FK_TESTRUN")
	@Index(name = "IDX_TESTRUNS")
	public TestRun getTestRun() {
		return (TestRun) super.getTestRun();
	}

	public void setTestRun(TestRun testRun) {
		super.setTestRun(testRun);
	}

	@Override
	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@Fetch(FetchMode.SELECT)
	@ForeignKey(name = "FK_TESTCASE")
	@Index(name = "IDX_TESTCASE")
	public TestCase getTestcase() {
		return (TestCase) super.getTestcase();
	}

	public void setTestcase(TestCase testcase) {
		super.setTestcase(testcase);
	}

	@Override
	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@Fetch(FetchMode.SELECT)
	@ForeignKey(name = "FK_RUNSTATUS")
	@Index(name = "IDX_RUNSTATUS")
	public RunStatus getStatus() {
		return (RunStatus) super.getStatus();
	}

	public void setStatus(RunStatus status) {
		super.setStatus(status);
	}

	@Override
	@Column(updatable = false, nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	public Date getCreatedDate() {
		return super.getCreatedDate();
	}

	@Override
	public void setCreatedDate(Date createdDate) {
		super.setCreatedDate(createdDate);
	}

	@Override
	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	public Date getUpdatedDate() {
		return super.getUpdatedDate();
	}

	@Override
	public void setUpdatedDate(Date updatedDate) {
		super.setUpdatedDate(updatedDate);
	}

	@Override
	@ManyToOne(fetch = FetchType.EAGER, optional = true)
	@Fetch(FetchMode.SELECT)
	@ForeignKey(name = "FK_UPDATEDBY_USERS")
	@Index(name = "IDX_UPDATEDBY_USERS")
	public User getUpdatedBy() {
		return (User) super.getUpdatedBy();
	}

	public void setUpdatedBy(User updatedBy) {
		super.setUpdatedBy(updatedBy);
	}

}
