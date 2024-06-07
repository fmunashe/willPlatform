package zw.co.zim.willplatform.processor;

import zw.co.zim.willplatform.common.BaseProcessor;
import zw.co.zim.willplatform.dto.CouponsDto;
import zw.co.zim.willplatform.utils.messages.request.CouponRequest;
import zw.co.zim.willplatform.utils.messages.response.basic.ApiResponse;

import java.time.LocalDate;

public interface CouponsProcessor extends BaseProcessor<CouponsDto, CouponRequest> {
    ApiResponse<CouponsDto> findCouponByCode(String couponCode);

    ApiResponse<CouponsDto> findAllPagedAppliedCoupons(Integer pageNo, Integer pageSize, Boolean appliedYes);

    ApiResponse<CouponsDto> findAllExpiredAndNotAppliedCoupons(Integer pageNo, Integer pageSize, LocalDate expiryDate, Boolean appliedNo);

    ApiResponse<CouponsDto> applyCoupon(String couponCode, Long clientId);
}
