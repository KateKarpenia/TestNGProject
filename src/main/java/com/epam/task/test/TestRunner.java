package com.epam.task.test;

import java.io.IOException;
import java.util.List;

import org.testng.TestNG;
import org.testng.xml.XmlSuite;

import com.epam.task.test.util.ConfigLoader;
import com.epam.task.test.util.SourceTestLoader;
import com.epam.task.test.util.TestNGConfigurator;

public class TestRunner
{
	public static void main(String[] args) throws IOException, ClassNotFoundException
	{
		SourceTestLoader sourceTestLoader = new SourceTestLoader(ConfigLoader.load());
		List<XmlSuite> suites = sourceTestLoader.setSourceTest();
		TestNG testNG = TestNGConfigurator.configure(suites);
		testNG.run();
	}
}
