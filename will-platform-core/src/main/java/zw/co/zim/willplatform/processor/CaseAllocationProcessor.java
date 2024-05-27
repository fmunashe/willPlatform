package zw.co.zim.willplatform.processor;

import zw.co.zim.willplatform.common.ProcessorService;
import zw.co.zim.willplatform.dto.CaseAllocationDto;
import zw.co.zim.willplatform.enums.CaseType;
import zw.co.zim.willplatform.messages.response.basic.ApiResponse;
import zw.co.zim.willplatform.model.Cases;

public interface CaseAllocationProcessor extends ProcessorService<CaseAllocationDto> {
    ApiResponse<CaseAllocationDto> findAll(Integer pageNo, Integer pageSize);

    ApiResponse<CaseAllocationDto> findAllByCaseType(String caseType, Integer pageNo, Integer pageSize);

    ApiResponse<CaseAllocationDto> findFirstByCaseId(Long caseId);
}
