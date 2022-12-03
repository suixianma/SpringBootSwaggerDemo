package com.sxma.swagger.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ServiceHolder {
    public static final String UNKNOWN_VERSION = "unknown";

    @Value("${service.name:SwaggerDemo}")
    private String name;

    private static final String description = "The service is to integrate static Swagger.json";

    private final String version;

    public ServiceHolder(ApplicationContext context) {
        version = context.getBeansWithAnnotation(SpringBootApplication.class).entrySet()
                .stream().findFirst()
                .flatMap(application ->
                        Optional.ofNullable(application.getValue().getClass().getPackage().getImplementationVersion()))
                .orElse(UNKNOWN_VERSION);
    }

    public String getVersion() {
        return version;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
