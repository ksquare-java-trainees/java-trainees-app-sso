package com.ksquare.sso.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	
	@Bean
    public Docket api(){
        return new Docket(DocumentationType.SWAGGER_2)
        	.groupName("Users and Client Endpoints")
            .select()
            .apis(RequestHandlerSelectors.basePackage("com.ksquare.sso.controller")) 
            .paths(PathSelectors.any()) 
            .build()
            .apiInfo(apiInfo());
    }
	
	@Bean
	public Docket api2() {
		return new Docket(DocumentationType.SWAGGER_2)
		    .groupName("Token Endpoint")
		    .select()
            .apis(RequestHandlerSelectors.any()) 
		    .paths(PathSelectors.ant("/oauth/token"))
		    .build()
		    .apiInfo(apiInfo());
	}

	private ApiInfo apiInfo() {
		ApiInfo apiInfo = new ApiInfo(
				"Single Sign On REST API", 
				"Users manager REST API", 
				"v0.1", 
				"API TOS",
				"rafap, thomvald", 
				"API License", 
				"API License URL");
		return apiInfo;
	}
}