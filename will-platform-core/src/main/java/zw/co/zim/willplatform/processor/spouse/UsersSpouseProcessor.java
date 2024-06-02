package zw.co.zim.willplatform.processor.spouse;


import zw.co.zim.willplatform.common.BaseProcessor;
import zw.co.zim.willplatform.dto.UsersSpouseDto;
import zw.co.zim.willplatform.utils.messages.request.SpouseRequest;
import zw.co.zim.willplatform.utils.messages.response.basic.ApiResponse;

public sealed interface UsersSpouseProcessor extends BaseProcessor<UsersSpouseDto, SpouseRequest> permits UsersSpouseProcessorImpl {
    ApiResponse<UsersSpouseDto> findAllByUserId(Long clientId, Integer pageNo, Integer pageSize);
}
