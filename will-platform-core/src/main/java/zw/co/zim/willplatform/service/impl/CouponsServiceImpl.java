package zw.co.zim.willplatform.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import zw.co.zim.willplatform.model.Coupons;
import zw.co.zim.willplatform.repository.CouponsRepository;
import zw.co.zim.willplatform.repository.spcecifications.CouponSpecification;
import zw.co.zim.willplatform.service.CouponService;
import zw.co.zim.willplatform.utils.PageableHelper;
import zw.co.zim.willplatform.utils.enums.RecordStatus;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class CouponsServiceImpl implements CouponService {
    private final CouponsRepository repository;

    public CouponsServiceImpl(CouponsRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Coupons> findAll() {
        Specification<Coupons> spec = Specification.where(CouponSpecification.hasStatus(RecordStatus.ACTIVE));
        return repository.findAll(spec);
    }

    @Override
    public Page<Coupons> findAll(Integer pageNo, Integer pageSize) {
        pageNo = PageableHelper.cleanPageNumber(pageNo);
        pageSize = PageableHelper.cleanPageSize(pageSize);
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.Direction.DESC, "id");
        Specification<Coupons> spec = Specification.where(CouponSpecification.hasStatus(RecordStatus.ACTIVE));
        return repository.findAll(spec, pageable);
    }

    @Override
    public Page<Coupons> findAllExpiredAndNotAppliedCoupons(Integer pageNo, Integer pageSize, LocalDate expiryDate, Boolean appliedNo) {
        pageNo = PageableHelper.cleanPageNumber(pageNo);
        pageSize = PageableHelper.cleanPageSize(pageSize);
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.Direction.DESC, "id");
        Specification<Coupons> spec = Specification.where(CouponSpecification.hasStatus(RecordStatus.ACTIVE))
            .and(CouponSpecification.isApplied(appliedNo))
            .and(CouponSpecification.expiredBefore(expiryDate));
        return repository.findAll(spec, pageable);
    }

    @Override
    public Optional<Coupons> findCouponByCode(String couponCode) {
        Specification<Coupons> spec = Specification.where(CouponSpecification.withCode(couponCode))
            .and(CouponSpecification.hasStatus(RecordStatus.ACTIVE));
        return repository.findOne(spec);
    }

    @Override
    public Page<Coupons> findAllPagedAppliedCoupons(Integer pageNo, Integer pageSize, Boolean appliedYes) {
        pageNo = PageableHelper.cleanPageNumber(pageNo);
        pageSize = PageableHelper.cleanPageSize(pageSize);
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.Direction.DESC, "id");
        Specification<Coupons> spec = Specification.where(CouponSpecification.hasStatus(RecordStatus.ACTIVE))
            .and(CouponSpecification.isApplied(appliedYes));
        return repository.findAll(spec, pageable);
    }

    @Override
    public Optional<Coupons> findById(Long id) {
        return repository.findFirstByIdAndRecordStatusNot(id, RecordStatus.DELETED);
    }

    @Override
    public Coupons save(Coupons coupons) {
        return repository.save(coupons);
    }

    @Override
    public Coupons update(Long id, Coupons coupons) {
        Optional<Coupons> optional = this.findById(id);
        if (optional.isPresent()) {
            Coupons coupon = optional.get();
            coupon.setCode(coupons.getCode());
            coupon.setApplied(coupons.isApplied());
            coupon.setDiscount(coupon.getDiscount());
            coupon.setUserId(coupons.getUserId());
            coupon.setExpiryDate(coupons.getExpiryDate());
            coupon.setProductId(coupons.getProductId());
            coupon.setRecordStatus(coupons.getRecordStatus());
            return repository.save(coupon);
        }
        return coupons;
    }

    @Override
    public void deleteById(Long id) {
        Optional<Coupons> optional = this.findById(id);
        if (optional.isPresent()) {
            Coupons coupon = optional.get();
            coupon.setRecordStatus(RecordStatus.DELETED);
            repository.save(coupon);
        }
    }

    @Override
    public Coupons applyCoupon(Coupons coupon) {
        return repository.save(coupon);
    }
}
