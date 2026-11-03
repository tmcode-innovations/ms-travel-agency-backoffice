package com.saas.tourismagency.admin.backoffice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
public class BackOfficeApplication {
	public static void main(String[] args) {
		SpringApplication.run(BackOfficeApplication.class, args);
	}
}
