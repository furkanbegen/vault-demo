package com.example.vault.demo;


public class Data {
	
	String accessToken;
	String refreshToken;
	
	public String getAccessToken() {
		return accessToken;
	}
	
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	
	public String getRefreshToken() {
		return refreshToken;
	}
	
	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}
	
	@Override
	public String toString() {
		return "Secrets{" +
				       "accessToken='" + accessToken + '\'' +
				       ", refreshToken='" + refreshToken + '\'' +
				       '}';
	}
}
