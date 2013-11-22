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
package org.jatakasource.testcube.svc.testrun;

import org.jatakasource.common.dao.BaseDao;
import org.jatakasource.common.svc.CRUDServiceImpl;
import org.jatakasource.testcube.dao.testrun.RunStatusDao;
import org.jatakasource.testcube.model.testrun.IRunStatus;
import org.jatakasource.testcube.model.testrun.RunStatusEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RunStatusServiceImpl extends CRUDServiceImpl<IRunStatus, Long> implements RunStatusService {

	@Autowired
	private RunStatusDao runStatusDao;

	@Override
	public BaseDao<IRunStatus, Long> getDao() {
		return runStatusDao;
	}
	
	@Override
	public IRunStatus get(RunStatusEnum status) {
		return runStatusDao.find(status);
	}

}
