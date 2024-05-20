package zw.co.zim.willplatform.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import zw.co.zim.willplatform.enums.RecordStatus;
import zw.co.zim.willplatform.model.Client;
import zw.co.zim.willplatform.model.LiabilitiesOutstandingLoan;

import java.util.List;
import java.util.Optional;

public interface LiabilitiesOutstandingLoanRepository extends JpaRepository<LiabilitiesOutstandingLoan, Long> {
    Page<LiabilitiesOutstandingLoan> findAllByRecordStatusNot(Pageable pageable, RecordStatus recordStatus);

    Page<LiabilitiesOutstandingLoan> findAllByUserIdAndRecordStatusNot(Pageable pageable, Client clientId, RecordStatus recordStatus);

    Optional<LiabilitiesOutstandingLoan> findFirstByIdAndRecordStatusNot(Long id, RecordStatus recordStatus);
}
