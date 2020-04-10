package com.epam.task.test.util;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import org.testng.xml.XmlSuite;

import com.epam.task.test.bean.Config;
import com.epam.task.test.bean.SourceTest;

public class SourceTestLoader
{
	public List<XmlSuite> getSourceTests(Config config) throws ClassNotFoundException, FileNotFoundException
	{
		List<SourceTest> sourceTestParams = getParamsFromConfig(config);

		List<XmlSuite> suitesList = new ArrayList<>();
		TestNGConfigurator testNGConfigurator = new TestNGConfigurator();
		for (SourceTest sourceTest : sourceTestParams)
		{
			XmlSuite suite = new XmlSuite();
			suite.setName(BaseConstants.SUIT_NAME + Utilities.getRandomName(4));
			List<XmlSuite> suites = testNGConfigurator.getSuites(suite, sourceTest);
			suitesList.addAll(suites);
		}
		return suitesList;
	}

	private List<SourceTest> getParamsFromConfig(Config config) throws FileNotFoundException
	{
		return FileReader.readParamsFromFile(config);
	}

}
