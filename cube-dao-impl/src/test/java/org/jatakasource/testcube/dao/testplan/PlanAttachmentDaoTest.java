package org.jatakasource.testcube.dao.testplan;

import org.jatakasource.common.utils.FileUtils;
import org.jatakasource.testcube.dao.SpringDaoTest;
import org.jatakasource.testcube.dao.testplan.PlanAttachmentDao;
import org.jatakasource.testcube.model.testplan.IPlanAttachment;
import org.jatakasource.testcube.model.testplan.PlanAttachment;
import org.springframework.beans.factory.annotation.Autowired;

public class PlanAttachmentDaoTest extends SpringDaoTest<Long, PlanAttachmentDao, IPlanAttachment> {

	@Autowired
	private PlanAttachmentDao planAttachmentDao;

	@Override
	public PlanAttachmentDao getDao() {
		return planAttachmentDao;
	}

	@Override
	public IPlanAttachment[] getValidEntities() {
		return new IPlanAttachment[] { getValidPlanAttachment() };
	}

	@Override
	public IPlanAttachment[] getInvalidEntities() {
		IPlanAttachment attachment = (IPlanAttachment) getValidEntity();

		attachment.setName(null);
		attachment.setContent(null);
		attachment.setContentType(null);

		return new IPlanAttachment[] { attachment };
	}

	@Override
	public void change(IPlanAttachment entity) {
		entity.setDescription("TEST PLAN ATTACHMENT UPDATE");
	}

	public static IPlanAttachment getValidPlanAttachment() {
		PlanAttachment attachment = new PlanAttachment();

		attachment.setName("TEST PLAN ATTACHMENT");
		attachment.setDescription("TEST PLAN ATTACHMENT");
		attachment.setContentType("png");
		attachment.setContent(FileUtils.getResourceAsBytes(PlanAttachmentDaoTest.class, "attachment.png"));
		
		return attachment;
	}

}
