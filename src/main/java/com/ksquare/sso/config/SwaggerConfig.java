package com.ksquare.sso.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.common.base.Predicate;

import io.swagger.annotations.ApiOperation;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@Bean
    public Docket api(){
        return new Docket(DocumentationType.SWAGGER_2)
            .select()
            .apis(RequestHandlerSelectors.any()) 
            .paths(PathSelectors.any()) 
            .build()
            .apiInfo(apiInfo());
    }

	private ApiInfo apiInfo() {
		ApiInfo apiInfo = new ApiInfo("Single Sign On REST API", "Users manager REST API", "v0.1", "API TOS",
				"rafap, thomvald", "API License", "API License URL");
		return apiInfo;
	}
}