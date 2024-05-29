package zw.co.zim.willplatform.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import zw.co.zim.willplatform.utils.enums.RecordStatus;
import zw.co.zim.willplatform.model.TransferFees;

import java.util.List;
import java.util.Optional;

public interface TransferFeesRepository extends JpaRepository<TransferFees, Long> {
    List<TransferFees> findAllByRecordStatusNot(RecordStatus recordStatus);

    Page<TransferFees> findAllByRecordStatusNot(Pageable pageable, RecordStatus recordStatus);

    Optional<TransferFees> findFirstByIdAndRecordStatusNot(Long id, RecordStatus recordStatus);
}
