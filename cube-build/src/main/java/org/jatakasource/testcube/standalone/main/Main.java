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
package org.jatakasource.testcube.standalone.main;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.xml.XmlConfiguration;

/**
 * Manually initialize and starts Jetty Web Server. NOTE: No logger here, since it needs to be initialized after.
 */
public class Main {
	static {
		findJettyHome();
		
		// Initialize ipv4 pref, unless previously configured otherwise
		String ipv4 = System.getProperty("java.net.preferIPv4Stack");
		if (ipv4 == null) {
			System.setProperty("java.net.preferIPv4Stack", "true");
		}
	}

	/**
	 * Main function, starts the jetty server. The first parameter can be the jetty.xml configuration file.
	 * 
	 * @param args
	 *            Alternative location of jetty configuration file
	 */
	public static void main(String[] args) {
		Server server = null;
		try {
			URL configUrl;

			// Jetty configuration file is the only param allowed
			if (args != null && args.length != 1) {
				System.err.println("Usage java " + Main.class.getName() + " [URL path to jetty xml conf]");
				System.exit(1);
			}

			String jettyXmlUrl = args[0];
			configUrl = getConfigUrl(jettyXmlUrl);

			XmlConfiguration xmlConfiguration = new XmlConfiguration(configUrl);
			server = new Server();
			xmlConfiguration.configure(server);

			// Start Jetty
			server.start();
			server.join();
		} catch (Exception e) {
			System.err.println("Could not start the Jetty server: " + e);
			e.printStackTrace();
			if (server != null) {
				try {
					server.stop();
				} catch (Exception e1) {
					System.err.println("Unable to stop the jetty server: " + e1);
				}
			}
		}
	}

	private static URL getConfigUrl(String jettyXmlUrl) throws MalformedURLException {
		File jettyConfFile = new File(jettyXmlUrl);
		return getConfigUrl(jettyConfFile, jettyXmlUrl);
	}

	private static URL getConfigUrl(File jettyConfFile, String jettyXmlUrl) throws MalformedURLException {
		URL configUrl;
		if (jettyConfFile.exists() && jettyConfFile.isFile()) {
			System.out.println("Starting jetty from configuration file " + jettyConfFile.getAbsolutePath());
			configUrl = new URL("file:" + jettyConfFile.toURI().getPath());
		} else if (jettyXmlUrl != null) {
			System.out.println("Starting jetty from URL configuration " + jettyXmlUrl);
			configUrl = new URL(jettyXmlUrl);
		} else {
			System.err.println("No jetty configuration file found at " + jettyConfFile.getAbsolutePath());
			System.exit(1);
			return null;
		}
		return configUrl;
	}

	private static String findJettyHome() {
		String home = System.getProperty("jetty.home");
		if (home == null) {
			home = System.getenv("JETTY_HOME");
			if (home == null) {
				System.err.println("No jetty home found ! please add -Djetty.home to your excution command.");
				System.exit(1);
				return null;
			}
		}
		return home.replace('\\', '/');
	}
}