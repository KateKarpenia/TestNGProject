package com.epam.task.test;

import java.io.IOException;
import java.util.List;

import org.testng.TestNG;

import com.epam.task.test.bean.SourceTest;
import com.epam.task.test.util.ConfigLoader;
import com.epam.task.test.util.SourceTestLoader;
import com.epam.task.test.util.TestNGConfigurator;

public class TestRunner
{
	public static void main(String[] args) throws IOException, ClassNotFoundException
	{
		SourceTestLoader sourceTestLoader = new SourceTestLoader();
		List<SourceTest> sourceTests = sourceTestLoader.getSourceTests(ConfigLoader.load());
		TestNG testNG = TestNGConfigurator.configure(sourceTests);
		testNG.run();
	}
}
