package com.saapi.saapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication
@EnableAutoConfiguration
//@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class SaApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(SaApiApplication.class, args);
	}

}
