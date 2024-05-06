package zw.co.zim.willplatform.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import zw.co.zim.willplatform.enums.CaseType;
import zw.co.zim.willplatform.enums.RecordStatus;
import zw.co.zim.willplatform.model.Cases;
import zw.co.zim.willplatform.model.SystemUser;

import java.util.List;
import java.util.Optional;

public interface CasesRepository extends JpaRepository<Cases, Long> {
    List<Cases> findAllByStatusNot(RecordStatus status, Pageable pageable);

    List<Cases> findAllByCaseTypeAndStatusNot(CaseType caseType, RecordStatus status, Pageable pageable);

    List<Cases> findAllByAssignedAgentAndStatusNot(Pageable pageable, SystemUser assignedAgent, RecordStatus status);

    Optional<Cases> findFirstByCaseNumberAndStatusNot(String caseNumber, RecordStatus status);

    Optional<Cases> findFirstByIdAndStatusNot(Long id, RecordStatus status);

}
