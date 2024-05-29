package zw.co.zim.willplatform.service;

import org.springframework.data.domain.Page;
import zw.co.zim.willplatform.common.AppService;
import zw.co.zim.willplatform.utils.enums.CaseType;
import zw.co.zim.willplatform.model.CaseAllocation;
import zw.co.zim.willplatform.model.Cases;

import java.util.Optional;

public interface CaseAllocationService extends AppService<CaseAllocation> {

    Page<CaseAllocation> findAll(Integer pageNo, Integer pageSize);

    Page<CaseAllocation> findAllByCaseType(CaseType caseType, Integer pageNo, Integer pageSize);

    Optional<CaseAllocation> findFirstByCaseId(Cases caseId);
}
