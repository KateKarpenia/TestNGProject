package com.epam.task.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Scanner;

import org.testng.TestNG;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlInclude;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;

import com.epam.task.test.util.BaseConstants;

public class TestRunner
{
	public static void main(String[] args) throws IOException, ClassNotFoundException
	{
		List<SourceTest> sourceTestParams = readParamsFromFile(args[0]);

		for (SourceTest sourceTest : sourceTestParams)
		{
			XmlSuite suite = new XmlSuite();
			suite.setName(BaseConstants.SUIT_NAME);
			sourceToTestNg(suite, sourceTest);
		}
	}

	private static List<SourceTest> readParamsFromFile(String config) throws FileNotFoundException
	{
		Properties properties = getProperties(System.getProperty(config));
		String filePath = properties.getProperty(BaseConstants.FILE_PATH_PROP);
		String delimiter = properties.getProperty(BaseConstants.DELIMITER_PROP);

		File file = new File(filePath);
		Scanner scanner = new Scanner(file);
		List<SourceTest> sourceTestList = new ArrayList<>();

		while (scanner.hasNextLine())
		{
			String data[] = scanner.nextLine().split(delimiter);
			sourceTestList.add(new SourceTest(data[0], data[1], Arrays.asList(data[2], data[3])));
		}
		scanner.close();
		return sourceTestList;
	}

	private static void sourceToTestNg(XmlSuite suite, SourceTest sourceTest) throws ClassNotFoundException
	{
		String className = sourceTest.getName();
		Class<?> testClasss = Class.forName(BaseConstants.CLASS_PATH + className);
		String methodToRun = sourceTest.getSourceMethod();
		List<String> params = sourceTest.getParams();

		XmlTest test = new XmlTest(suite);
		test.setName(BaseConstants.TEST_NAME);

		XmlClass testClass = new XmlClass(testClasss);
		List<XmlInclude> methodsToRun = new ArrayList<>();
		Map<String, String> parameters = new HashMap<>();
		List<XmlClass> classes = new ArrayList<>();

		XmlInclude method = new XmlInclude(methodToRun);
		parameters.put(BaseConstants.PARAM_1, params.get(0));
		parameters.put(BaseConstants.PARAM_2, params.get(1));
		method.setParameters(parameters);
		methodsToRun.add(method);

		testClass.setIncludedMethods(methodsToRun);
		classes.add(testClass);

		test.setXmlClasses(classes);

		TestNG testng = new TestNG();
		List<XmlSuite> suites = new ArrayList<>();
		suites.add(suite);
		testng.setXmlSuites(suites);
		System.out.println(suite.toXml());
		testng.run();
	}

	private static Properties getProperties(String propFile)
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
