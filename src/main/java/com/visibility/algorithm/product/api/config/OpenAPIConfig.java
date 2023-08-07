package com.visibility.algorithm.product.api.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * This class is used to set up the OpenAPI configuration.
 * <p>
 * The '@Configuration' annotation tells Spring that this class is a configuration class,
 * and the 'proxyBeanMethods' property is set to 'false' for performance optimization
 * as it bypasses the CGLIB proxy creation for bean methods.
 * </p>
 * The '@Slf4j' annotation is a Lombok annotation which provides a logger for the class.
 */
@Slf4j
@Configuration(proxyBeanMethods = false)
public class OpenAPIConfig {

    /**
     * The '@Bean' annotation is used at the method level, it tells Spring that the
     * returning instance from this method will be a bean. The bean's lifecycle will be managed
     * by Spring.
     * <p>
     * This method sets up the OpenAPI details including its information like title,
     * version, description, terms of service, and license. This is used for the API documentation.
     * </p>
     *
     * @return An OpenAPI object with the setup information.
     */
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Test and exercise for capitole-consulting-Inditex")
                        .version("0.1")
                        .description("When a grid of products is painted on the web fronts of commercial stores e-mail, it is necessary to filter those products that have run out of stock to facilitate the user to find products that he can buy")
                        .termsOfService("http://swagger.io/terms/")
                        .license(new License().name("Apache 2.0").url("http://springdoc.org")));
    }
}
