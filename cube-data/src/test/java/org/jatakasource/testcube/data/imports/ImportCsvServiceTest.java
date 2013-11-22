package org.jatakasource.testcube.data.imports;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.Collection;
import java.util.List;
import java.util.Locale;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.jatakasource.common.data.DataReader;
import org.jatakasource.common.data.dao.DataReaderDao;
import org.jatakasource.common.data.svc.ImportCsvService;
import org.jatakasource.common.utils.JarUtils;
import org.jatakasource.testcube.data.SpringServiceTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

@Transactional
public abstract class ImportCsvServiceTest extends SpringServiceTest {
	private final static Logger logger = Logger.getLogger(ImportCsvServiceTest.class);

	@Autowired
	private ImportCsvService importCSVService;
	@Autowired
	private DataReaderDao domainObjectDao;

	protected <T extends DataReader<?>, TR> void importCsvFile(Class<T> dataReader, Class<TR> innerType, Locale locale) {
		String path = org.jatakasource.common.utils.FileUtils.getDirectoryPath(dataReader);
		path = JarUtils.addLocale(locale, path);

		Assert.notNull(path, "Can't find  package path for Class: " + dataReader.getName());

		if (path.contains(JarUtils.JAR_EXTENSION)) {
			importCSVService.importAll(dataReader, locale);
		} else {
			saveFiles(path, dataReader, innerType);
		}

		List<TR> savedInstances = domainObjectDao.getAll(innerType);
		Assert.notEmpty(savedInstances, "Unable to read elemets from database !!!");
	}

	/**
	 * Save all CSV files in case test was before jar creation.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private <T extends DataReader, TR> void saveFiles(String path, Class<T> dataReader, Class<TR> innerType) {
		File directory = new File(path);
		Assert.isTrue(directory.isDirectory(), "Unable to get directory: " + path);

		Collection<File> resources = FileUtils.listFiles(directory, new String[] { ImportCsvService.CSV_SUFFIX }, false);
		Assert.isTrue(directory.isDirectory(), "Unable to get directory: " + path);
		Assert.notEmpty(resources, "Directory " + directory.getPath() + " Has no elements !!!");

		FileInputStream inStream = null;
		InputStreamReader streamReader = null;

		// Read all CSV files
		for (File resource : resources) {
			try {
				inStream = new FileInputStream(resource);

				try {
					streamReader = new InputStreamReader(inStream, "UTF-8");
				} catch (UnsupportedEncodingException e) {
					logger.error("Fail to read CSV file" + e);
				}

				List<T> imports = importCSVService.readFile(streamReader, dataReader);

				domainObjectDao.saveAll((List<T>) imports);
			} catch (FileNotFoundException e) {
				logger.error("Error getting input stream !!!");
			} finally {
				org.jatakasource.common.utils.FileUtils.closeSilently(inStream);
				org.jatakasource.common.utils.FileUtils.closeSilently(streamReader);
			}
		}
	}
}
