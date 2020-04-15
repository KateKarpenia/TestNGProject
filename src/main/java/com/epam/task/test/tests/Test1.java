package com.epam.task.test.tests;

import org.testng.annotations.Guice;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.epam.task.test.TestModule;
import com.epam.task.test.bean.Config;
import com.epam.task.test.util.BaseConstants;
import com.google.inject.Inject;

@Guice(modules = TestModule.class)
public class Test1
{
	@Inject
	private Config config;

	@Test
	@Parameters({ BaseConstants.PARAM_1, BaseConstants.PARAM_2 })
	public void test1Method1(int param1, int param2) {
		System.out.println("Test 1 Method 1 " + param1 + config.getDelimiter() + " " + param2);
	}

	@Test
	@Parameters({ BaseConstants.PARAM_1, BaseConstants.PARAM_2 })
	public void test1Method2(int param1, int param2) {
		System.out.println("Test 1 Method 2 " + param1 + config.getDelimiter() + " " + param2);
	}
}
