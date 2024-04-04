package com.store.greenShoes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootVersion;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityFilterAutoConfiguration;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class, SecurityFilterAutoConfiguration.class
		// Add ManagementWebSecurityAutoConfiguration.class if using Actuator security
})
public class GreenShoeStoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(GreenShoeStoreApplication.class, args);
		System.out.print("HI");
		System.out.println("Spring Boot version: " + SpringBootVersion.getVersion());

	}
}
