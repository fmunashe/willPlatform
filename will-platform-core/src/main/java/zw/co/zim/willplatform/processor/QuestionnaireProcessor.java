package zw.co.zim.willplatform.processor;

import zw.co.zim.willplatform.common.BaseProcessor;
import zw.co.zim.willplatform.dto.QuestionnaireDto;
import zw.co.zim.willplatform.utils.messages.request.QuestionnaireRequest;
import zw.co.zim.willplatform.utils.messages.response.basic.ApiResponse;

public interface QuestionnaireProcessor extends BaseProcessor<QuestionnaireDto, QuestionnaireRequest> {
    ApiResponse<QuestionnaireDto> findFirstByUserId(Long clientId);
}
