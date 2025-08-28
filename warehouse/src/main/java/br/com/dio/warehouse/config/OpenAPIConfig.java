package br.com.dio.warehouse.config;

import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class OpenAPIConfig {
    @Bean
    OpenAPI customOpenAPI(){
        return new OpenAPI()
                .info(new Info()
                        .title("API da estoque do E-commerce")
                        .version("1.0")
                        .description("Documentação do estoque do E-commerce."));
    }
}
