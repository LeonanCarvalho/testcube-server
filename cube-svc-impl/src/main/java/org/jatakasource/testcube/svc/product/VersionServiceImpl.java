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
package org.jatakasource.testcube.svc.product;

import java.util.List;

import org.jatakasource.common.dao.BaseDao;
import org.jatakasource.common.model.paging.Paging;
import org.jatakasource.common.model.paging.Sorting;
import org.jatakasource.common.svc.CRUDServiceImpl;
import org.jatakasource.testcube.dao.product.VersionDao;
import org.jatakasource.testcube.model.product.IVersion;
import org.jatakasource.testcube.svc.product.VersionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VersionServiceImpl extends CRUDServiceImpl<IVersion, Long> implements VersionService {

	@Autowired
	private VersionDao versionDao;

	@Override
	public BaseDao<IVersion, Long> getDao() {
		return versionDao;
	}

	@Override
	public List<IVersion> getAll(Paging paging, Sorting sorting, String keyword, Long productId) {
		return versionDao.findAll(paging, sorting, keyword, productId);
	}
	

}
