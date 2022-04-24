package com.accenture.lkm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class App {											//Primary Configuration Class
	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}
}


/*
 * @Configuration
 * @ComponentScan - com.accenture.lkm
 * @EnableAutoConfiguration
 */
