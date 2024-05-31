package zw.co.zim.willplatform.processor;

import zw.co.zim.willplatform.common.BaseProcessor;
import zw.co.zim.willplatform.dto.LiabilitiesUserOwesMoneyDto;
import zw.co.zim.willplatform.utils.messages.request.UserOwesMoneyRequest;
import zw.co.zim.willplatform.utils.messages.response.basic.ApiResponse;

public interface LiabilitiesUserOwesMoneyProcessor extends BaseProcessor<LiabilitiesUserOwesMoneyDto, UserOwesMoneyRequest> {
    ApiResponse<LiabilitiesUserOwesMoneyDto> findAllByUserId(Long clientId, Integer pageNo, Integer pageSize);
}
