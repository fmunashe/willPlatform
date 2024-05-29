package zw.co.zim.willplatform.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import zw.co.zim.willplatform.utils.enums.RecordStatus;
import zw.co.zim.willplatform.model.Coupons;

import java.util.Optional;

public interface CouponsRepository extends JpaRepository<Coupons, Long>, JpaSpecificationExecutor<Coupons> {
    Page<Coupons> findAllByRecordStatusNot(Pageable pageable, RecordStatus recordStatus);

    Optional<Coupons> findFirstByIdAndRecordStatusNot(Long id, RecordStatus recordStatus);
}
