package com.epam.task.test.loader;

import java.io.IOException;
import java.util.List;

import com.epam.task.test.bean.SourceTest;

public interface ITestLoader
{
	List<SourceTest> getSourceTests() throws IOException, IllegalAccessException;
}
