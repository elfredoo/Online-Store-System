package com.website.system.order;

import com.website.system.cart.ShoppingCart;
import com.website.system.cart.ShoppingCartDto;
import com.website.system.cart.ShoppingCartDtoMapper;
import com.website.system.cart.ShoppingCartManager;
import com.website.system.invoice.Invoice;
import com.website.system.invoice.InvoiceGenerator;
import com.website.system.invoice.InvoicePdfSaver;
import com.website.system.product.ProductManager;
import com.website.system.product.ProductRepository;
import com.website.system.product.datamodel.Product;
import com.website.system.product.dto.ProductDto;
import com.website.system.product.dto.ProductDtoMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderProcessor {
    private final ShoppingCartManager shoppingCartManager;
    private final ProductDtoMapper productDtoMapper;
    private final ShoppingCartDtoMapper shoppingCartDtoMapper;
    private final OrderManager orderManager;
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final ProductManager productManager;
    private final OrderDtoMapper orderDtoMapper;
    private final InvoiceGenerator invoiceGenerator;
    private final InvoicePdfSaver invoicePdfSaver;

    public OrderProcessor(ShoppingCartManager shoppingCartManager,
                          ProductDtoMapper productDtoMapper,
                          ShoppingCartDtoMapper shoppingCartDtoMapper,
                          OrderManager orderManager,
                          OrderRepository orderRepository,
                          ProductRepository productRepository,
                          ProductManager productManager,
                          OrderDtoMapper orderDtoMapper,
                          InvoiceGenerator invoiceGenerator,
                          InvoicePdfSaver invoicePdfSaver) {
        this.shoppingCartManager = shoppingCartManager;
        this.productDtoMapper = productDtoMapper;
        this.shoppingCartDtoMapper = shoppingCartDtoMapper;
        this.orderManager = orderManager;
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
        this.productManager = productManager;
        this.orderDtoMapper = orderDtoMapper;
        this.invoiceGenerator = invoiceGenerator;
        this.invoicePdfSaver = invoicePdfSaver;
    }

    @Transactional
    public OrderDto placeOrder(Long shoppingCartId) {
        ShoppingCartDto shoppingCartDto = shoppingCartManager.getShoppingCart(shoppingCartId);
        ShoppingCart shoppingCart = shoppingCartDtoMapper.map(shoppingCartDto);
        removeFromStock(shoppingCart.getProducts());
        Order order = new Order();
        order.setClient(shoppingCart.getClient());
        order.setShoppingCart(shoppingCart);
        order.setTotalPrice(orderManager.calculateTotalPrice(shoppingCartDto));
        order.setOrderDate(LocalDateTime.now());
        order.setOrderStatus(OrderStatus.PROCESSING);
        Order savedOrder = orderRepository.save(order);
        OrderDto orderDto = orderDtoMapper.map(order);

        Invoice invoice = invoiceGenerator.generateInvoice(savedOrder);
        String filePath = "invoices/invoice_"+savedOrder.getId()+".pdf";
        invoicePdfSaver.saveInvoiceToPdf(invoice, filePath);

        return orderDto;
    }

    private void removeFromStock(List<Product> products) {
        for (Product product : products) {
            if (product.getQuantity() <= 0) {
                throw new ProductOutOfStockException();
            } else if (product.getQuantity() <= 1) {
                productManager.deleteProduct(product.getId());
            } else{
                minusQuantity(product);
                productRepository.save(product);
            }
        }
    }

    private void minusQuantity(Product product) {
        product.setQuantity(product.getQuantity() - 1);
    }
}
