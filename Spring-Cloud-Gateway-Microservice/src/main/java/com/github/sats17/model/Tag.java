package com.github.sats17.model;

import java.util.List;

public class Tag<E> {
	
	private String status;
	private List<E> response;
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public List<E> getResponse() {
		return response;
	}
	public void setResponse(List<E> response) {
		this.response = response;
	}
	
	
	
}
