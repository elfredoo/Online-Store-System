package com.website.system.client;

import java.util.List;

public class ClientDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String timeZone;
    private Long shoppingCartId;
    private String password;

    public ClientDto(Long id,
                     String firstName,
                     String lastName,
                     String timeZone,
                     Long shoppingCartId,
                     String password) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.timeZone = timeZone;
        this.shoppingCartId = shoppingCartId;
        this.password = password;
    }

    public ClientDto(String firstName,
                     String lastName,
                     String timeZone,
                     Long shoppingCartId,
                     String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.timeZone = timeZone;
        this.shoppingCartId = shoppingCartId;
        this.password = password;
    }

    public ClientDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }

    public Long getShoppingCartId() {
        return shoppingCartId;
    }

    public void setShoppingCartId(Long shoppingCartId) {
        this.shoppingCartId = shoppingCartId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "ClientDto{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", timeZone='" + timeZone + '\'' +
                ", shoppingCartId=" + shoppingCartId +
                ", password='" + password + '\'' +
                '}';
    }
}
