package br.com.restful.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class OpenApiConfig {

	@Bean
	OpenAPI customApi() {
		return new OpenAPI()
				.info(new Info()
						.title("Restful Api with Spring Boot and Java 17")
						.version("v1")
						.description("Api developed for personal use")
						.termsOfService("")
						.license(new License()
								.name("Apache 2.0")
								.url("https://www.apache.org/licenses/LICENSE-2.0.txt")));
	}

}
