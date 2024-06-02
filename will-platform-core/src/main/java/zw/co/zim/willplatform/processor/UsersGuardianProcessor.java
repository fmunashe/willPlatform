package zw.co.zim.willplatform.processor;

import zw.co.zim.willplatform.common.BaseProcessor;
import zw.co.zim.willplatform.dto.UsersGuardianDto;
import zw.co.zim.willplatform.utils.messages.request.GuardianRequest;
import zw.co.zim.willplatform.utils.messages.response.basic.ApiResponse;

public interface UsersGuardianProcessor extends BaseProcessor<UsersGuardianDto, GuardianRequest> {
    ApiResponse<UsersGuardianDto> findAllByUserId(Long userId, Integer pageNo, Integer pageSize);
}
