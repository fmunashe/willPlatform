package zw.co.zim.willplatform.processor;

import zw.co.zim.willplatform.common.BaseProcessor;
import zw.co.zim.willplatform.dto.SignupProgressJourneyDto;
import zw.co.zim.willplatform.utils.messages.response.basic.ApiResponse;

import java.util.Map;

public interface SignupProgressJourneyProcessor extends BaseProcessor<SignupProgressJourneyDto, SignupProgressJourneyDto> {
    ApiResponse<SignupProgressJourneyDto> findFirstByClientId(Long clientId);

    ApiResponse<Map<String, Long>> signupProgressStatistics();
}
