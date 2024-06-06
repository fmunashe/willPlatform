package zw.co.zim.willplatform.processor.willPasswordInstructions;

import org.springframework.data.domain.Page;
import zw.co.zim.willplatform.common.BaseProcessor;
import zw.co.zim.willplatform.dto.WillPasswordInstructionsDto;
import zw.co.zim.willplatform.model.Client;
import zw.co.zim.willplatform.model.WillPasswordsInstructions;
import zw.co.zim.willplatform.utils.messages.request.WillPasswordInstructionsRequest;
import zw.co.zim.willplatform.utils.messages.response.basic.ApiResponse;

public sealed interface WillPasswordInstructionsProcessor extends BaseProcessor<WillPasswordInstructionsDto, WillPasswordInstructionsRequest> permits WillPasswordInstructionsProcessorImpl{
    ApiResponse<WillPasswordInstructionsDto> findAllByUserId(Long userId, Integer pageNo, Integer pageSize);
}
