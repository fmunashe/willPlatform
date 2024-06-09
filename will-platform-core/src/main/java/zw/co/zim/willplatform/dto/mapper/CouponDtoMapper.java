package zw.co.zim.willplatform.dto.mapper;

import org.springframework.stereotype.Service;
import zw.co.zim.willplatform.dto.CouponsDto;
import zw.co.zim.willplatform.model.Coupons;

import java.util.function.Function;

@Service
public class CouponDtoMapper implements Function<Coupons, CouponsDto> {
    @Override
    public CouponsDto apply(Coupons coupons) {
        return CouponsDto.builder()
            .id(coupons.getId())
            .code(coupons.getCode())
            .discount(coupons.getDiscount())
            .expiryDate(coupons.getExpiryDate())
            .applied(coupons.isApplied())
            .productId(coupons.getProductId().getId())
            .createdAt(coupons.getCreatedAt())
            .updatedAt(coupons.getUpdatedAt())
            .recordStatus(coupons.getRecordStatus())
            .build();
    }
}
