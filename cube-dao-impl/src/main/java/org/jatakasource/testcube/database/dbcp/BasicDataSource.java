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
package org.jatakasource.testcube.database.dbcp;

import java.sql.Connection;
import java.sql.SQLException;

import javax.annotation.PostConstruct;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.jatakasource.common.thread.ThreadUtils;

/**
 * Extending class of the connection pool manager in order to monitor pooling
 * exceptions
 */
public class BasicDataSource extends org.apache.commons.dbcp.BasicDataSource {

	private static final Logger logger = Logger.getLogger(BasicDataSource.class);

	// Default wait is 5 minutes
	private static final int MAX_RETRY = 60;
	private static final int SLEEP_TIME_MILL = 5000; // 5 seconds

	private volatile boolean hadError = false;

	private int maxRetry = MAX_RETRY;
	private int sleepTimeMill = SLEEP_TIME_MILL;

	@PostConstruct
	protected void waitToDB() {
		// Disable exception logging
		hadError = true;

		waitToDB(0);

		// Enable exception logging
		hadError = false;
	}

	/**
	 * @param counter
	 */
	private void waitToDB(int counter) {
		logger.trace("Checking database connectivity !!!");

		Connection connection = null;

		try {
			connection = getConnection();
		} catch (Throwable e) {
			logger.debug(e);
		}

		if (connection == null) {
			if (counter >= maxRetry) {
				logger.error("Unable to establish database (" + getDatabaseVendor() + ") connection !!! maxRetry: " + maxRetry + " counter: " + counter);
				return;
			}

			logger.info("Unable to get database connection " + getDBDetails() + ", sleeping for " + sleepTimeMill + " milliseconds ...");
			ThreadUtils.sleepSilently(sleepTimeMill);
			logger.info("Retry to get database connection (" + counter + " of " + maxRetry + ")");

			waitToDB(++counter);
		}
	}

	private String getDBDetails() {
		return "(" + super.getUrl() + ")";
	}

	@Override
	public Connection getConnection() throws SQLException {
		try {
			Connection connection = super.getConnection();
			if (connection.isReadOnly()) {
				logger.warn("About to provide a read-only connection!");
			}
			if (connection.isClosed()) {
				logger.warn("About to provide a closed connection!");
			}

			hadError = false;

			return connection;
		} catch (RuntimeException e) {
			logError("Error getting a connetion. Status-> " + getStatus(), e);
			throw e;
		} catch (SQLException e) {
			logError("Error getting a connetion. Status-> " + getStatus(), e);
			throw e;
		}
	}

	private void logError(String errorMsg, Throwable t) {
		logger.error(errorMsg + " Exception Message: " + t.getMessage() + " Exception Cause: " + t.getCause());

		if (!hadError) {
			logger.debug(t);

			hadError = true;
		}
	}

	private String getStatus() {
		StringBuilder sb = new StringBuilder();
		sb.append("Connections: ");
		sb.append(getNumActive());
		sb.append(" active, ");
		sb.append(getNumIdle());
		sb.append(" idle.");
		return sb.toString();
	}

	/**
	 * Just for debug output messages.
	 */
	private String getDatabaseVendor() {
		if (StringUtils.isEmpty(getDriverClassName())) {
			return StringUtils.EMPTY;
		}

		if (getDriverClassName().toLowerCase().contains("db2")) {
			return "DB2";
		} else if (getDriverClassName().toLowerCase().contains("mysql")) {
			return "MySQL";
		} else if (getDriverClassName().toLowerCase().contains("oracle")) {
			return "Oracle";
		}

		return StringUtils.EMPTY;
	}

	public int getMaxRetry() {
		return maxRetry;
	}

	public void setMaxRetry(int maxRetry) {
		this.maxRetry = maxRetry;
	}

	public int getSleepTimeMill() {
		return sleepTimeMill;
	}

	public void setSleepTimeMill(int sleepTimeMill) {
		this.sleepTimeMill = sleepTimeMill;
	}

}
