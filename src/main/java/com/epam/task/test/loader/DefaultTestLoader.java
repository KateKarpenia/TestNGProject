package com.epam.task.test.loader;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.epam.task.test.bean.Config;
import com.epam.task.test.bean.SourceTest;
import com.epam.task.test.util.TestConfig;
import com.google.inject.Inject;

public class DefaultTestLoader implements ITestLoader
{
	@Inject
	private Config config;

	public DefaultTestLoader(Config config) {
		this.config = config;
	}

	public List<SourceTest> getSourceTests() throws IllegalAccessException {
		return readParamsFromConstants(config);
	}

	private static List<SourceTest> readParamsFromConstants(Config config) throws IllegalAccessException {
		Map<String, String> params = putConstantsToMap();
		List<SourceTest> sourceTestList = new ArrayList<>();

		for (Map.Entry<String, String> entry : params.entrySet())
		{
			String key = entry.getKey();
			if (key.contains("CASE_"))
			{
				String value = entry.getValue();
				if (value != null)
				{
					String[] data = value.split(config.getDelimiter());
					sourceTestList.add(new SourceTest(data[0], data[1], Arrays.asList(data[2], data[3])));
				}
			}
		}
		return sourceTestList;
	}

	private static Map<String, String> putConstantsToMap() throws IllegalAccessException {
		Map<String, String> map = new HashMap<>();
		TestConfig testConfig = new TestConfig();
		Field[] fields = TestConfig.class.getDeclaredFields();
		for (Field field : fields)
		{
			map.put(field.getName(), (String) field.get(testConfig));
		}
		return map;
	}
}
