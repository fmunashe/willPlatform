package zw.co.zim.willplatform.processor;

import zw.co.zim.willplatform.common.BaseProcessor;
import zw.co.zim.willplatform.dto.WillAlternativeExecutorDto;
import zw.co.zim.willplatform.utils.messages.request.AlternativeExecutorRequest;
import zw.co.zim.willplatform.utils.messages.response.basic.ApiResponse;

public interface WillAlternativeExecutorProcessor extends BaseProcessor<WillAlternativeExecutorDto, AlternativeExecutorRequest>  {
    ApiResponse<WillAlternativeExecutorDto> findAllByUserId(Long clientId, Integer pageNo, Integer pageSize);
}
