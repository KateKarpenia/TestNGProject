package com.epam.task.test;

import java.io.IOException;
import java.util.List;

import org.testng.TestNG;

import com.epam.task.test.bean.Config;
import com.epam.task.test.bean.SourceTest;
import com.epam.task.test.loader.ITestLoader;
import com.epam.task.test.util.TestNGConfigurator;
import com.google.inject.Guice;
import com.google.inject.Injector;

public class TestRunner
{
	public static void main(String[] args) throws IOException, ClassNotFoundException, IllegalAccessException {
		Injector injector = Guice.createInjector(new TestModule());
		ITestLoader testLoader = injector.getInstance(ITestLoader.class);
		List<SourceTest> sourceTests = testLoader.getSourceTests();

		TestNG testNG = TestNGConfigurator.configure(sourceTests);
		testNG.run();
	}
}
