package org.jatakasource.testcube.model.testrun;

import java.util.Date;

public class RunStatusPerDate {
	private Date date;
	private Long idle = 0L;
	private Long passed = 0L;
	private Long failed = 0L;

	public RunStatusPerDate(Date date) {
		super();
		this.date = date;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Long getIdle() {
		return idle;
	}

	public void setIdle(Long idle) {
		this.idle = idle;
	}

	public Long getPassed() {
		return passed;
	}

	public void setPassed(Long passed) {
		this.passed = passed;
	}

	public Long getFailed() {
		return failed;
	}

	public void setFailed(Long failed) {
		this.failed = failed;
	}

	public void plusIdle(Long idle) {
		this.idle += idle;
	}

	public void plusPassed(Long passed) {
		this.passed += passed;
	}

	public void plusFailed(Long failed) {
		this.failed += failed;
	}
}