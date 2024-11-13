package com.website.system.product;

import com.website.system.client.Client;
import com.website.system.product.datamodel.Product;
import com.website.system.product.datamodel.ProductType;
import com.website.system.product.dto.ProductDto;
import com.website.system.product.dto.ProductDtoMapper;
import jakarta.validation.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class ProductManager {
    private final ProductDtoMapper productDtoMapper;
    private final ProductRepository productRepository;

    public ProductManager(ProductDtoMapper productDtoMapper, ProductRepository productRepository) {
        this.productDtoMapper = productDtoMapper;
        this.productRepository = productRepository;
    }

    public ProductDto addProduct(ProductDto productDto) {
        Product productToSave = productDtoMapper.map(productDto);
        Product savedProduct = productRepository.save(productToSave);
        return productDtoMapper.map(savedProduct);
    }

    public ProductDto addProduct(Product product) {
        Product savedProduct = productRepository.save(product);
        return productDtoMapper.map(savedProduct);
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    public Optional<ProductDto> getProductDtoById(Long id) {
        return productRepository.findById(id).map(productDtoMapper::map);
    }

    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    public List<ProductDto> getAllProductsOfTypeIfAvailable(ProductType productType){
        Iterable<Product> all = productRepository.findAll();
        List<ProductDto> products = new ArrayList<>();
        for(Product product : all){
            if (product.getProductType().equals(productType) && product.getQuantity() > 0){
                products.add(productDtoMapper.map(product));
            }
        }
        return products;
    }

    public List<ProductDto> getAllProducts() {
        List<ProductDto> productList = new ArrayList<>();
        for (Product product : productRepository.findAll()) {
            productList.add(productDtoMapper.map(product));
        }
        return productList;
    }

    public Optional<ProductDto> updateProduct(Long id, ProductDto productDto) {
        Optional<Product> productById = productRepository.findById(id);
        if (productById.isPresent()) {
            Product existingProduct = productById.get();
            existingProduct.setName(productDto.getName());
            existingProduct.setPrice(productDto.getPrice());
            productRepository.save(existingProduct);
            return Optional.of(productDtoMapper.map(existingProduct));
        }
        return Optional.empty();
    }

//    @Transactional
//    public void applyDiscount(Long productId, double percentageOfDiscount){
//        if (percentageOfDiscount <= 0) throw new IllegalArgumentException("Procent rabatu nie może być mniejszy równy zeru!");
//        Product product = productRepository.findById(productId).orElseThrow(ProductNotFoundException::new);
//        double discountAmount = product.getPrice() * percentageOfDiscount;
//        double priceAfterDiscount = product.getPrice() - discountAmount;
//        if(priceAfterDiscount < 0) throw new IllegalArgumentException("Cena po rabacie nie może być mniejsza od zera");
//        product.setPrice(priceAfterDiscount);
//        productRepository.save(product);
//    }
//
//    @Transactional
//    public void applyDiscounts(List<Long> productIds, double percentageOfDiscount){
//        if (percentageOfDiscount <= 0) throw new IllegalArgumentException("Procent rabatu nie może być mniejszy równy zeru!");
//
//        List<Product> products = (List<Product>) productRepository.findAllById(productIds);
//        if (products.size() != productIds.size()) {
//            throw new ProductNotFoundException("Niektóre produkty nie zostały znalezione.");
//        }
//
//        for (Product product : products) {
//            double discountAmount = product.getPrice() * percentageOfDiscount;
//            double priceAfterDiscount = product.getPrice() - discountAmount;
//            if (priceAfterDiscount < 0) throw new IllegalArgumentException("Cena po rabacie nie może być mniejsza od zera");
//            product.setPrice(priceAfterDiscount);
//        }
//        productRepository.saveAll(products);
//    }
//
//    @Transactional
//    public void applyDiscounts(ProductType productType, double percentageOfDiscount){
//        List<ProductDto> productsOfType = getAllProductsOfTypeIfAvailable(productType);
//
//        for (ProductDto product : productsOfType) {
//            double discountAmount = product.getPrice() * percentageOfDiscount;
//            double priceAfterDiscount = product.getPrice() - discountAmount;
//            if (priceAfterDiscount < 0) throw new IllegalArgumentException("Cena po rabacie nie może być mniejsza od zera!");
//            product.setPrice(priceAfterDiscount);
//        }
//        productsOfType.
//        productRepository.saveAll(productsOfType);
//    }
}
