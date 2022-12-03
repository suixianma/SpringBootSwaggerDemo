package com.sxma.swagger.controller;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.io.CharSource;
import com.google.common.io.LineProcessor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;


@RestController
public class SwaggerController {

    @Value("#{servletContext.contextPath}")
    private String serverContextPath;

    private final static String API_DOCS_URI = "/v1/api-docs";

    private byte[] swaggerApiDocsJson;

    @PostConstruct
    private void init() {
        try {

            InputStream is = new ClassPathResource("/swagger/swagger.json").getInputStream();
            CharSource charSource = new CharSource() {

                @Override
                public Reader openStream() throws IOException {
                    return new InputStreamReader(is, Charset.defaultCharset());
                }
            };

            swaggerApiDocsJson = charSource.readLines(new LineProcessor<byte[]>() {

                StringBuilder sb = new StringBuilder();

                @Override
                public boolean processLine(String line) throws IOException {
                    sb.append(line.replaceAll("SERVICE_CONTEXT_PATH", serverContextPath));
                    sb.append("\n");
                    return true;
                }

                @Override
                public byte[] getResult() {
                    return sb.toString().getBytes(Charset.defaultCharset());
                }
            });
        } catch (IOException e) {
            ;
        }
    }

    @RequestMapping(method = RequestMethod.GET, path = API_DOCS_URI, produces = "application/json")
    public Resource apiDocs() {
        return new ByteArrayResource(swaggerApiDocsJson);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/swagger-resources/configuration/ui", produces = "application/json")
    public String uiConfig() {
        return "{\"isError\":false,\"validatorUrl\":null,\"apisSorter\":\"alpha\",\"docExpansion\":\"list\",\"displayRequestDuration\":true,\"filter\":false,\"jsonEditor\":false,\"showRequestHeaders\":true,\"message\":\"SUCCESS\",\"statusCode\":200,\"data\":{\"validatorUrl\":null,\"apisSorter\":\"alpha\",\"docExpansion\":\"list\",\"displayRequestDuration\":true,\"filter\":false,\"jsonEditor\":false,\"showRequestHeaders\":true}}";
    }

    @RequestMapping(method = RequestMethod.GET, path = "/swagger-resources/configuration/security", produces = "application/json")
    public Object securityConfig() {
        return ImmutableList.of(ImmutableMap.of());
    }

    @RequestMapping(method = RequestMethod.GET, path = "/swagger-resources", produces = "application/json")
    public Object resources() {
        return ImmutableList.of(ImmutableMap.of("name", "swaggerdemo", "url", API_DOCS_URI,
                "location", "/",
                "swaggerVersion", "2.0"));
    }

}
