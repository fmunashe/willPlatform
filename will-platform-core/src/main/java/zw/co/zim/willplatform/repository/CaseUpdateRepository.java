package zw.co.zim.willplatform.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import zw.co.zim.willplatform.enums.RecordStatus;
import zw.co.zim.willplatform.model.CaseUpdate;
import zw.co.zim.willplatform.model.Cases;

import java.util.Optional;

public interface CaseUpdateRepository extends JpaRepository<CaseUpdate, Long> {
    Page<CaseUpdate> findAllByStatusNot(Pageable pageable, RecordStatus status);
    Page<CaseUpdate> findAllByCaseIdAndStatusNot(Pageable pageable, Cases caseId, RecordStatus status);
    Optional<CaseUpdate> findFirstByIdAndStatusNot(Long caseId, RecordStatus status);
}
