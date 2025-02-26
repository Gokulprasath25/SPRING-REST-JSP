package spring.mvc.crud.configuration;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("spring.mvc.crud")).paths(PathSelectors.any()).build()
				.apiInfo(apiDetails());
	}

	private ApiInfo apiDetails() {
		return new ApiInfo("SpringBoot_Crud", " DATA API and IMPLEMENTATION ", "1.0",
				"Terms of Service : http://abc.com",
				new Contact("Yahweh Corporation", "www.google.com", "gokulprasath25@hotmailmail.com"), "API TASK LICENSE",
				"www.springtask.com", Collections.emptyList());
	}
}
