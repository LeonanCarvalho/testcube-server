package org.jatakasource.testcube.dao.testcase;

import org.jatakasource.common.utils.FileUtils;
import org.jatakasource.testcube.dao.SpringDaoTest;
import org.jatakasource.testcube.dao.testcase.CaseAttachmentDao;
import org.jatakasource.testcube.model.testcase.CaseAttachment;
import org.jatakasource.testcube.model.testcase.ICaseAttachment;
import org.springframework.beans.factory.annotation.Autowired;

public class CaseAttachmentDaoTest extends SpringDaoTest<Long, CaseAttachmentDao, ICaseAttachment> {

	@Autowired
	private CaseAttachmentDao caseAttachmentDao;

	@Override
	public CaseAttachmentDao getDao() {
		return caseAttachmentDao;
	}

	@Override
	public ICaseAttachment[] getValidEntities() {
		return new ICaseAttachment[] { getValidPlanAttachment() };
	}

	@Override
	public ICaseAttachment[] getInvalidEntities() {
		ICaseAttachment attachment = (ICaseAttachment) getValidEntity();

		attachment.setName(null);
		attachment.setContent(null);
		attachment.setContentType(null);

		return new ICaseAttachment[] { attachment };
	}

	@Override
	public void change(ICaseAttachment entity) {
		entity.setDescription("TEST PLAN ATTACHMENT UPDATE");
	}

	public static ICaseAttachment getValidPlanAttachment() {
		CaseAttachment attachment = new CaseAttachment();

		attachment.setName("TEST PLAN ATTACHMENT");
		attachment.setDescription("TEST PLAN ATTACHMENT");
		attachment.setContentType("png");
		attachment.setContent(FileUtils.getResourceAsBytes(CaseAttachmentDaoTest.class, "attachment.png"));
		
		return attachment;
	}

}
