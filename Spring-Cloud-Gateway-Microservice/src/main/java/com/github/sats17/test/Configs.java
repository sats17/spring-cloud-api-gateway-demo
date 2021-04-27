package com.github.sats17.test;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties
public class Configs {

	private String aws;

	public String getAws() {
		return aws;
	}

	public void setAws(String aws) {
		this.aws = aws;
	}
	
	
	
}
