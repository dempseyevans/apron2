package com.apron2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("com.apron2.repository")
@ComponentScan(basePackages = "com.apron2")
@EntityScan("com.apron2.entity")
public class Apron2Application {

	public static void main(String[] args) {
		SpringApplication.run(Apron2Application.class, args);
	}

}
