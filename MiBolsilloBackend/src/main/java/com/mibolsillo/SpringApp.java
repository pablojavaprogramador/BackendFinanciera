package com.mibolsillo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;

import com.mibolsilllo.config.SwaggerConfiguration;


@SpringBootApplication
@Import(SwaggerConfiguration.class)
//@ComponentScan(basePackages = "com.mibolsilllo")
public class SpringApp {

	public static void main(String[] args) {
		SpringApplication.run(SpringApp.class, args);
	}
	public void addResourceHandlers(ResourceHandlerRegistry registry) {  
        registry.addResourceHandler("swagger-ui.html")
                 .addResourceLocations("classpath:/META-INF/resources/");

 }
}
