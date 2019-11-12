package com.Organizer.Snacky;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
public class SnackyApplication {

	public static void main(String[] args) {
		SpringApplication.run(SnackyApplication.class, args);
	}

}
