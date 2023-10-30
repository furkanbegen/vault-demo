package com.example.vault.demo;

import com.example.vault.demo.property.MyConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.vault.authentication.AppRoleAuthentication;
import org.springframework.vault.authentication.AppRoleAuthenticationOptions;
import org.springframework.vault.client.VaultClients;
import org.springframework.vault.client.VaultEndpoint;
import org.springframework.vault.core.VaultTemplate;
import org.springframework.vault.support.VaultResponse;
import org.springframework.vault.support.VaultResponseSupport;
import org.springframework.vault.support.VaultToken;
import org.springframework.web.client.RestOperations;


@SpringBootApplication
public class VaultDemoApplication implements CommandLineRunner {
	
	public static Logger log = LoggerFactory.getLogger(VaultDemoApplication.class);
	
	
	public static final String UUID = "123456";
	// kv engine v2 kullandığım için path'e /data eklemem gerekiyor.
	public static final String FACEBOOK_DATA = "/facebook/data/";
	public static final String SECRET_PATH = FACEBOOK_DATA + UUID;
	
	
	private final MyConfiguration configuration;
	
	public VaultDemoApplication(MyConfiguration myConfiguration) {
		this.configuration = myConfiguration;
	}
	
	
	public static void main(String[] args) {
		SpringApplication.run(VaultDemoApplication.class, args);
		VaultEndpoint endpoint = new VaultEndpoint();
		endpoint.setScheme("http");
		
		var options = AppRoleAuthenticationOptions.builder()
				              .roleId(AppRoleAuthenticationOptions.RoleId.provided("a79d332e-146c-ed9a-ac36-0de8b9f64352"))
				              .secretId(AppRoleAuthenticationOptions.SecretId.pull(VaultToken.of("hvs.CAESIFNHGBy80ecvMheQJ4jdwfuHTUOlU3J2GmLArcgLC7FWGh4KHGh2cy5sZEJDaFIzeE9ZNUVqV3FTVEh2VEtSbFQ")))
//				              .secretId(AppRoleAuthenticationOptions.SecretId.provided("fabacd99-a302-b2a4-484a-4d6d85c173b0"))
				              .appRole("my-role")
				              .build();
		
		HttpComponentsClientHttpRequestFactory clientHttpRequestFactory
				= new HttpComponentsClientHttpRequestFactory();
		clientHttpRequestFactory.setConnectTimeout(5000);
		
		
		RestOperations restOperations = VaultClients.createRestTemplate(endpoint, clientHttpRequestFactory);
		
		AppRoleAuthentication authentication = new AppRoleAuthentication(options, restOperations);
		
		
		VaultTemplate vaultTemplate = new VaultTemplate(endpoint, authentication);
		
		UserConnectionResponse userConnectionResponse = new UserConnectionResponse();
		Data data = new Data();
		data.setAccessToken("cok-gizli-token");
		userConnectionResponse.setSecrets(data);
		VaultResponse write = vaultTemplate.write(FACEBOOK_DATA + "/123123", userConnectionResponse);
		System.out.println("write: " + write.getData());
		
		
		for (int i = 0; i < 100; i++) {
			VaultResponseSupport<UserConnectionResponse> response = vaultTemplate.read(SECRET_PATH, UserConnectionResponse.class);
			System.out.println(String.valueOf(i) + " " + response.getData().getSecrets());
			
		}
		
	}
	
	@Override
	public void run(String... args) throws Exception {
		
		Logger log = LoggerFactory.getLogger(VaultDemoApplication.class);
		log.info("++++++++++++++++++++++++++++++");
		log.info("username: " + configuration.getUsername());
		log.info("password: " + configuration.getPassword());
		
	}
}
