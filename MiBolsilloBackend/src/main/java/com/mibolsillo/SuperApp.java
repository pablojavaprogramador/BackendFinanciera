package com.mibolsillo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;

import com.mibolsilllo.config.SwaggerConfiguration;


@SpringBootApplication
@Import(SwaggerConfiguration.class)
//@EnableJpaRepositories(basePackages="com.mibolsillo.repository")
@ComponentScan({"com.mibolsillo","com.mibolsillo.controlador"})


public class SuperApp {

	public static void main(String[] args) {
		SpringApplication.run(SuperApp.class, args);
	}
	public void addResourceHandlers(ResourceHandlerRegistry registry) {  
        registry.addResourceHandler("swagger-ui.html")
                 .addResourceLocations("classpath:/META-INF/resources/");

 }
}
