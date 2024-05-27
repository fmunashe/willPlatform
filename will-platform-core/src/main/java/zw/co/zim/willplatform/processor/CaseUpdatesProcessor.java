package zw.co.zim.willplatform.processor;

import zw.co.zim.willplatform.common.ProcessorService;
import zw.co.zim.willplatform.dto.CaseUpdatesDto;
import zw.co.zim.willplatform.messages.response.basic.ApiResponse;

import java.util.Optional;

public interface CaseUpdatesProcessor extends ProcessorService<CaseUpdatesDto> {
    ApiResponse<CaseUpdatesDto> findAll(Integer pageNo, Integer pageSize);

    ApiResponse<CaseUpdatesDto> findAllByCaseId(Long caseId, Integer pageNo, Integer pageSize);

    ApiResponse<CaseUpdatesDto> findFirstById(Long caseId);
}
