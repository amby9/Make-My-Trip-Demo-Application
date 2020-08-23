package com.user.ledger.platform.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.user.ledger.platform.controllers"))
                .paths(PathSelectors.any())
                .build();
    }
//    private ApiInfo metaData() {
//        ApiInfo apiInfo = new ApiInfo(
//                "Consumer REST APIs",
//                "APIs for Consumer",
//                "0.1",
//                "Terms of service",
//                new Contact("Consumer Engineering Team", "https://lazypay.in", "virat.mishra@payu.in"),
//                "swagger-ui.html",
//                "swagger-ui.html",null);
//        return apiInfo;
//    }

}
