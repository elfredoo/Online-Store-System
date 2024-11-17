package com.website.system.ui.cmd;

import com.website.system.cart.ShoppingCartDto;
import com.website.system.cart.ShoppingCartDtoMapper;
import com.website.system.cart.ShoppingCartManager;
import com.website.system.client.*;
import com.website.system.order.*;
import com.website.system.product.ProductManager;
import com.website.system.product.ProductNotFoundException;
import com.website.system.product.datamodel.Product;
import com.website.system.product.datamodel.ProductType;
import jakarta.validation.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;

@Service
public class CommandLineInterface {
    private final OrderService orderService;
    private final OrderProcessor orderProcessor;
    ClientDto client;
    private final ProductManager productManager;
    private final ShoppingCartDtoMapper shoppingCartDtoMapper;
    private final Scanner sc;
    private final ClientManager clientManager;
    private final ClientDtoMapper clientDtoMapper;
    private final ShoppingCartManager shoppingCartManager;

    public CommandLineInterface(Scanner sc, ClientManager clientManager, ClientDtoMapper clientDtoMapper, ShoppingCartManager shoppingCartManager, ProductManager productManager, ShoppingCartDtoMapper shoppingCartDtoMapper, OrderService orderService, OrderProcessor orderProcessor) {
        this.sc = sc;
        this.clientManager = clientManager;
        this.clientDtoMapper = clientDtoMapper;
        this.shoppingCartManager = shoppingCartManager;
        this.productManager = productManager;
        this.shoppingCartDtoMapper = shoppingCartDtoMapper;
        this.orderService = orderService;
        this.orderProcessor = orderProcessor;
    }

    public void start(){
        logInRegisterOrExit();
        chooseAction();
    }

    private void chooseAction() {
        int option = -1;
        do{
            try {
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
            }catch (InputMismatchException e){
                System.err.println("Wybrano niepoprawną opcję, spróbuj ponownie.");
                sc.nextLine();
                option = -1;
            }catch (NoSuchElementException e){
                System.err.println(e.getMessage());
                option = -1;
            }
        }while (option != 0);
    }

    private void placeOrder() {
        Long shoppingCartId = client.getShoppingCartId();
        try {
            OrderDto orderDto = orderProcessor.processOrder(shoppingCartId).get();
            System.out.println("Zamówienie zostało złożone.");
            System.out.println("Podsumowanie:");
            System.out.println(orderDto);
            System.out.println("Dziękujemy za zakupy "+client.getFirstName()+"!");
        }catch (DataIntegrityViolationException | ProductOutOfStockException e){
            System.err.println(e.getMessage());
            System.out.println("W twoim koszyku znajdują się produkty, które nie powinny się tam znaleźć, za chwilę nastąpi wyczyszczenie Twojego koszyka.");
            shoppingCartManager.clearShoppingCart(shoppingCartId);
        } catch (ExecutionException | InterruptedException e) {
            System.err.println(e.getMessage());
            System.out.println("Nie udało się złożyć zamówienia");
        }

    }

    private void viewCart() {
        int option = -1;
        do {
            ShoppingCartDto shoppingCartDto = shoppingCartManager.getShoppingCart(client.getShoppingCartId());
            System.out.println("Liczba produktów w koszyku: " + shoppingCartDto.getProductsIds().size());
            System.out.println("Produkty: ");

            shoppingCartDto.getProductsIds()
                    .stream()
                    .map(id -> productManager.getProductDtoById(id).orElseThrow(ProductNotFoundException::new))
                    .sorted()
                    .forEach(System.out::println);
            System.out.println(0+" - wyjście");
            System.out.println(1+" - wyczyść koszyk");
            option = sc.nextInt();
            sc.nextLine();
            switch (option) {
                case 0:
                    redirectToMainLoop();
                   break;
                case 1:
                    clearCart(shoppingCartDto);
                    break;
                default:
                    invalidOption();
                    break;
            }
        }while(option != 0);
    }

    private void clearCart(ShoppingCartDto shoppingCartDto) {
        Long cartToDelete = shoppingCartDto.getId();
        shoppingCartManager.clearShoppingCart(cartToDelete);
        System.out.println("Usunięto produkty z koszyka");
    }

    private void redirectToMainLoop() {
        System.out.println("Jesteś przekierowywany do głownego menu...");
    }

    private void addProducts() {
        ProductType[] productTypes = ProductType.values();
        int option = -2;
        ProductType chosenProductType = null;
        do {
            try{
                System.out.println("Wybierz typ produktu który Cię interesuje:");
                for (int i = 0; i < productTypes.length; i++) {
                    System.out.println(i+1 + ". " + productTypes[i].name());
                }
                option = sc.nextInt() - 1;
                sc.nextLine();
                if (option >= 0 && option < productTypes.length) {
                    chosenProductType = productTypes[option];
                    productManager.getAllProductsOfTypeIfAvailable(chosenProductType).forEach(System.out::println);
                }else {
                    throw new NoSuchElementException("Wybrano niepoprawny typ produktu.");
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
            }catch (InputMismatchException e){
                System.err.println("Proszę o wybranie poprawnej opcji.");
                sc.nextLine();
                option = -2;
            }catch (ProductOutOfStockException | ProductNotFoundException e) {
                System.err.println(e.getMessage());
                option = -2;
            }
        }while(option != 0);
    }

    private void addProduct(int option, ProductType productType) {
        Optional<Product> productById = productManager.getProductById((long) option);
        if (productById.isPresent()) {
                Product product = productById.get();
                if (!product.getProductType().equals(productType)) throw new ProductNotFoundException();
                shoppingCartManager.addProduct(client.getShoppingCartId(),
                        product.getId());
                System.out.println("Dodano produkt do koszyka.");
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
                try {
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
                            option = -1;
                            break;
                    }
                }catch (InputMismatchException e) {
                    System.err.println("Niepoprawny typ danych, spróbuj ponownie.");
                    sc.nextLine();
                    option = -1;
                }catch (ConstraintViolationException | EmailAlreadyTakenException e){
                    System.err.println(e.getMessage());
                    option = -1;
                }
        }while (option == -1) ;

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

                ClientDto dto = clientManager.verifyLoginData(email,password);
                client = dto;
                valid = true;
            }catch (ClientNotFoundException | InvalidPasswordException e){
                System.err.println(e.getMessage());
                System.out.println("Konto nie istnieje.");
                logInRegisterOrExit();
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

        String phoneNumber;
        System.out.println("Wprowadź swój numer telefonu:");
        phoneNumber = sc.nextLine();

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
        System.out.println("Preferowana strefa czasowa (Kontynent/Stolica):");
        timeZone = sc.nextLine();

        String password;
        System.out.println("Wprowadź hasło:");
        password = sc.nextLine();

        Client potentialClient = new Client(firstName, lastName, email, phoneNumber, country, city, street, homeNo, zipCode, timeZone, password);
        return clientManager.validateData(potentialClient);
    }

}