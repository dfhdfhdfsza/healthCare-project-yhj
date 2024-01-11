package com.healthcare.www;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

@EnableJpaAuditing
@EnableSpringDataWebSupport
@SpringBootApplication
public class HealthcareApplication {
	public static void main(String[] args) {

		SpringApplication.run(HealthcareApplication.class, args);
	}

}
