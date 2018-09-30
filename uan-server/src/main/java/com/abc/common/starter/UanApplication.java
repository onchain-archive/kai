/**  
 * Title: UanApplication.java
 * Description: UanApplication
 * Copyright Agriculture Bank of China
 * @author Bo Liu
 * @date 2018-09-20
 * @version 1.0
 */ 
package com.abc.common.starter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RestController;

/**
 * Title: UanApplication
 * @Description: UanApplication
 * @author Bo Liu
 * @date 2018-09-20
 */
@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan(basePackages = "com.abc.*")
@RestController
public class UanApplication {

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(UanApplication.class);
		app.run(args);
	}

}
