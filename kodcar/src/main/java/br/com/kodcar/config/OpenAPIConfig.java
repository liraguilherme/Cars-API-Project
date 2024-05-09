package br.com.kodcar.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class OpenAPIConfig {

	@Bean
	public OpenAPI customOpenAPI() {
		return new OpenAPI()
				  .info(new Info()
					.title("KodCars API with Java and Spring Boot")
					  .version("v1")
					    .description("KodCars Web Api")
					      .termsOfService("https://youtube.com") //Link dos termos de serviço
					        .license(new License().name("Apache 2.0")
					        		.url("https://youtube.com"))); //Link da licença 

}
	
}
