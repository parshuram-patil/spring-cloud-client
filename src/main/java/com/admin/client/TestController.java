package com.admin.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

    @Value("${env.name.full}")
    private String envFullName;

    @Value("${env.name.short}")
    private String envShortName;

    @GetMapping
    public String getProperty() {
        return envShortName + " -> " + envFullName;
    }
}

