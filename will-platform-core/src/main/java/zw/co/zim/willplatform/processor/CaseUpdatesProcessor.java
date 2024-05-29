package zw.co.zim.willplatform.processor;

import zw.co.zim.willplatform.common.ProcessorService;
import zw.co.zim.willplatform.dto.CaseUpdatesDto;
import zw.co.zim.willplatform.utils.messages.response.basic.ApiResponse;

public interface CaseUpdatesProcessor extends ProcessorService<CaseUpdatesDto,CaseUpdatesDto> {
    ApiResponse<CaseUpdatesDto> findAll(Integer pageNo, Integer pageSize);

    ApiResponse<CaseUpdatesDto> findAllByCaseId(Long caseId, Integer pageNo, Integer pageSize);
}
