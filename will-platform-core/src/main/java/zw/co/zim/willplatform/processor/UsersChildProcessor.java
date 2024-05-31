package zw.co.zim.willplatform.processor;

import zw.co.zim.willplatform.common.BaseProcessor;
import zw.co.zim.willplatform.dto.UsersChildDto;
import zw.co.zim.willplatform.utils.messages.request.UsersChildRequest;
import zw.co.zim.willplatform.utils.messages.response.basic.ApiResponse;

public interface UsersChildProcessor extends BaseProcessor<UsersChildDto, UsersChildRequest> {
    ApiResponse<UsersChildDto> findAllByUserId(Long clientId, Integer pageNo, Integer pageSize);
}
