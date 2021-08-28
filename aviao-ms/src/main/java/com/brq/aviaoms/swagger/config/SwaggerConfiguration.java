package com.brq.aviaoms.swagger.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

    /**
     *Método responsável por criar o Docket para Swagger
     * @return Docket
     */
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2).select()
                .apis(RequestHandlerSelectors.basePackage("com.brq.aviaoms")).paths(PathSelectors.any()).build()
                .apiInfo(apiInformation());
    }

    /**
     *Método responsável por prover informação para a API para o Swagger
     * @return ApiInfo
     */
    private ApiInfo apiInformation() {
        return new ApiInfoBuilder().title("Avião's Microservice API")
                .description("API Documentation for Avião Microservice").version("1.0").build();
    }
}
