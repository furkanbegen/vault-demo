package com.example.vault.demo.property;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyConfiguration {
 
	/*
	Değerler vault'ta secret/gs-vault-config isimli kv engine'de duruyor
	application.properties'deki spring.application.name değeri secret/ içindeki değer ile aynı
	 */
	
	
	@Value("${example.username}")
	private String username;
	
	@Value("${example.password}")
	private String password;
	
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
}
