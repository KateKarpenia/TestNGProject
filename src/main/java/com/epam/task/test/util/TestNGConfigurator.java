package com.epam.task.test.util;

import java.util.List;

import org.testng.TestNG;
import org.testng.xml.XmlSuite;

public class TestNGConfigurator
{
	public static TestNG configure(List<XmlSuite> suites)
	{
		TestNG testng = new TestNG();
		testng.setXmlSuites(suites);
		return testng;
	}
}
