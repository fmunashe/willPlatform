package zw.co.zim.willplatform.processor;

import zw.co.zim.willplatform.common.BaseProcessor;
import zw.co.zim.willplatform.dto.WillCommentsDto;
import zw.co.zim.willplatform.processor.impl.WillCommentsProcessorImpl;
import zw.co.zim.willplatform.utils.messages.request.WillCommentsRequest;
import zw.co.zim.willplatform.utils.messages.response.basic.ApiResponse;

public interface WillCommentsProcessor extends BaseProcessor<WillCommentsDto, WillCommentsRequest> {
    ApiResponse<WillCommentsDto> findAllByUserId(Long clientId, Integer pageNo, Integer pageSize);
}
