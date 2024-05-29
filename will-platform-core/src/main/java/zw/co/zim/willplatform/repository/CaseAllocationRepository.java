package zw.co.zim.willplatform.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import zw.co.zim.willplatform.utils.enums.CaseType;
import zw.co.zim.willplatform.utils.enums.RecordStatus;
import zw.co.zim.willplatform.model.CaseAllocation;
import zw.co.zim.willplatform.model.Cases;

import java.util.List;
import java.util.Optional;

public interface CaseAllocationRepository extends JpaRepository<CaseAllocation, Long> {
    List<CaseAllocation> findAllByRecordStatusNot(RecordStatus recordStatus);

    Page<CaseAllocation> findAllByRecordStatusNot(Pageable pageable, RecordStatus recordStatus);

    Page<CaseAllocation> findAllByCaseTypeAndRecordStatusNot(CaseType caseType, Pageable pageable, RecordStatus recordStatus);

    Optional<CaseAllocation> findFirstByCaseIdAndRecordStatusNot(Cases caseId, RecordStatus recordStatus);

    Optional<CaseAllocation> findFirstByIdAndRecordStatusNot(Long caseId, RecordStatus recordStatus);
}
