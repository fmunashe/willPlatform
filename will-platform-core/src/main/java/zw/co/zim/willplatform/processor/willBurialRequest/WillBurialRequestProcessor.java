package zw.co.zim.willplatform.processor.willBurialRequest;

import zw.co.zim.willplatform.common.BaseProcessor;
import zw.co.zim.willplatform.dto.WillBurialRequestDto;
import zw.co.zim.willplatform.utils.messages.request.BurialRequest;
import zw.co.zim.willplatform.utils.messages.response.basic.ApiResponse;

public sealed interface WillBurialRequestProcessor extends BaseProcessor<WillBurialRequestDto, BurialRequest> permits WillBurialRequestProcessorImpl {
    ApiResponse<WillBurialRequestDto> findFirstByUserId(Long clientId);
}
