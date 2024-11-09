package com.website.system.order.product;

import com.website.system.order.Order;
import com.website.system.product.ProductManager;
import com.website.system.product.ProductRepository;
import com.website.system.product.datamodel.Product;
import com.website.system.product.dto.ProductDto;
import com.website.system.product.dto.ProductDtoMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class OrderProductManager {
    private final OrderProductRepository orderProductRepository;
    private final ProductManager productManager;
    private final ProductDtoMapper productDtoMapper;

    public OrderProductManager(OrderProductRepository orderProductRepository, ProductManager productManager, ProductDtoMapper productDtoMapper) {
        this.orderProductRepository = orderProductRepository;
        this.productManager = productManager;
        this.productDtoMapper = productDtoMapper;
    }

    @Transactional
    public OrderProduct map(ProductDto product, Order order){
        Optional<OrderProduct> existingOrderProduct = order.getProducts().stream()
                .filter(prod -> prod.getProduct().getId().equals(product.getId()))
                .findFirst();
        if (existingOrderProduct.isPresent()) {
            OrderProduct orderProduct = existingOrderProduct.get();
            orderProduct.setQuantity(orderProduct.getQuantity()+1);
            return orderProductRepository.save(orderProduct);
        }
        OrderProduct newOrderProduct = new OrderProduct();
        newOrderProduct.setProduct(productDtoMapper.map(product));
        newOrderProduct.setOrder(order);
        newOrderProduct.setQuantity(1);
        newOrderProduct.setPrice(product.getPrice());

        order.getProducts().add(newOrderProduct);
        return orderProductRepository.save(newOrderProduct);
    }
}
