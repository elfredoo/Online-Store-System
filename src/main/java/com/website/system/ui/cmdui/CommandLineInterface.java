package com.website.system.ui.cmdui;

import com.website.system.cart.ShoppingCart;
import com.website.system.cart.ShoppingCartDto;
import com.website.system.cart.ShoppingCartDtoMapper;
import com.website.system.cart.ShoppingCartManager;
import com.website.system.client.*;
import com.website.system.order.OrderDto;
import com.website.system.order.OrderManager;
import com.website.system.order.OrderProcessor;
import com.website.system.product.ProductManager;
import com.website.system.product.ProductNotFoundException;
import com.website.system.product.datamodel.Product;
import com.website.system.product.datamodel.ProductType;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Scanner;

@Service
public class CommandLineInterface {
    private final OrderManager orderManager;
    private final OrderProcessor orderProcessor;
    ClientDto client;
    private final ProductManager productManager;
    private final ShoppingCartDtoMapper shoppingCartDtoMapper;
    private final Scanner sc;
    private final ClientManager clientManager;
    private final ClientDtoMapper clientDtoMapper;
    private final ShoppingCartManager shoppingCartManager;

    public CommandLineInterface(Scanner sc, ClientManager clientManager, ClientDtoMapper clientDtoMapper, ShoppingCartManager shoppingCartManager, ProductManager productManager, ShoppingCartDtoMapper shoppingCartDtoMapper, OrderManager orderManager, OrderProcessor orderProcessor) {
        this.sc = sc;
        this.clientManager = clientManager;
        this.clientDtoMapper = clientDtoMapper;
        this.shoppingCartManager = shoppingCartManager;
        this.productManager = productManager;
        this.shoppingCartDtoMapper = shoppingCartDtoMapper;
        this.orderManager = orderManager;
        this.orderProcessor = orderProcessor;
    }

    public void start(){
        if (client == null) logInRegisterOrExit();
        chooseAction();
    }

    private void chooseAction() {
        int option = -1;
        do{
            System.out.println("Co chciałbyś zrobić?");
            System.out.println(0 + " - wyjście");
            System.out.println(1 + " - przeglądaj dostępne produkty");
            System.out.println(2 + " - przeglądaj koszyk");
            System.out.println(3 + " - złóż zamówienie");
            option = sc.nextInt();
            sc.nextLine();
            switch (option) {
                case 0:
                    exit();
                    break;
                case 1:
                    addProducts();
                    break;
                case 2:
                    viewCart();
                    break;
                case 3:
                    placeOrder();
                    break;
                default:
                    invalidOption();
                    break;
            }
        }while (option != 3 || option != 0);
    }

    private void finalizeShopping() {
        int option = -1;
        do {
            System.out.println("Wybierz akcję którą chcesz wykonać:");
            System.out.println(0+" - wyjście");
            System.out.println(1+" - przeglądaj koszyk");
            System.out.println(2+" - złóż zamówienie");
            option = sc.nextInt();
            sc.nextLine();
            switch (option) {
                case 0:
                    exit();
                    break;
                case 1:
                    viewCart();
                    break;
                case 2:
                    placeOrder();
                    break;
                default:
                    invalidOption();
            }
        }while(option != 0 || option != 2);

    }

    private void placeOrder() {
        Long shoppingCartId = client.getShoppingCartId();
        try {
            OrderDto orderDto = orderProcessor.placeOrder(shoppingCartId);
            System.out.println("Zamówienie zostało złożone.");
            System.out.println("Podsumowanie:");
            System.out.println(orderDto);
            System.out.println("Dziękujemy za zakupy "+client.getFirstName()+"!");
        }catch (DataIntegrityViolationException e){
            System.err.println("Masz już złożone jedno zamówienie "+client.getFirstName());
        }

    }

    private void viewCart() {
        ShoppingCartDto shoppingCartDto = shoppingCartManager.getShoppingCart(client.getShoppingCartId());
        System.out.println("Liczba produktów w koszyku: "+shoppingCartDto.getProductsIds().size());
        System.out.println("Produkty: ");

        shoppingCartDto.getProductsIds()
                .stream()
                .map(id -> productManager.getProductDtoById(id).orElseThrow(ProductNotFoundException::new))
                .sorted()
                .forEach(System.out::println);
    }

