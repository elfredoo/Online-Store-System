package com.website.system.discount;

import com.website.system.product.ProductManager;
import com.website.system.product.ProductNotFoundException;
import com.website.system.product.ProductRepository;
import com.website.system.product.datamodel.Product;
import com.website.system.product.dto.ProductDto;
import com.website.system.product.dto.ProductDtoMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Service
public class DiscountManager {

    private final DiscountRepository discountRepository;
    private final ProductRepository productRepository;
    private final DiscountDtoMapper discountDtoMapper;
    private final ProductDtoMapper productDtoMapper;
    private final ProductManager productManager;

    public DiscountManager(DiscountRepository discountRepository, ProductRepository productRepository, DiscountDtoMapper discountDtoMapper, ProductDtoMapper productDtoMapper, ProductManager productManager) {
        this.discountRepository = discountRepository;
        this.productRepository = productRepository;
        this.discountDtoMapper = discountDtoMapper;
        this.productDtoMapper = productDtoMapper;
        this.productManager = productManager;
    }

    public DiscountDto save(Discount discount) {
        Discount savedDiscount = discountRepository.save(discount);
        return discountDtoMapper.map(savedDiscount);
    }

    @Transactional
    public ProductDto checkForDiscount(Long discountId, Long productId){
        Discount discount = discountRepository.findById(discountId)
                .orElseThrow(DiscountNotFoundException::new);
        if (discount.getPercentage() <= 0 || discount.getPercentage() > 1) {
            throw new IllegalArgumentException("Procent rabatu musi być w zakresie od 0 do 1.");
        }
        Product foundProduct = productRepository.findById(productId).orElseThrow(ProductNotFoundException::new);
        ProductDto product = productDtoMapper.map(foundProduct);
        BigDecimal price = BigDecimal.valueOf(product.getPrice());
        BigDecimal discountPercentage = BigDecimal.valueOf(discount.getPercentage());
        BigDecimal valueToSubtract = price.multiply(discountPercentage);
        BigDecimal amountAfterDiscount = price.subtract(valueToSubtract).setScale(2, RoundingMode.HALF_UP);
        product.setPrice(amountAfterDiscount.doubleValue());
        return product;
    }
}
