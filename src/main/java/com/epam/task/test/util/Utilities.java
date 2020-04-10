package com.epam.task.test.util;

import static org.apache.commons.lang3.StringUtils.capitalize;

import org.apache.commons.lang3.RandomStringUtils;

public class Utilities
{
	public static String getRandomName(int length)
	{
		return capitalize(RandomStringUtils.randomAlphanumeric(length));
	}
}
