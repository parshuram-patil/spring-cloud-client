package com.admin.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.context.scope.refresh.RefreshScopeRefreshedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

@Component
@EnableScheduling
@RefreshScope
public class Scheduler implements ApplicationListener<RefreshScopeRefreshedEvent> {

    @Value("${executor.pool.core-size}")
    private String corePoolSize;

    @Value("${executor.pool.max-size}")
    private String maxPoolSize;

    @Value("${executor.pool.capacity}")
    private String capacity;

    @Autowired
    private ThreadPoolTaskExecutor asyncExecutor;

    @Scheduled(cron = "* * * * * *")
    public void executeTask() {
        System.out.println(Thread.currentThread().getName() + ": " + corePoolSize + " -> " + maxPoolSize + " -> " + capacity);
        asyncExecutor.submit(() -> {
            try {
                Thread.sleep(0);
                System.out.println(Thread.currentThread().getName() + " Executed!");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
    }

    @Override
    public void onApplicationEvent(RefreshScopeRefreshedEvent event) {

    }
}
