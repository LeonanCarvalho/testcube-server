package org.jatakasource.testcube.web.xml.testrun;

import org.jatakasource.common.date.DateUtil;
import org.jatakasource.testcube.model.testrun.RunStatusPerDate;
import org.jatakasource.web.xml.rendered.XMLRendererImpl;
import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "RUNSTATUS_DATE")
public class RunStatusPerDateRendered extends XMLRendererImpl<RunStatusPerDate> {

	@Element(name = "DATE", data = true, required = true)
	public String getDate() {
		return DateUtil.DAY_MONTH_DATE_FORMAT.format(getDelegated().getDate());
	}

	@Element(name = "DATE", data = true, required = true)
	public void setDate(String date) {
		// Empty XML setter
	}

	@Attribute(required = true)
	public Long getIdle() {
		return getDelegated().getIdle();
	}

	@Attribute(required = true)
	public void setIdle(Long idle) {
		getDelegated().setIdle(idle);
	}

	@Attribute(required = true)
	public Long getPassed() {
		return getDelegated().getPassed();
	}

	@Attribute(required = true)
	public void setPassed(Long passed) {
		getDelegated().setPassed(passed);
	}

	@Attribute(required = true)
	public Long getFailed() {
		return getDelegated().getFailed();
	}

	@Attribute(required = true)
	public void setFailed(Long failed) {
		getDelegated().setFailed(failed);
	}
}
