package zw.co.zim.willplatform.service;

import org.springframework.data.domain.Page;
import zw.co.zim.willplatform.common.BaseService;
import zw.co.zim.willplatform.model.Client;
import zw.co.zim.willplatform.utils.enums.CaseType;
import zw.co.zim.willplatform.model.Cases;
import zw.co.zim.willplatform.model.SystemUser;

import java.util.Optional;

public interface CaseService extends BaseService<Cases> {
    Page<Cases> findAll(Integer pageNo, Integer pageSize);

    Page<Cases> findAllByCaseType(CaseType caseType, Integer pageNo, Integer pageSize);

    Page<Cases> findAllByAssignedAgent(SystemUser assignedAgent, Integer pageNo, Integer pageSize);
    Page<Cases> findAllByClient(Client client, Integer pageNo, Integer pageSize);

    Optional<Cases> findFirstByCaseNumber(String caseNumber);
    Optional<Cases> findLatestCase();
}
