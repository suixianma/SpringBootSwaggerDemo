package com.sxma.swagger.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.web.bind.annotation.*;

@RestController
public class OpenApiController {

    @PostMapping(value = "/swaggerdemo/heartbeat")
    @Operation(summary = "heart beat")
    public String heartBeat() {
        return "OK";
    }

    @PostMapping(value = "/swaggerdemo/hello")
    @Operation(summary = "say hello to somebody")
    public String sayHello(
            @Parameter(
                    name = "name",
                    description = "full name",
                    required = true
            )
            @RequestParam String name) {
        return "Hello " + name;
    }

}
