package com.admin.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
@RequestMapping("/test")
public class TestController {

    @Value("${executor.pool.core-size}")
    private String corePoolSize;

    @Value("${executor.pool.max-size}")
    private String maxPoolSize;

    @Value("${executor.pool.capacity}")
    private String capacity;

    @GetMapping
    public String getProperty() {
        return corePoolSize + " -> " + maxPoolSize + " -> " + capacity;
    }
}

