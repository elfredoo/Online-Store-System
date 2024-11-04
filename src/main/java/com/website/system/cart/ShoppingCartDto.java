package com.website.system.cart;

import java.util.List;

public class ShoppingCartDto {
    private Long id;
    private Long clientId;
    private List<Long> productsIds;

    public ShoppingCartDto(Long id, Long clientId, List<Long> productsIds) {
        this.id = id;
        this.clientId = clientId;
        this.productsIds = productsIds;
    }

    public ShoppingCartDto(Long clientId, List<Long> productsIds) {
        this.clientId = clientId;
        this.productsIds = productsIds;
    }

    public ShoppingCartDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public List<Long> getProductsIds() {
        return productsIds;
    }

    public void setProductsIds(List<Long> productsIds) {
        this.productsIds = productsIds;
    }
}
