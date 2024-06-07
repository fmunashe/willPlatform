package zw.co.zim.willplatform.service;

import org.springframework.data.domain.Page;
import zw.co.zim.willplatform.common.BaseService;
import zw.co.zim.willplatform.dto.CouponsDto;
import zw.co.zim.willplatform.model.Client;
import zw.co.zim.willplatform.model.Coupons;
import zw.co.zim.willplatform.utils.messages.response.basic.ApiResponse;

import java.time.LocalDate;
import java.util.Optional;

public interface CouponService extends BaseService<Coupons> {
    Page<Coupons> findAll(Integer pageNo, Integer pageSize);

    Optional<Coupons> findCouponByCode(String couponCode);

    Page<Coupons> findAllPagedAppliedCoupons(Integer pageNo, Integer pageSize, Boolean appliedYes);

    Page<Coupons> findAllExpiredAndNotAppliedCoupons(Integer pageNo, Integer pageSize, LocalDate expiryDate, Boolean appliedNo);
    Coupons applyCoupon(Coupons coupon);
}
