package com.portfolio.BlogManagementSystem.configs;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
//This annotation tells swagger that This API uses Authorization: Bearer <JWT>
@SecurityScheme(
        name = "Bearer Authentication",
        type = SecuritySchemeType.HTTP,
        scheme = "bearer",
        bearerFormat = "JWT")

public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenApi() {

        return new OpenAPI()
                .info(
                        new Info()
                                .title("Blog Management System API")
                                .version("1.0")
                                .description("REST APIs for Blog Management System")
                );
    }

}
