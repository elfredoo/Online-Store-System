package com.website.system.order;

import com.website.system.cart.ShoppingCart;
import com.website.system.cart.ShoppingCartDto;
import com.website.system.cart.ShoppingCartDtoMapper;
import com.website.system.cart.ShoppingCartManager;
import com.website.system.client.Client;
import com.website.system.client.ClientManager;
import com.website.system.discount.DiscountManager;
import com.website.system.invoice.Invoice;
import com.website.system.invoice.InvoiceGenerator;
import com.website.system.invoice.InvoicePdfSaver;
import com.website.system.order.product.OrderProduct;
import com.website.system.order.product.OrderProductManager;
import com.website.system.product.ProductManager;
import com.website.system.product.ProductRepository;
import com.website.system.product.datamodel.Product;
import com.website.system.product.dto.ProductDto;
import com.website.system.product.dto.ProductDtoMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import static java.util.Arrays.stream;

@Service
public class OrderService {
    private final ShoppingCartManager shoppingCartManager;
    private final ProductDtoMapper productDtoMapper;
    private final ShoppingCartDtoMapper shoppingCartDtoMapper;
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final ProductManager productManager;
    private final OrderDtoMapper orderDtoMapper;
    private final InvoiceGenerator invoiceGenerator;
    private final InvoicePdfSaver invoicePdfSaver;
    private final OrderProductManager orderProductManager;
    private final ClientManager clientManager;
    private final DiscountManager discountManager;

    public OrderService(ShoppingCartManager shoppingCartManager,
                        ProductDtoMapper productDtoMapper,
                        ShoppingCartDtoMapper shoppingCartDtoMapper,
                        OrderRepository orderRepository,
                        ProductRepository productRepository,
                        ProductManager productManager,
                        OrderDtoMapper orderDtoMapper,
                        InvoiceGenerator invoiceGenerator,
                        InvoicePdfSaver invoicePdfSaver, OrderProductManager orderProductManager, ClientManager clientManager, DiscountManager discountManager) {
        this.shoppingCartManager = shoppingCartManager;
        this.productDtoMapper = productDtoMapper;
        this.shoppingCartDtoMapper = shoppingCartDtoMapper;
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
        this.productManager = productManager;
        this.orderDtoMapper = orderDtoMapper;
        this.invoiceGenerator = invoiceGenerator;
        this.invoicePdfSaver = invoicePdfSaver;
        this.orderProductManager = orderProductManager;
        this.clientManager = clientManager;
        this.discountManager = discountManager;
    }

    @Transactional
    public OrderDto placeOrder(Long shoppingCartId) {
        ShoppingCartDto shoppingCartDto = shoppingCartManager.getShoppingCart(shoppingCartId);
        ShoppingCart shoppingCart = shoppingCartDtoMapper.map(shoppingCartDto);
        List<Product> products = shoppingCart.getProducts();

        removeFromStock(products);
        Order order = new Order();

        String clientTimeZone = shoppingCart.getClient().getTimeZone();
        ZoneId zoneId = ZoneId.of(clientTimeZone);
        System.out.println("Strefa czasowa użytkownika: " + clientTimeZone);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        ZonedDateTime now = ZonedDateTime.now(zoneId);
        String nowFormatted = now.format(formatter);
        System.out.println("Data złożenia zamówienia: "+nowFormatted);
        
        order.setClient(shoppingCart.getClient());
        order.setProducts(mapShoppingCartToOrderProducts(shoppingCart, order));
        order.setTotalPrice(calculateTotalPrice(order.getProducts()));
        order.setOrderDate(LocalDateTime.now(zoneId));
        order.setOrderStatus(OrderStatus.PROCESSING);
        Order savedOrder = orderRepository.save(order);
        OrderDto orderDto = orderDtoMapper.map(order);

        Client client = order.getClient();
        Long cartToDeleteId = client.getShoppingCart().getId();
        shoppingCartManager.clearShoppingCart(cartToDeleteId);
        client.setShoppingCart(shoppingCart);
        clientManager.addClient(client);

        Invoice invoice = invoiceGenerator.generateInvoice(savedOrder);
        String filePath = "invoices/invoice_"+savedOrder.getId()+".pdf";
        invoicePdfSaver.saveInvoiceToPdf(invoice, filePath);

        return orderDto;
    }

    private double calculateTotalPrice(Set<OrderProduct> products) {
        double totalPrice = 0.0;
        for (OrderProduct product : products) {
            totalPrice += product.getPrice() * product.getQuantity();
        }
        return totalPrice;
    }

    private Set<OrderProduct> mapShoppingCartToOrderProducts(ShoppingCart shoppingCart, Order order) {
        List<Product> products = shoppingCart.getProducts();
        Set<OrderProduct> setToReturn = new HashSet<>();
        for (Product product : products) {
            if (product.getDiscount()!= null){
                ProductDto productDto = discountManager.checkForDiscount(product.getDiscount().getId(), product.getId());
                OrderProduct orderProduct = orderProductManager.map(productDto, order);
                setToReturn.add(orderProduct);
            }else{
                OrderProduct orderProduct = orderProductManager.map(productDtoMapper.map(product), order);
                setToReturn.add(orderProduct);
            }
        }
        return setToReturn;

    }

    private void removeFromStock(List<Product> products) {
        for (Product product : products) {
            if (product.getQuantity() <= 0) {
                throw new ProductOutOfStockException();
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
