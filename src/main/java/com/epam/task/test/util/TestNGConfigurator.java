package com.epam.task.test.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.testng.TestNG;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlInclude;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;

import com.epam.task.test.bean.SourceTest;

public class TestNGConfigurator
{
	private static final Logger logger = LogManager.getLogger(TestNGConfigurator.class);

	public static TestNG configure(List<SourceTest> sourceTests) throws ClassNotFoundException
	{
		List<XmlSuite> suitesList = new ArrayList<>();
		TestNGConfigurator testNGConfigurator = new TestNGConfigurator();
		for (SourceTest sourceTest : sourceTests)
		{
			XmlSuite suite = new XmlSuite();
			suite.setName(BaseConstants.SUIT_NAME + Utilities.getRandomName(4));
			List<XmlSuite> suites = testNGConfigurator.getSuites(suite, sourceTest);
			suitesList.addAll(suites);
		}
		TestNG testng = new TestNG();
		testng.setXmlSuites(suitesList);
		return testng;
	}

	private List<XmlSuite> getSuites(XmlSuite suite, SourceTest sourceTest) throws ClassNotFoundException
	{
		List<XmlSuite> suites = new ArrayList<>();
		List<XmlInclude> methodsToRun;
		List<XmlClass> classes;

		methodsToRun = configureMethods(sourceTest);
		classes = configureClasses(sourceTest, methodsToRun);
		configureTest(suite, classes);
		suites.add(suite);
		logger.info(suite.toXml());
		return suites;
	}

	private List<XmlInclude> configureMethods(SourceTest sourceTest)
	{
		String methodToRun = sourceTest.getSourceMethod();
		List<String> params = sourceTest.getParams();

		XmlInclude method = new XmlInclude(methodToRun);
		Map<String, String> parameters = new HashMap<>();
		parameters.put(BaseConstants.PARAM_1, params.get(0));
		parameters.put(BaseConstants.PARAM_2, params.get(1));
		method.setParameters(parameters);

		List<XmlInclude> methodsToRun = new ArrayList<>();
		methodsToRun.add(method);
		return methodsToRun;
	}

	private List<XmlClass> configureClasses(SourceTest sourceTest, List<XmlInclude> methodsToRun)
			throws ClassNotFoundException
	{
		String className = sourceTest.getName();
		Class<?> testClasss = Class.forName(BaseConstants.CLASS_PATH + className);
		XmlClass testClass = new XmlClass(testClasss);
		List<XmlClass> classes = new ArrayList<>();
		testClass.setIncludedMethods(methodsToRun);
		classes.add(testClass);
		return classes;
	}

	private void configureTest(XmlSuite suite, List<XmlClass> classes)
	{
		XmlTest test = new XmlTest(suite);
		test.setName(BaseConstants.TEST_NAME + Utilities.getRandomName(4));
		test.setXmlClasses(classes);
	}
}
