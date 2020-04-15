package com.epam.task.test.tests;

import org.testng.annotations.Guice;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.epam.task.test.TestModule;
import com.epam.task.test.bean.Config;
import com.epam.task.test.util.BaseConstants;
import com.google.inject.Inject;

@Guice(modules = TestModule.class)
public class Test2
{
	@Inject
	Config config;

	@Test
	@Parameters({ BaseConstants.PARAM_1, BaseConstants.PARAM_2 })
	public void test2Method1(int param1, int param2) {
		System.out.println(
				"Test 2 Method 1 " + param1 + " " + param2 + ". " + "From source file: " + config.getFilePath());
	}

	@Test
	@Parameters({ BaseConstants.PARAM_1, BaseConstants.PARAM_2 })
	public void test2Method2(int param1, int param2) {
		System.out.println(
				"Test 2 Method 2 " + param1 + " " + param2 + ". " + "From source file: " + config.getFilePath());
	}
}
