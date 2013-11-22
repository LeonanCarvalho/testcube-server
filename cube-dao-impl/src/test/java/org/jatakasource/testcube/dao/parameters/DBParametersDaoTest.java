package org.jatakasource.testcube.dao.parameters;

import org.jatakasource.common.model.parameters.IDBParameter;
import org.jatakasource.testcube.dao.SpringDaoTest;
import org.jatakasource.testcube.model.parameters.DBParameter;
import org.springframework.beans.factory.annotation.Autowired;

public class DBParametersDaoTest extends SpringDaoTest<String, DBParameterDao, IDBParameter> {
	@Autowired
	private DBParameterDao dbParameterDao;

	@Override
	public void change(IDBParameter parameter) {
		parameter.setValue("TEST_PARAMETER");
	}

	@Override
	public DBParameterDao getDao() {
		return dbParameterDao;
	}

	@Override
	public IDBParameter[] getInvalidEntities() {
		IDBParameter invalidParameter = getValidEntity();
		invalidParameter.setId(null);
		invalidParameter.setValue(null);
		return new IDBParameter[] { invalidParameter };
	}

	@Override
	public IDBParameter[] getValidEntities() {
		DBParameter dbParameter = new DBParameter();
		dbParameter.setId("TEST_PARAMETER");
		dbParameter.setValue("TEST_PARAMETER_VALUE");
		dbParameter.setValidatorClass("TEST_PARAMETER_VALIDATOR");
		dbParameter.setDescription("TEST_PARAMETER_VALIDATOR");
		dbParameter.setUpdateble(true);

		return new IDBParameter[] { dbParameter };
	}
}