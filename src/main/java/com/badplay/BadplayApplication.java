package com.badplay;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class BadplayApplication {
	public static void main(String[] args) {
		SpringApplication.run(BadplayApplication.class, args);
	}
}