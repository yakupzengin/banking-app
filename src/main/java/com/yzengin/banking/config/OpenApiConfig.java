package com.yzengin.banking.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition
public class OpenApiConfig {
    @Bean
    public OpenAPI baseOpenApI(){
        return new OpenAPI().info(new Info()
                .title("Baking API")
                .description("API for banking operations")
                .version("1.0")
        );
    }
}
