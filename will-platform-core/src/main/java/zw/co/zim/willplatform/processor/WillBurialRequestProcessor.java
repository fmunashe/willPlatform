package zw.co.zim.willplatform.processor;

import zw.co.zim.willplatform.common.BaseProcessor;
import zw.co.zim.willplatform.dto.WillBurialRequestDto;
import zw.co.zim.willplatform.processor.impl.WillBurialRequestProcessorImpl;
import zw.co.zim.willplatform.utils.messages.request.BurialRequest;
import zw.co.zim.willplatform.utils.messages.response.basic.ApiResponse;

public interface WillBurialRequestProcessor extends BaseProcessor<WillBurialRequestDto, BurialRequest> {
    ApiResponse<WillBurialRequestDto> findFirstByUserId(Long clientId);
}
