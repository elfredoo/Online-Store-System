package com.website.system.invoice;

import com.website.system.cart.ShoppingCart;
import com.website.system.client.Client;
import com.website.system.client.ClientRepository;
import com.website.system.order.Order;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class InvoiceGenerator {
    private final ClientRepository clientRepository;

    public InvoiceGenerator(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public Invoice generateInvoice(Order order) {
        String invoiceNumber = generateInvoiceNumber(order);
        return new Invoice(invoiceNumber, order.getClient(), order.getProducts(), 0.23);
    }

    private String generateInvoiceNumber(Order order) {
        Client client = order.getClient();
        String firstNameLetters = client.getFirstName().substring(0,2).toUpperCase();
        String lastNameLetters = client.getLastName().substring(0,2).toUpperCase();
        String usernameBeginning = firstNameLetters + lastNameLetters;
        String invoiceNumber = usernameBeginning + randomCode(3) + order.getId();
        return invoiceNumber;
    }

    private String randomCode(int length) {
        List<String> digits = Arrays.asList("0123456789".split(""));
        Collections.shuffle(digits);
        return digits.subList(0, length).stream().reduce(String::concat).get();
    }
}
