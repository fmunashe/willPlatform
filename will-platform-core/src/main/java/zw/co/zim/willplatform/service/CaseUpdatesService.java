package zw.co.zim.willplatform.service;

import org.springframework.data.domain.Page;
import zw.co.zim.willplatform.common.BaseService;
import zw.co.zim.willplatform.model.CaseUpdate;
import zw.co.zim.willplatform.model.Cases;

import java.util.Optional;

public interface CaseUpdatesService extends BaseService<CaseUpdate> {
    Page<CaseUpdate> findAll(Integer pageNo, Integer pageSize);

    Page<CaseUpdate> findAllByCaseId(Cases caseId, Integer pageNo, Integer pageSize);

    Optional<CaseUpdate> findFirstById(Long caseId);
}
