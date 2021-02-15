package com.github.sats17.test;

import java.util.HashMap;
import java.util.Map;

public class DefaultRaw extends Raw {

	@Override
	protected int testResponse(int a) {
		return a + 20;
	}

	@Override
	public String plus(int a) {
		// TODO Auto-generated method stub
		return null;
	}

//	@Override
//	public String plus(int a) {
//		// TODO Auto-generated method stub
//		return null;
//	}

}
