package com.website.system.order;

import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderDtoMapper {
    private final OrderRepository orderRepository;

    public OrderDtoMapper(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    OrderDto map(Order order) {
        OrderDto orderDto = new OrderDto();
        orderDto.setId(order.getId());
        orderDto.setTotalPrice(order.getTotalPrice());
        orderDto.setOrderStatus(order.getOrderStatus());
        return orderDto;
    }

    Order map(OrderDto dto) {
        Optional<Order> orderById = orderRepository.findById(dto.getId());
        if (orderById.isPresent()) {
            return orderById.get();
        }
        Order order = new Order();
        if (dto.getId() != null){
            order.setId(dto.getId());
        }
        order.setTotalPrice(dto.getTotalPrice());
        order.setOrderStatus(dto.getOrderStatus());
        return order;
    }
}
