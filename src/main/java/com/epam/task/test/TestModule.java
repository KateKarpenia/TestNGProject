package com.epam.task.test;

import java.util.Properties;

import com.epam.task.test.loader.ITestLoader;
import com.epam.task.test.loader.TestLoaderProvider;
import com.epam.task.test.util.BaseConstants;
import com.epam.task.test.util.FileReader;
import com.google.inject.AbstractModule;
import com.google.inject.name.Names;

public class TestModule extends AbstractModule
{
	@Override
	protected void configure() {
		Properties properties = FileReader.getProperties(BaseConstants.PROP_FILE);
		Names.bindProperties(binder(), properties);
		bind(ITestLoader.class).toProvider(TestLoaderProvider.class);
	}
}
