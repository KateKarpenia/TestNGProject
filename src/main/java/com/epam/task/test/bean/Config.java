package com.epam.task.test.bean;

import javax.inject.Named;

import com.google.inject.Inject;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor @EqualsAndHashCode
public class Config
{
	@Inject
	@Named("filePath")
	private String filePath;
	@Inject
	@Named("delimiter")
	private String delimiter;
}
