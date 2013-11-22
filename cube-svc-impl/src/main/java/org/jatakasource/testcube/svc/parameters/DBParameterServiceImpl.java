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
package org.jatakasource.testcube.svc.parameters;

import org.apache.log4j.Logger;
import org.jatakasource.common.dao.BaseDao;
import org.jatakasource.common.model.parameters.IDBParameter;
import org.jatakasource.common.svc.CRUDServiceImpl;
import org.jatakasource.testcube.dao.parameters.DBParameterDao;
import org.jatakasource.testcube.svc.parameters.DBParameterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class DBParameterServiceImpl extends CRUDServiceImpl<IDBParameter, String> implements DBParameterService {
	private final static Logger logger = Logger.getLogger(DBParameterServiceImpl.class);

	@Autowired
	private DBParameterDao parameterDao;

	@Override
	public BaseDao<IDBParameter, String> getDao() {
		return parameterDao;
	}

	@Override
	public Boolean getBoolean(String key, Boolean defaultValue) {
		return Boolean.valueOf(get(key, defaultValue.toString()));
	}

	@Override
	public void setBoolean(String key, Boolean value) {
		get(key).setValue(value.toString());
	}

	private String get(String key, String defaultValue) {
		IDBParameter parameter = parameterDao.getById(key);

		if (parameter == null) {
			logger.debug("Unable to find property: " + key + " In database !!!");
			return defaultValue;
		}

		return parameter.getValue();
	}

	@Override
	public IDBParameter update(IDBParameter pojo) {
		super.clearCache();

		return super.update(pojo);
	}

	@Override
	public IDBParameter saveOrUpdate(IDBParameter pojo) {
		super.clearCache();

		return super.saveOrUpdate(pojo);
	}
}
