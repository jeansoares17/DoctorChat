package br.com.fiap.doctorchat.config;

import org.springframework.context.annotation.*;

import io.swagger.v3.oas.models.*;
import io.swagger.v3.oas.models.info.*;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configuration
public class DocumentationConfig {
    @Bean
    public OpenAPI costumOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("DoctorChat Open API")
                        .version("V1")
                        .description("API para agendar consultas")
                        .contact(new Contact().name("Mateus Marchetti Vieira").email("rm94075@fiap.com.br")))
                .components(new Components()
                        .addSecuritySchemes("bearer-key",
                                new SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("bearer")
                                        .bearerFormat("JWT")));
    }
}