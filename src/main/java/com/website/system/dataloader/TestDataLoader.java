package com.website.system.dataloader;

import com.website.system.cart.ShoppingCart;
import com.website.system.client.Client;
import com.website.system.client.ClientManager;
import com.website.system.discount.Discount;
import com.website.system.discount.DiscountManager;
import com.website.system.product.ProductManager;
import com.website.system.product.datamodel.ProductType;
import com.website.system.product.datamodel.antiwrinklecream.AntiWrinkleCream;
import com.website.system.product.datamodel.antiwrinklecream.SkinType;
import com.website.system.product.datamodel.babyclothes.BabyClothes;
import com.website.system.product.datamodel.babyclothes.Season;
import com.website.system.product.dto.ProductDto;
import com.website.system.product.dto.ProductDtoMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Stream;

@Service
public class TestDataLoader {

    private final ProductManager productManager;
    private final DiscountManager discountManager;
    private final ProductDtoMapper productDtoMapper;
    private final ClientManager clientManager;

    public TestDataLoader(ProductManager productManager, DiscountManager discountManager, ProductDtoMapper productDtoMapper, ClientManager clientManager) {
        this.productManager = productManager;
        this.discountManager = discountManager;
        this.productDtoMapper = productDtoMapper;
        this.clientManager = clientManager;
    }

    public void loadTestData(){
        AntiWrinkleCream cream1 = new AntiWrinkleCream();
        cream1.setName("Krem na noc 30+");
        cream1.setPrice(129.99);
        cream1.setQuantity(50);
        cream1.setAgeGroup(30);
        cream1.setProductType(ProductType.ANTI_WRINKLE_CREAM);
        cream1.setSkinType(SkinType.SENSITIVE);

        AntiWrinkleCream cream2 = new AntiWrinkleCream();
        cream2.setName("Krem na dzień 50+");
        cream2.setPrice(159.99);
        cream2.setQuantity(35);
        cream2.setAgeGroup(50);
        cream2.setProductType(ProductType.ANTI_WRINKLE_CREAM);
        cream2.setSkinType(SkinType.MATURE);

        AntiWrinkleCream cream3 = new AntiWrinkleCream();
        cream3.setName("Krem nawilżający 40+");
        cream3.setPrice(139.49);
        cream3.setQuantity(40);
        cream3.setAgeGroup(40);
        cream3.setProductType(ProductType.ANTI_WRINKLE_CREAM);
        cream3.setSkinType(SkinType.DRY);

        productManager.addProduct(cream1);
        productManager.addProduct(cream2);
        productManager.addProduct(cream3);

        BabyClothes winterClothes = new BabyClothes();
        winterClothes.setName("Ciepły kombinezon zimowy");
        winterClothes.setPrice(149.99);
        winterClothes.setQuantity(30);
        winterClothes.setBabyHeight(70);
        winterClothes.setProductType(ProductType.BABY_CLOTHES);
        winterClothes.setSeason(Season.WINTER);
        BabyClothes springClothes = new BabyClothes();

        springClothes.setName("Lekka bluza na wiosnę");
        springClothes.setPrice(89.99);
        springClothes.setQuantity(50);
        springClothes.setBabyHeight(60);
        springClothes.setProductType(ProductType.BABY_CLOTHES);
        springClothes.setSeason(Season.SPRING);

        BabyClothes summerClothes = new BabyClothes();
        summerClothes.setName("Bawełniane body na lato");
        summerClothes.setPrice(49.99);
        summerClothes.setQuantity(40);
        summerClothes.setBabyHeight(50);
        summerClothes.setProductType(ProductType.BABY_CLOTHES);
        summerClothes.setSeason(Season.SUMMER);

        BabyClothes autumnClothes = new BabyClothes();
        autumnClothes.setName("Ciepły sweterek na jesień");
        autumnClothes.setPrice(99.99);
        autumnClothes.setQuantity(35);
        autumnClothes.setBabyHeight(65);
        autumnClothes.setProductType(ProductType.BABY_CLOTHES);
        autumnClothes.setSeason(Season.AUTUMN);

        // Dodanie produktów do systemu
        productManager.addProduct(winterClothes);
        productManager.addProduct(springClothes);
        productManager.addProduct(summerClothes);
        productManager.addProduct(autumnClothes);

        Discount discount1 = new Discount();
        discount1.setName("Black Friday Sale");
        discount1.setPercentage(0.2);
        discount1.setStartDate(LocalDate.of(2024, 11, 24));
        discount1.setEndDate(LocalDate.of(2024, 11, 30));
        discount1.setApplicableProducts(List.of(autumnClothes, winterClothes));

        Discount discount2 = new Discount();
        discount2.setName("Winter Special");
        discount2.setPercentage(0.15);
        discount2.setStartDate(LocalDate.of(2024, 12, 1));
        discount2.setEndDate(LocalDate.of(2025, 2, 28));
        discount2.setApplicableProducts(List.of(summerClothes));

        Discount discount3 = new Discount();
        discount3.setName("Skincare Discount");
        discount3.setPercentage(0.1);
        discount3.setStartDate(LocalDate.of(2024, 11, 20));
        discount3.setEndDate(LocalDate.of(2024, 12, 31));
        discount3.setApplicableProducts(List.of(cream1,cream2,cream3));

        List<ProductDto> productDtosForSkincareDiscount = Stream.of(cream1, cream2, cream3).map(productDtoMapper::map).toList();

        discountManager.save(discount1);
        discountManager.save(discount2);
        discountManager.save(discount3);
        discountManager.applyDiscount(3L,productDtosForSkincareDiscount);
    }
}
