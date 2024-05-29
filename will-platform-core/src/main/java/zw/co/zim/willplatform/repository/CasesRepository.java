package zw.co.zim.willplatform.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import zw.co.zim.willplatform.utils.enums.CaseType;
import zw.co.zim.willplatform.utils.enums.RecordStatus;
import zw.co.zim.willplatform.model.Cases;
import zw.co.zim.willplatform.model.SystemUser;

import java.util.Optional;

public interface CasesRepository extends JpaRepository<Cases, Long> {
    Page<Cases> findAllByStatusNot(RecordStatus status, Pageable pageable);

    Page<Cases> findAllByCaseTypeAndStatusNot(CaseType caseType, RecordStatus status, Pageable pageable);

    Page<Cases> findAllByAssignedAgentAndStatusNot(Pageable pageable, SystemUser assignedAgent, RecordStatus status);

    Optional<Cases> findFirstByCaseNumberAndStatusNot(String caseNumber, RecordStatus status);

    Optional<Cases> findFirstByIdAndStatusNot(Long id, RecordStatus status);

    Optional<Cases> findTopByOrderByIdDesc();
}
