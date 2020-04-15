package com.epam.task.test.loader;

import java.io.FileNotFoundException;
import java.util.List;

import com.epam.task.test.bean.Config;
import com.epam.task.test.bean.SourceTest;
import com.epam.task.test.util.FileReader;
import com.google.inject.Inject;

public class SourceTestLoader implements ITestLoader
{
	@Inject
	private Config config;

	public SourceTestLoader(Config config) {
		this.config = config;
	}

	public List<SourceTest> getSourceTests() throws FileNotFoundException {
		return FileReader.readParamsFromFile(config);
	}
}
