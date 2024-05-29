package zw.co.zim.willplatform.repository.spcecifications;

import org.springframework.data.jpa.domain.Specification;
import zw.co.zim.willplatform.utils.enums.RecordStatus;
import zw.co.zim.willplatform.model.Coupons;

import java.time.LocalDate;

public class CouponSpecification {
    public static Specification<Coupons> hasStatus(RecordStatus recordStatus) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("recordStatus"), recordStatus);
    }

    public static Specification<Coupons> withCode(String code) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("code"), code);
    }

    public static Specification<Coupons> isApplied(Boolean applied) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("applied"), applied);
    }

    public static Specification<Coupons> expiredBefore(LocalDate expiryDate) {
        return (root, query, criteriaBuilder) -> {
            return criteriaBuilder.lessThan(root.get("expiryDate"), expiryDate);
        };
    }

    public static Specification<Coupons> expiresAfter(LocalDate expiryDate) {
        return (root, query, criteriaBuilder) -> {
            return criteriaBuilder.greaterThan(root.get("expiryDate"), expiryDate);
        };
    }
}
