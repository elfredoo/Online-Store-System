package com.website.system.client;

import com.website.system.cart.ShoppingCart;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.util.List;

@Entity
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
    @Email @NotNull
    private String email;
    @NotNull @Pattern(regexp = "\\d{9,15}")
    private String phoneNumber;
    @NotNull
    private String country;
    @NotNull
    private String city;
    @NotNull
    private String street;
    @NotNull
    private String homeNo;
    @NotNull @Pattern(regexp = "^[A-Za-z0-9_-]{3,10}$")
    private String zipCode;
    @NotNull @Pattern(regexp = "[a-zA-Z0-9_]+/[a-zA-Z0-9_]+")
    private String timeZone;
    @OneToOne(mappedBy = "client",cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private ShoppingCart shoppingCart;
    @NotNull
    private String password;

    public Client() {
    }

    public Client(String firstName,
                  String lastName,
                  String email,
                  String phoneNumber,
                  String country,
                  String city,
                  String street,
                  String homeNo,
                  String zipCode,
                  String timeZone,
                  ShoppingCart shoppingCart,
                  String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.country = country;
        this.city = city;
        this.street = street;
        this.homeNo = homeNo;
        this.zipCode = zipCode;
        this.timeZone = timeZone;
        this.shoppingCart = shoppingCart;
        this.password = password;
    }

    public Client(String firstName,
                  String lastName,
                  String email,
                  String phoneNumber,
                  String country,
                  String city,
                  String street,
                  String homeNo,
                  String zipCode,
                  String timeZone,
                  String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.country = country;
        this.city = city;
        this.street = street;
        this.homeNo = homeNo;
        this.zipCode = zipCode;
        this.timeZone = timeZone;
        this.password = password;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getHomeNo() {
        return homeNo;
    }

    public void setHomeNo(String homeNo) {
        this.homeNo = homeNo;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }

    public void setShoppingCart(ShoppingCart shoppingCart) {
        this.shoppingCart = shoppingCart;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber=" + phoneNumber +
                ", country='" + country + '\'' +
                ", city='" + city + '\'' +
                ", street='" + street + '\'' +
                ", homeNo='" + homeNo + '\'' +
                ", zipCode='" + zipCode + '\'' +
                ", timeZone='" + timeZone + '\'' +
                ", shoppingCart=" + shoppingCart +
                ", password='" + password + '\'' +
                '}';
    }
}
