package zw.co.zim.willplatform.processor;

import zw.co.zim.willplatform.common.BaseProcessor;
import zw.co.zim.willplatform.dto.CaseDto;
import zw.co.zim.willplatform.utils.messages.response.basic.ApiResponse;

public interface CaseServiceProcessor extends BaseProcessor<CaseDto,CaseDto> {
    ApiResponse<CaseDto> findAllByCaseType(String caseType, Integer pageNo, Integer pageSize);

    ApiResponse<CaseDto> findAllByAssignedAgent(Long assignedAgent, Integer pageNo, Integer pageSize);

    ApiResponse<CaseDto> findFirstByCaseNumber(String caseNumber);
}
