package zw.co.zim.willplatform.processor;

import zw.co.zim.willplatform.common.BaseProcessor;
import zw.co.zim.willplatform.dto.LiabilitiesOutstandingAccountDto;
import zw.co.zim.willplatform.utils.messages.request.OutstandingAccountRequest;
import zw.co.zim.willplatform.utils.messages.response.basic.ApiResponse;

public interface LiabilitiesOutstandingAccountProcessor extends BaseProcessor<LiabilitiesOutstandingAccountDto, OutstandingAccountRequest> {
    ApiResponse<LiabilitiesOutstandingAccountDto> findAllByUserId(Long clientId, Integer pageNo, Integer pageSize);
}
