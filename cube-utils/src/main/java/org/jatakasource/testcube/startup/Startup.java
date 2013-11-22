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
package org.jatakasource.testcube.startup;

import org.jatakasource.common.startup.CommonStartup;

/**
 * Default Startup properties.
 */
public abstract class Startup extends CommonStartup{
	private static final String TESTCUBE = "testcube";

	// Default sys.properties DIR Without relevant instance
	private static final String ENVDIR_FOLDER = FS + "etc" + FS + JATAKASOURCE + FS + TESTCUBE;
	public static final String SERVER_ENVDIR_FOLDER = ENVDIR_FOLDER + FS + SERVER;

	private static final String RUNDIR_FOLDER = FS + "var" + FS + "run" + FS +  JATAKASOURCE + FS + TESTCUBE;
	public static final String SERVER_RUNDIR_FOLDER = RUNDIR_FOLDER + FS + SERVER;
	
	private static final String LIB_FOLDER = FS + "var" + FS + "lib" + FS +  JATAKASOURCE + FS + TESTCUBE;
	public static final String SERVER_LIB_FOLDER = LIB_FOLDER + FS + SERVER;
	
	private static final String SHARE_FOLDER = FS + "usr" + FS + "share" + FS + JATAKASOURCE + FS + TESTCUBE;
	public static final String SERVER_SHARE_FOLDER = SHARE_FOLDER + FS + SERVER;
}
