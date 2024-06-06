package zw.co.zim.willplatform.processor;

import zw.co.zim.willplatform.common.BaseProcessor;
import zw.co.zim.willplatform.dto.WillPasswordInstructionsDto;
import zw.co.zim.willplatform.processor.impl.WillPasswordInstructionsProcessorImpl;
import zw.co.zim.willplatform.utils.messages.request.WillPasswordInstructionsRequest;
import zw.co.zim.willplatform.utils.messages.response.basic.ApiResponse;

public interface WillPasswordInstructionsProcessor extends BaseProcessor<WillPasswordInstructionsDto, WillPasswordInstructionsRequest>  {
    ApiResponse<WillPasswordInstructionsDto> findAllByUserId(Long userId, Integer pageNo, Integer pageSize);
}
