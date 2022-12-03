package com.sxma.swagger.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import java.util.function.Predicate;
import static springfox.documentation.builders.PathSelectors.regex;

@Configuration
public class SwaggerConfig {

    @Bean
    public Docket analyticsApi() {
        return new Docket(DocumentationType.OAS_30)
                .groupName("demo")
                .apiInfo(analyticsApiInfo())
                .select()
                .paths(analyticsPaths())
                .build();
    }

    private Predicate<String> analyticsPaths() {
        return regex("/swaggerdemo/heartbeat").or(regex("/swaggerdemo/hello"));
    }

    private ApiInfo analyticsApiInfo() {
        String description = "Swagger demo";

        Contact contact = new Contact("SXMA", "http://www.demo.com", "sxma@demo.com");
        return new ApiInfoBuilder().title("Swagger demo")
                .description(description)
                .contact(contact)
                .version("1.0").build();
    }

}
