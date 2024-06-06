package zw.co.zim.willplatform.processor;


import zw.co.zim.willplatform.common.BaseProcessor;
import zw.co.zim.willplatform.dto.UsersSpouseDto;
import zw.co.zim.willplatform.processor.impl.UsersSpouseProcessorImpl;
import zw.co.zim.willplatform.utils.messages.request.SpouseRequest;
import zw.co.zim.willplatform.utils.messages.response.basic.ApiResponse;

public interface UsersSpouseProcessor extends BaseProcessor<UsersSpouseDto, SpouseRequest> {
    ApiResponse<UsersSpouseDto> findAllByUserId(Long clientId, Integer pageNo, Integer pageSize);
}
