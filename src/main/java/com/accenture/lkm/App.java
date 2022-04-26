package com.accenture.lkm;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@SpringBootApplication
@EnableSwagger2
@OpenAPIDefinition(info = @Info(title = "Customer Billing System API", version = "V2", description = ""))
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
