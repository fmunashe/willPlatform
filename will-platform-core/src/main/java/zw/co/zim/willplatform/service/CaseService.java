package zw.co.zim.willplatform.service;

import org.springframework.data.domain.Page;
import zw.co.zim.willplatform.common.AppService;
import zw.co.zim.willplatform.enums.CaseType;
import zw.co.zim.willplatform.model.Cases;
import zw.co.zim.willplatform.model.SystemUser;

import java.util.Optional;

public interface CaseService extends AppService<Cases> {
    Page<Cases> findAll(Integer pageNo, Integer pageSize);

    Page<Cases> findAllByCaseType(CaseType caseType, Integer pageNo, Integer pageSize);

    Page<Cases> findAllByAssignedAgent(SystemUser assignedAgent, Integer pageNo, Integer pageSize);

    Optional<Cases> findFirstByCaseNumber(String caseNumber);
}
