package com.epam.task.test.loader;

import java.io.FileNotFoundException;
import java.util.List;

import com.epam.task.test.bean.Config;
import com.epam.task.test.bean.SourceTest;
import com.google.inject.Inject;

public interface ITestLoader
{
	List<SourceTest> getSourceTests() throws FileNotFoundException, IllegalAccessException;
}
