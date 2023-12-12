package com.kevinraupp.api.studydockeraws.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI(){
        return new OpenAPI().info(new Info().title("RESTFul API with Docker, AWS by Kevin Raupp").version("v1").description("API created to study")
                .termsOfService("").license(new License().name("Apache 2.0").url("")));
    }
}
