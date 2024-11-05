package com.website.system.invoice;

import com.website.system.cart.ShoppingCart;
import com.website.system.client.Client;
import com.website.system.order.Order;
import org.springframework.stereotype.Service;

@Service
public class InvoiceGenerator {
    public Invoice generateInvoice(Order order) {
        String invoiceNumber = "INV-"+order.getId();
        Invoice invoice = new Invoice(invoiceNumber, order.getClient(), order.getShoppingCart().getProducts(), 0.23);
        return invoice;
    }
}
