package com.admin.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
@RefreshScope
public class ThreadPoolConfig {

    @Value("${executor.pool.core-size}")
    private Integer corePoolSize;

    @Value("${executor.pool.max-size}")
    private Integer maxPoolSize;

    @Value("${executor.pool.capacity}")
    private Integer capacity;

    @Bean
    @RefreshScope
    public ThreadPoolTaskExecutor asyncExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(corePoolSize);
        executor.setMaxPoolSize(maxPoolSize);
        executor.setQueueCapacity(capacity);
        executor.setThreadNamePrefix("AsyncExecutor-");
        executor.initialize();
        return executor;
    }
}
