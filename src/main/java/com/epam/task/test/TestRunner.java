package com.epam.task.test;

import java.io.IOException;
import java.util.List;

import org.testng.TestNG;

import com.epam.task.test.bean.Config;
import com.epam.task.test.bean.SourceTest;
import com.epam.task.test.util.ConfigLoader;
import com.epam.task.test.util.SourceTestLoader;
import com.epam.task.test.util.TestNGConfigurator;
import com.google.inject.Guice;
import com.google.inject.Injector;

public class TestRunner
{
	public static void main(String[] args) throws IOException, ClassNotFoundException
	{
		Injector injector = Guice.createInjector(new TestModule());
		Config config = injector.getInstance(Config.class);
		SourceTestLoader sourceTestLoader = new SourceTestLoader();
		List<SourceTest> sourceTests = sourceTestLoader.getSourceTests(config);
		TestNG testNG = TestNGConfigurator.configure(sourceTests);
		testNG.run();
	}
}
