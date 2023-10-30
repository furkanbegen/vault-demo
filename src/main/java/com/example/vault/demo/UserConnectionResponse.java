package com.example.vault.demo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserConnectionResponse {
	
	@JsonProperty("data")
	Data data;
	
	public Data getSecrets() {
		return data;
	}
	
	public void setSecrets(Data data) {
		this.data = data;
	}
}
