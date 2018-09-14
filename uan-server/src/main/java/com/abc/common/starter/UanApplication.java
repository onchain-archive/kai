package com.abc.common.starter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan(basePackages = "com.abc.*")
// @MapperScan("com.abc")
@RestController
public class UanApplication {

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(UanApplication.class);
		app.run(args);
	}

}
