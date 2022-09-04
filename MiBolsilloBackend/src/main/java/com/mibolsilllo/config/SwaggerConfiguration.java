package com.mibolsilllo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.collect.Sets;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration  {

	//http://localhost:9090/swagger-ui.html
		
	@Bean
	public Docket apiDocket() {
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(getApiInfo())
				.select()
				.apis(Predicates.or(
						RequestHandlerSelectors.basePackage("com.mibolsillo")
				))
				.paths(getPaths())
				.build()
				.enableUrlTemplating(false)
				.protocols(Sets.newHashSet("http"));
	
			
	}


	private Predicate<String> getPaths() {
		return Predicates.or(
				PathSelectors.ant("/**")
		
		);
	}

	private ApiInfo getApiInfo() {
		return new ApiInfoBuilder().title("APIS MI BOLSILLO SUPERAPP")
				.description("Apis SuperAPP Modelo")
				.termsOfServiceUrl("http://www.mibolsillo.com.mx")
				.contact(new Contact("Pablo Ernesto Cortes Palma", "", "tecnoplacita@gmail.com")).license("License of API")
				.licenseUrl("http://www.mibolsillo.com.mx/terms/").version("1.0.0").build();
	}


}
