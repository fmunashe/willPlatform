package zw.co.zim.willplatform.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import zw.co.zim.willplatform.enums.RecordStatus;
import zw.co.zim.willplatform.model.Coupons;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface CouponsRepository extends JpaRepository<Coupons, Long> {
    List<Coupons> findAllByRecordStatusNot(Pageable pageable, RecordStatus recordStatus);

    //not expired
    List<Coupons> findAllByExpiryDateGreaterThanEqualAndRecordStatusNot(Pageable pageable, LocalDate expiryDate, RecordStatus recordStatus);

    //expired but not applied
    List<Coupons> findAllByExpiryDateLessThanAndAppliedAndRecordStatusNot(Pageable pageable, LocalDate expiryDate, Boolean applied, RecordStatus recordStatus);

    Optional<Coupons> findFirstByCodeAndRecordStatusNot(String couponCode, RecordStatus recordStatus);
    Optional<Coupons> findFirstByIdAndRecordStatusNot(Long id, RecordStatus recordStatus);
}
