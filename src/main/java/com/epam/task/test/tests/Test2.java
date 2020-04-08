package com.epam.task.test.tests;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.epam.task.test.util.BaseConstants;

public class Test2
{
	@Test
	@Parameters({ BaseConstants.PARAM_1, BaseConstants.PARAM_2})
	public void test2Method1(int param1, int param2)
	{
		System.out.println("Test 2 Method 1 " + param1 + " " + param2);
	}

	@Test
	@Parameters({BaseConstants.PARAM_1, BaseConstants.PARAM_2})
	public void test2Method2(int param1, int param2)
	{
		System.out.println("Test 2 Method 2 " + param1 + " " + param2);
	}
}
