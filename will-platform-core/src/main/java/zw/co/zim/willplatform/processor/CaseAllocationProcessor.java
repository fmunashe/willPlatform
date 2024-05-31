package zw.co.zim.willplatform.processor;

import zw.co.zim.willplatform.common.BaseProcessor;
import zw.co.zim.willplatform.dto.CaseAllocationDto;
import zw.co.zim.willplatform.utils.messages.response.basic.ApiResponse;

public interface CaseAllocationProcessor extends BaseProcessor<CaseAllocationDto,CaseAllocationDto> {

    ApiResponse<CaseAllocationDto> findAllByCaseType(String caseType, Integer pageNo, Integer pageSize);

    ApiResponse<CaseAllocationDto> findFirstByCaseId(Long caseId);
}
