package zw.co.zim.willplatform.processor;

import zw.co.zim.willplatform.common.BaseProcessor;
import zw.co.zim.willplatform.dto.TotalDemiseDto;
import zw.co.zim.willplatform.utils.messages.request.TotalDemiseRequest;
import zw.co.zim.willplatform.utils.messages.response.basic.ApiResponse;

public interface TotalDemiseProcessor extends BaseProcessor<TotalDemiseDto, TotalDemiseRequest> {
    ApiResponse<TotalDemiseDto> findAllByUserId(Long clientId, Integer pageNo, Integer pageSize);
}
