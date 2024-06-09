package zw.co.zim.willplatform.dto.mapper;

import org.springframework.stereotype.Service;
import zw.co.zim.willplatform.dto.ProductsDto;
import zw.co.zim.willplatform.model.Products;

import java.util.function.Function;

@Service
public class ProductsDtoMapper implements Function<Products, ProductsDto> {
    @Override
    public ProductsDto apply(Products products) {
        return ProductsDto.builder()
            .id(products.getId())
            .name(products.getName())
            .price(products.getPrice())
            .description(products.getDescription())
            .currency(products.getCurrency())
            .createdAt(products.getCreatedAt())
            .updatedAt(products.getUpdatedAt())
            .recordStatus(products.getRecordStatus())
            .build();
    }
}
