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
package org.jatakasource.testcube.data.svc;

import java.util.Locale;

import org.jatakasource.common.data.svc.ImportCsvService;
import org.jatakasource.common.data.svc.InitializeDatabaseService;
import org.jatakasource.testcube.data.system.parameters.DBParameterReader;
import org.jatakasource.testcube.data.system.testcase.casepriority.CasePriorityReader;
import org.jatakasource.testcube.data.system.testcase.casestatus.CaseStatusReader;
import org.jatakasource.testcube.data.system.testcase.runstatus.RunStatusReader;
import org.jatakasource.testcube.data.system.testplan.plantype.PlanTypeReader;
import org.jatakasource.testcube.data.system.users.UserReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class InitializeDatabaseServiceImpl implements InitializeDatabaseService {

	@Autowired
	private ImportCsvService importCsvFile;

	public void initialize(Locale locale) {
		importCsvFile.importAll(UserReader.class, locale);
		importCsvFile.importAll(DBParameterReader.class, locale);
		importCsvFile.importAll(CaseStatusReader.class, locale);
		importCsvFile.importAll(CasePriorityReader.class, locale);
		importCsvFile.importAll(RunStatusReader.class, locale);
		importCsvFile.importAll(PlanTypeReader.class, locale);
	}

}
