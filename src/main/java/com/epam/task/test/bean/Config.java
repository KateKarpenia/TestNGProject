package com.epam.task.test.bean;

import java.util.Objects;

public class Config
{
	private String filePath;
	private String delimiter;

	public Config()
	{
	}

	public String getFilePath()
	{
		return filePath;
	}

	public void setFilePath(String filePath)
	{
		this.filePath = filePath;
	}

	public String getDelimiter()
	{
		return delimiter;
	}

	public void setDelimiter(String delimiter)
	{
		this.delimiter = delimiter;
	}

	@Override
	public boolean equals(Object o)
	{
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Config config = (Config) o;
		return Objects.equals(filePath, config.filePath) &&
				Objects.equals(delimiter, config.delimiter);
	}

	@Override
	public int hashCode()
	{

		return Objects.hash(filePath, delimiter);
	}
}
