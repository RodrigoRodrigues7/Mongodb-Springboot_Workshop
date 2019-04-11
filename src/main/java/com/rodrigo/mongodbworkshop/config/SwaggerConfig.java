package com.rodrigo.mongodbworkshop.config;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Header;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	private final ResponseMessage m201 = customMessage1();
	private final ResponseMessage m204put = simpleMessage(204, "Resource Updated");
	private final ResponseMessage m204del = simpleMessage(204, "Resource Deleted");
	private final ResponseMessage m403 = simpleMessage(403, "Unauthorized");
	private final ResponseMessage m404 = simpleMessage(404, "Not Found");
	private final ResponseMessage m422 = simpleMessage(422, "Validation Error");
	private final ResponseMessage m500 = simpleMessage(500, "Unexpected Error");

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				
				.useDefaultResponseMessages(false)
				.globalResponseMessage(RequestMethod.GET, Arrays.asList(m403, m404, m500))
				.globalResponseMessage(RequestMethod.POST, Arrays.asList(m201, m403, m422, m500))
				.globalResponseMessage(RequestMethod.PUT, Arrays.asList(m204put, m403, m404, m422, m500))
				.globalResponseMessage(RequestMethod.DELETE, Arrays.asList(m204del, m403, m404, m500))
				
				
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.rodrigo.mongodbworkshop.resources"))
				.paths(PathSelectors.any())
				.build()
				.apiInfo(apiInfo());
	}

	private ApiInfo apiInfo() {
		return new ApiInfo("API do Workshop de Springboot com MongoDB",
				"Esta API é utilizada no workshop de Springboot com MongoDB do prof. Nelio Alves", 
				"Versão 1.0",
				"https://www.udemy.com/terms",
				new Contact("Rodrigo A. de C. Rodrigues", "udemy.com/user/rodrigo-araujo-de-carvalho-rodrigues/", "rodrigo97.carvalho@gmail.com"),
				"Permitido uso para estudantes", "https://www.udemy.com/terms", 
				Collections.emptyList() // Vendor Extensions
		);
	}
	
	private ResponseMessage customMessage1() {
		Map<String, Header> map = new HashMap<>();
		map.put("location", new Header("location", "URI of the new resource", new ModelRef("string")));
		return new ResponseMessageBuilder()
		.code(201)
		.message("Resource Created")
		.headersWithDescription(map)
		.build();
		}
	
	private ResponseMessage simpleMessage(int code, String msg) {
		return new ResponseMessageBuilder().code(code).message(msg).build();
	}

}
