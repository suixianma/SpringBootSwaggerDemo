package com.sxma.swagger.controller;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Home redirection to swagger api documentation
 */
@Controller
public class HomeController {
    private static final String SERVICE_INFO_KEY = "app";
    private static final String serviceName = "Swagger demo service";

    @Autowired
    private ServiceHolder serviceHolder;

    @GetMapping(value = "/")
    public String index() {
        return "redirect:swagger-ui.html";
    }

    @GetMapping(value = { "/v1/swagger-ui.html", "/v1" })
    public String index2() {
        return "redirect:/";
    }

    @GetMapping(value = "/heartbeat", produces = "application/json")
    @ResponseBody
    public String heartbeat() {
        return "OK";
    }

    @GetMapping(value = "/info")
    @ResponseBody
    public Map<String, Object> serviceInfo() {
        Map<String, Object> ret = new HashMap<>();
        ret.put(SERVICE_INFO_KEY, serviceHolder);
        return ret;
    }

    @JsonPropertyOrder({
            "service",
            "status",
            "date"
    })
    @SuppressWarnings("unused")
    private static final class ServiceStatus {
        private String status = "OK";

        private String date = new Date().toString();

        public String getService() {
            return serviceName;
        }

        public String getStatus() {
            return status;
        }

        public String getDate() {
            return date;
        }
    }
}
