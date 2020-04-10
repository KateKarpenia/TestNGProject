package com.epam.task.test.util;

import java.io.FileNotFoundException;
import java.util.List;

import com.epam.task.test.bean.Config;
import com.epam.task.test.bean.SourceTest;

public class SourceTestLoader
{
	public List<SourceTest> getSourceTests(Config config) throws FileNotFoundException
	{
		return FileReader.readParamsFromFile(config);
	}
}
