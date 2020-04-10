package com.epam.task.test.util;

import java.util.Properties;

import com.epam.task.test.bean.Config;

public class ConfigLoader
{
	public static Config load()
	{
		Config config = new Config();
		Properties properties = FileReader.getProperties(BaseConstants.PROP_FILE);
		String filePath = properties.getProperty(BaseConstants.FILE_PATH_PROP);
		String delimiter = properties.getProperty(BaseConstants.DELIMITER_PROP);

		config.setFilePath(filePath);
		config.setDelimiter(delimiter);
		return config;
	}
}
