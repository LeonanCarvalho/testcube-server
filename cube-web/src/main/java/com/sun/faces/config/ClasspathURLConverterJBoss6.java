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
package com.sun.faces.config;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Vector;
import java.util.jar.JarEntry;

import org.apache.log4j.Logger;

/**
 * PATCH TO support jboss 6/6.1 vfs
 */
public class ClasspathURLConverterJBoss6 {
	private static Logger logger = Logger.getLogger(ClasspathURLConverterJBoss6.class);

	public static String getVirtualPath(URL url) {
		if (url != null && url.getProtocol().startsWith("vfs")) {
			// supports virtual filesystem used by JBoss 5/6.x
			try {
				URLConnection connection = (URLConnection) url.openConnection();
				Object virtualFile = invokerGetter(connection, "getContent");
				Object physicalFile = invokerGetter(virtualFile, "getPhysicalFile");

				return ((File) physicalFile).getAbsolutePath();

			} catch (Exception e) {
				logger.info(e.getCause().toString());
			}
		}
		throw new UnsupportedOperationException("ClasspathURLConverterJBoss6 Support only VSF protocol !!!");
	}

	public static List<JarEntry> geJarEntries(String path) throws MalformedURLException {
		URL url = new URL(path);
		Vector<JarEntry> entries = new Vector<JarEntry>();

		if (url != null && url.getProtocol().startsWith("vfs")) {
			// supports virtual filesystem used by JBoss 5/6.x
			try {
				URLConnection connection = (URLConnection) url.openConnection();
				Object virtualFile = invokerGetter(connection, "getContent");
				Object stream = invokerGetter(virtualFile, "openStream");

				Object nextJarEntry = invokerGetter(stream, "getNextJarEntry");
				while (nextJarEntry != null) {
					entries.add((JarEntry) nextJarEntry);
					nextJarEntry = invokerGetter(stream, "getNextJarEntry");
				}

				return entries;
			} catch (Exception e) {
				logger.info(e.getCause().toString());
			}
		}

		throw new UnsupportedOperationException("ClasspathURLConverterJBoss6 Support only VSF protocol !!!");
	}

	private static Object invokerGetter(Object target, String getter) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
		Class<?> type = target.getClass();
		Method method;
		try {
			method = type.getMethod(getter);
		} catch (NoSuchMethodException e) {
			method = type.getDeclaredMethod(getter);
		}

		method.setAccessible(true);
		return method.invoke(target);
	}
}
