package com.store.greenShoes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootVersion;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityFilterAutoConfiguration;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMethod;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class, SecurityFilterAutoConfiguration.class
		// Add ManagementWebSecurityAutoConfiguration.class if using Actuator security
})
@CrossOrigin(origins="http://localhost:3000",methods = {RequestMethod.DELETE,RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT})
public class GreenShoeStoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(GreenShoeStoreApplication.class, args);
		System.out.print("HI");
		System.out.println("Spring Boot version: " + SpringBootVersion.getVersion());

	}
}