    private void addProducts() {
        ProductType[] productTypes = ProductType.values();
        int option = -2;
        ProductType chosenProductType = null;
        do {
            System.out.println("Wybierz typ produktu który Cię interesuje:");
            for (int i = 0; i < productTypes.length; i++) {
                System.out.println(i+1 + ". " + productTypes[i].name());
            }
            option = sc.nextInt() - 1;
            sc.nextLine();
            for (ProductType productType : productTypes) {
                if (option == productType.ordinal()) {
                    chosenProductType = productTypes[option];
                    productManager.getAllProductsOfType(chosenProductType).forEach(System.out::println);
                }
            }
            System.out.println(0+" - wyjście");
            System.out.println("ID produktu - dodanie produktu do koszyka.");
            option = sc.nextInt();
            sc.nextLine();
            switch (option) {
                case 0:
                    exit();
                    break;
                default:
                    addProduct(option, chosenProductType);
                    break;
                }
        }while(option != 0);
    }

    private void addProduct(int option, ProductType productType) {
        Optional<Product> productById = productManager.getProductById((long) option);
        if (productById.isPresent()) {
            try {
                Product product = productById.get();
                if (!product.getProductType().equals(productType)) throw new ProductNotFoundException();
                shoppingCartManager.addProduct(client.getShoppingCartId(),
                        product.getId());
                System.out.println("Dodano produkt do koszyka.");
            }catch (ProductNotFoundException e) {
                System.out.println(e.getMessage());
            }
        }else{
            throw new ProductNotFoundException();
        }
    }

    private void logInRegisterOrExit() {
        int option = -1;
        do {
            System.out.println("Witaj w e-sklepie!");
            System.out.println("Wybierz jedną z opcji: ");
            System.out.println("1 - Zaloguj się");
            System.out.println("2 - Zarejestruj się");
            option = sc.nextInt();
            sc.nextLine();

            switch (option) {
                case 1:
                    logIn();
                    break;
                case 2:
                    register();
                    break;
                default:
                    invalidOption();
            }
        }while(option == -1);
    }

    private void invalidOption() {
        System.err.println("Wybrano nieobsługiwaną opcję, spróbuj ponownie.");
    }

    private void exit() {
        System.out.println("Dziękujemy za skorzystanie z naszej e-usługi!");
    }

    private void logIn() {
        boolean valid = false;
        do{
            try{
                System.out.println("Wprowadż swój email:");
                String email = sc.nextLine();

                System.out.println("Podaj hasło:");
                String password = sc.nextLine();

                ClientDto dto = clientManager.validateData(email,password);
                client = dto;
                valid = true;
            }catch (ClientNotFoundException | InvalidPasswordException e){
                System.err.println(e.getMessage());
                System.out.println("Spróbuj ponownie.");
            }
        }while(!valid);
    }

    private void register(){
        Client clientToSave = getClient();
        client = clientDtoMapper.map(clientToSave);
        ShoppingCartDto shoppingCartDto = shoppingCartManager.createShoppingCart(clientToSave);
        client.setShoppingCartId(shoppingCartDto.getId());
        clientManager.addClient(clientToSave);
    }

    private Client getClient() {
        String firstName;
        System.out.println("Wprowadź swoje imię:");
        firstName = sc.nextLine();

        String lastName;
        System.out.println("Wprowadź swoje nazwisko:");
        lastName = sc.nextLine();

        String email;
        System.out.println("Wprowadź swoje email:");
        email = sc.nextLine();

        int phoneNumber;
        System.out.println("Wprowadź swój numer telefonu:");
        phoneNumber = sc.nextInt();
        sc.nextLine();

        String country;
        System.out.println("Kraj zamieszkania:");
        country = sc.nextLine();

        String city;
        System.out.println("Miasto zamieszkania:");
        city = sc.nextLine();

        String street;
        System.out.println("Ulica zamieszkania:");
        street = sc.nextLine();

        String homeNo;
        System.out.println("Numer domu:");
        homeNo = sc.nextLine();

        String zipCode;
        System.out.println("Kod pocztowy:");
        zipCode = sc.nextLine();

        String timeZone;
        System.out.println("Preferowana strefa czasowa:");
        timeZone = sc.nextLine();

        String password;
        System.out.println("Wprowadź hasło:");
        password = sc.nextLine();

        return new Client(firstName, lastName, email, phoneNumber, country, city, street, homeNo, zipCode, timeZone, password);

    }

}