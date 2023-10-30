package com.example.vault.demo;

import com.example.vault.demo.property.MyConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class VaultDemoApplication implements CommandLineRunner{

	private final MyConfiguration configuration;
	
	public VaultDemoApplication(MyConfiguration myConfiguration) {
		this.configuration = myConfiguration;
	}
	
	
	public static void main(String[] args) {
		SpringApplication.run(VaultDemoApplication.class, args);

	}
	
	@Override
	public void run(String... args) throws Exception {
		
		Logger log = LoggerFactory.getLogger(VaultDemoApplication.class);
		log.info("++++++++++++++++++++++++++++++");
		log.info("username: " + configuration.getUsername());
		log.info("password: " + configuration.getPassword());
		
	}
}
