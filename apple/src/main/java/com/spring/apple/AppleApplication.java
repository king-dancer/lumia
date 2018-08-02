package com.spring.apple;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class AppleApplication {

	public static void main(String[] args) {
		SpringApplication.run(AppleApplication.class, args);
	}
	
}
