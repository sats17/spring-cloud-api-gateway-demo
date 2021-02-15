package com.github.sats17.test;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
public abstract class Raw implements interfaceTesting {

	@Override
	public int test(int a) {
		return testResponse(a);
	}
	
	@Override
	public int minus(int a) {
		return 50 - a;
	}
	
	@Override
	public int aggregate(int a) {
		return testResponse(a);
	}
	
	protected abstract int testResponse(int a);

}
