package com.website.system.order;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;

@Service
@EnableAsync
public class OrderProcessor {
    private final OrderService orderService;
    private final ExecutorService orderProcessingExecutor;

    public OrderProcessor(OrderService orderService,
                          @Qualifier("orderProcessingExecutor")ExecutorService orderProcessingExecutor) {
        this.orderService = orderService;
        this.orderProcessingExecutor = orderProcessingExecutor;
    }

    public CompletableFuture<OrderDto> processOrder(Long shoppingCartId) {
        return CompletableFuture.supplyAsync(()-> orderService.placeOrder(shoppingCartId),orderProcessingExecutor);
    }
}
