package com.store.greenShoes;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootVersion;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityFilterAutoConfiguration;

import com.store.greenShoes.service.AwsS3Service;


@SpringBootApplication(exclude = { 
    SecurityAutoConfiguration.class, 
    SecurityFilterAutoConfiguration.class 
    // Add ManagementWebSecurityAutoConfiguration.class if using Actuator security
})
public class GreenShoeStoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(GreenShoeStoreApplication.class, args);
		System.out.print("HI");
		AwsS3Service obj = AwsS3Service.GetAwsServiceObj();
		obj.SetupS3ClientObj();
		obj.CreateAndValidateS3Bucket();
		System.out.println("Spring Boot version: " + SpringBootVersion.getVersion());
	}
}
