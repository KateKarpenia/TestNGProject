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

	public static TestNG configure(List<SourceTest> sourceTests) {
		List<XmlSuite> suitesList = new ArrayList<>();
		sourceTests.forEach(sourceTest -> {
			XmlSuite suite = new XmlSuite();
			suite.setName(BaseConstants.SUIT_NAME + Utilities.getRandomName(4));
			getSuites(suite, sourceTest);
			suitesList.add(suite);
		});
		TestNG testng = new TestNG();
		testng.setXmlSuites(suitesList);
		return testng;
	}

	private static void getSuites(XmlSuite suite, SourceTest sourceTest) {
		List<XmlInclude> methodsToRun = configureMethods(sourceTest);
		List<XmlClass> classes = null;
		try
		{
			classes = configureClasses(sourceTest, methodsToRun);
		}
		catch (ClassNotFoundException e)
		{
			logger.debug("Class was not found " + e);
		}
		configureTest(suite, classes);
		logger.info(suite.toXml());
	}

	private static List<XmlInclude> configureMethods(SourceTest sourceTest) {
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

	private static List<XmlClass> configureClasses(SourceTest sourceTest, List<XmlInclude> methodsToRun)
			throws ClassNotFoundException {
		String className = sourceTest.getName();
		Class<?> testClasss = Class.forName(BaseConstants.CLASS_PATH + className);
		XmlClass testClass = new XmlClass(testClasss);
		List<XmlClass> classes = new ArrayList<>();
		testClass.setIncludedMethods(methodsToRun);
		classes.add(testClass);
		return classes;
	}

	private static void configureTest(XmlSuite suite, List<XmlClass> classes) {
		XmlTest test = new XmlTest(suite);
		test.setName(BaseConstants.TEST_NAME + Utilities.getRandomName(4));
		test.setXmlClasses(classes);
	}
}
