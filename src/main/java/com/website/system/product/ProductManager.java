package com.website.system.product;

import com.website.system.product.datamodel.Product;
import com.website.system.product.datamodel.ProductType;
import com.website.system.product.dto.ProductDto;
import com.website.system.product.dto.ProductDtoMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    public List<ProductDto> getAllProductsOfType(ProductType productType){
        Iterable<Product> all = productRepository.findAll();
        List<ProductDto> productDtos = new ArrayList<>();
        for(Product product : all){
            if (product.getProductType().equals(productType)){
                productDtos.add(productDtoMapper.map(product));
            }
        }
        return productDtos;
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

    //-------------->>probably will be deleted<<-----------------------

//    @Transactional
//    public void placeOrder(List<ProductDto> productDtos) {
//        for (T product : products) {
//            if(product.getQuantity() <= 1) {
//                productRepository.delete(product);
//            }
//            else{
//                minusQuantity(product);
//                productRepository.save(product);
//            }
//        }
//    }
//
//    private <T extends Product> void minusQuantity(T product) {
//        product.setQuantity(product.getQuantity() - 1);
//    }
}
