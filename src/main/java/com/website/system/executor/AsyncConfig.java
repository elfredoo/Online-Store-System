package com.website.system.executor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Configuration
public class AsyncConfig {

    @Bean(name = "orderProcessingExecutor")
    public ExecutorService orderProcessingExecutor() {
        return Executors.newFixedThreadPool(20);
    }
}
