package com.epam.task.test.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

import com.epam.task.test.bean.Config;
import com.epam.task.test.bean.SourceTest;

public class FileReader
{
	public static List<SourceTest> readParamsFromFile(Config config) throws FileNotFoundException
	{
		String filePath = config.getFilePath();
		String delimiter = config.getDelimiter();

		File file = new File(filePath);
		Scanner scanner = new Scanner(file);
		List<SourceTest> sourceTestList = new ArrayList<>();

		while (scanner.hasNextLine())
		{
			String[] data = scanner.nextLine().split(delimiter);
			sourceTestList.add(new SourceTest(data[0], data[1], Arrays.asList(data[2], data[3])));
		}
		scanner.close();
		return sourceTestList;
	}

	public static Properties getProperties(String propFile)
	{
		File file = new File(propFile);
		Properties properties = new Properties();
		try (InputStream inputStream = new FileInputStream(file.getAbsolutePath()))
		{
			properties.load(inputStream);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		return properties;
	}

}
