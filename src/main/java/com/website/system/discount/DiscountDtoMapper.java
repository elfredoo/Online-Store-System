package com.website.system.discount;

import com.website.system.product.dto.ProductDtoMapper;
import org.springframework.stereotype.Service;

@Service
public class DiscountDtoMapper {
    private final DiscountRepository discountRepository;
    private final ProductDtoMapper productDtoMapper;

    public DiscountDtoMapper(DiscountRepository discountRepository, ProductDtoMapper productDtoMapper) {
        this.discountRepository = discountRepository;
        this.productDtoMapper = productDtoMapper;
    }

    public Discount map(DiscountDto dto){
        if (dto.getId() != null) {
            return discountRepository.findById(dto.getId()).orElseThrow(DiscountNotFoundException::new);
        }
        Discount discount = new Discount();
        discount.setName(dto.getName());
        discount.setPercentage(dto.getPercentage());
        discount.setStartDate(dto.getStartDate());
        discount.setEndDate(dto.getEndDate());
        if (dto.getProducts() != null) {
            discount.setApplicableProducts(dto.getProducts()
                    .stream()
                    .map(productDtoMapper::map)
                    .toList());
        }
        return discount;
    }

    public DiscountDto map(Discount discount){
        DiscountDto dto = new DiscountDto();
        dto.setId(discount.getId());
        dto.setName(discount.getName());
        dto.setPercentage(discount.getPercentage());
        dto.setStartDate(discount.getStartDate());
        dto.setEndDate(discount.getEndDate());
        if (discount.getApplicableProducts() != null) {
            dto.setProducts(discount.getApplicableProducts()
                    .stream()
                    .map(productDtoMapper::map)
                    .toList());
        }
        return dto;
    }
}
