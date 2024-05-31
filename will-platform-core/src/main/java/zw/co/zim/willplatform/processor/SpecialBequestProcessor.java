package zw.co.zim.willplatform.processor;

import zw.co.zim.willplatform.common.BaseProcessor;
import zw.co.zim.willplatform.dto.SpecialBequestDto;
import zw.co.zim.willplatform.utils.messages.request.SpecialBequestRequest;
import zw.co.zim.willplatform.utils.messages.response.basic.ApiResponse;

public interface SpecialBequestProcessor extends BaseProcessor<SpecialBequestDto, SpecialBequestRequest> {
    ApiResponse<SpecialBequestDto> findFirstByUserId(Long clientId);
}
