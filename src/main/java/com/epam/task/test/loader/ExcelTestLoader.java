package com.epam.task.test.loader;

import java.io.IOException;
import java.util.List;

import com.epam.task.test.bean.Config;
import com.epam.task.test.bean.SourceTest;
import com.epam.task.test.util.FileReader;
import com.google.inject.Inject;

public class ExcelTestLoader implements ITestLoader
{
	@Inject
	private Config config;

	public ExcelTestLoader(Config config) {
		this.config = config;
	}

	@Override
	public List<SourceTest> getSourceTests() throws IOException {
		return FileReader.readParamsFromExcelFile(config);
	}

}
