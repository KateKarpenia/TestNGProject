package com.epam.task.test.loader;

import com.epam.task.test.bean.Config;
import com.epam.task.test.util.BaseConstants;
import com.google.inject.Inject;
import com.google.inject.Provider;

public class TestLoaderProvider implements Provider<ITestLoader>
{
	@Inject
	private Config config;

	@Override
	public ITestLoader get() {
		String sourcePath = config.getFilePath();
		if (sourcePath.endsWith(BaseConstants.FILE_EXTENTION))
		{
			return new SourceTestLoader(config);
		}
		return new DefaultTestLoader(config);
	}
}
