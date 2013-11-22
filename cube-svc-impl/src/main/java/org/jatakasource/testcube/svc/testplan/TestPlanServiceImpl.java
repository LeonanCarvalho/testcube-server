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
package org.jatakasource.testcube.svc.testplan;

import java.util.List;

import org.jatakasource.common.dao.BaseDao;
import org.jatakasource.common.model.paging.Paging;
import org.jatakasource.common.model.paging.Sorting;
import org.jatakasource.common.svc.CRUDServiceImpl;
import org.jatakasource.testcube.dao.testplan.TestPlanDao;
import org.jatakasource.testcube.model.testplan.ITestPlan;
import org.jatakasource.testcube.svc.testplan.TestPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestPlanServiceImpl extends CRUDServiceImpl<ITestPlan, Long> implements TestPlanService {

	@Autowired
	private TestPlanDao testPlanDao;

	@Override
	public BaseDao<ITestPlan, Long> getDao() {
		return testPlanDao;
	}
	
	@Override
	public List<ITestPlan> getAll(Paging paging, Sorting sorting, String keyword, Long productId) {
		return testPlanDao.findAll(paging, sorting, keyword, productId);
	}

}
