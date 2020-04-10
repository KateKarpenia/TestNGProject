package com.epam.task.test.bean;

import java.util.List;
import java.util.Objects;

public class SourceTest
{
	private String name;
	private String sourceMethod;
	private List<String> params;

	public SourceTest(String name, String sourceMethod, List<String> params)
	{
		this.name = name;
		this.sourceMethod = sourceMethod;
		this.params = params;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getSourceMethod()
	{
		return sourceMethod;
	}

	public void setSourceMethod(String sourceMethod)
	{
		this.sourceMethod = sourceMethod;
	}

	public List<String> getParams()
	{
		return params;
	}

	public void setParams(List<String> params)
	{
		this.params = params;
	}

	@Override
	public boolean equals(Object o)
	{
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		SourceTest that = (SourceTest) o;
		return Objects.equals(name, that.name) &&
				Objects.equals(sourceMethod, that.sourceMethod) &&
				Objects.equals(params, that.params);
	}

	@Override
	public int hashCode()
	{

		return Objects.hash(name, sourceMethod, params);
	}

	@Override
	public String toString()
	{
		return "SourceTest{" +
				"name='" + name + '\'' +
				", sourceMethod='" + sourceMethod + '\'' +
				", params=" + params +
				'}';
	}
}
