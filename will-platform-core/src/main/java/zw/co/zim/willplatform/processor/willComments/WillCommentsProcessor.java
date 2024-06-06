package zw.co.zim.willplatform.processor.willComments;

import zw.co.zim.willplatform.common.BaseProcessor;
import zw.co.zim.willplatform.dto.WillCommentsDto;
import zw.co.zim.willplatform.utils.messages.request.WillCommentsRequest;
import zw.co.zim.willplatform.utils.messages.response.basic.ApiResponse;

public sealed interface WillCommentsProcessor extends BaseProcessor<WillCommentsDto, WillCommentsRequest> permits WillCommentsProcessorImpl {
    ApiResponse<WillCommentsDto> findAllByUserId(Long clientId, Integer pageNo, Integer pageSize);
}
