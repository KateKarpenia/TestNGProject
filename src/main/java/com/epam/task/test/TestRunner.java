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
import com.epam.task.test.util.FileReader;
import com.epam.task.test.util.TestNGConfigurator;

public class TestRunner
{
	public static void main(String[] args) throws IOException, ClassNotFoundException
	{
		TestNGConfigurator testNGConfigurator = new TestNGConfigurator(BaseConstants.CONFIG);
		List<SourceTest> sourceTestParams = testNGConfigurator.getParamsFromConfig();
		List<XmlSuite> suites = testNGConfigurator.setSourceTest(sourceTestParams);
		TestNG testNG = testNGConfigurator.configure(suites);
		testNG.run();
	}
}
