package com.website.system.invoice;

import com.website.system.client.Client;
import com.website.system.order.product.OrderProduct;
import com.website.system.product.datamodel.Product;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;


public class Invoice {
    private String invoiceNumber;
    private LocalDate issueDate;
    private Client client;
    private Set<OrderProduct> products;
    private double vatRate;

    public Invoice(String invoiceNumber, Client client, Set<OrderProduct> products ,double vatRate) {
        this.invoiceNumber = invoiceNumber;
        this.issueDate = LocalDate.now();
        this.client = client;
        this.products = products;
        this.vatRate = vatRate;
    }

    public void addProduct(OrderProduct product) {
        products.add(product);
    }

    public double getNetTotal(){
        double netTotal = 0;
        for (OrderProduct product : products) {
            netTotal += product.getPrice() * product.getQuantity();
        }
        return netTotal;
    }

    public double getVatAmount(){
        return getNetTotal()*vatRate;
    }

    public double getGrossTotal(){
        return getNetTotal()+getVatAmount();
    }

    public LocalDate getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(LocalDate issueDate) {
        this.issueDate = issueDate;
    }

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Set<OrderProduct> getProducts() {
        return products;
    }

    public void setProducts(Set<OrderProduct> products) {
        this.products = products;
    }

    public double getVatRate() {
        return vatRate;
    }

    public void setVatRate(double vatRate) {
        this.vatRate = vatRate;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Invoice Number: ").append(invoiceNumber).append("\n");
        sb.append("Issue Date: ").append(issueDate).append("\n");
        sb.append("Client: ").append(client.getFirstName()).append(" ").append(client.getLastName()).append("\n");
        sb.append("Products: \n");
        products.forEach(product -> sb.append(product.getProduct().getName()).append("\n"));
        sb.append("Net Total: ").append(getNetTotal()).append("PLN\n");
        sb.append("VAT (").append(vatRate*100).append("%): ").append(getVatAmount()).append("PLN\n");
        sb.append("Gross Total: ").append(getGrossTotal()).append("PLN\n");
        return sb.toString();
    }
}
