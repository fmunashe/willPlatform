package zw.co.zim.willplatform.processor;

import zw.co.zim.willplatform.common.BaseProcessor;
import zw.co.zim.willplatform.dto.AssetTimeShareDto;
import zw.co.zim.willplatform.utils.messages.request.TimeshareRequest;
import zw.co.zim.willplatform.utils.messages.response.basic.ApiResponse;

public interface AssetTimeshareProcessor extends BaseProcessor<AssetTimeShareDto, TimeshareRequest> {
    ApiResponse<AssetTimeShareDto> findAllByUserId(Long clientId, Integer pageNo, Integer pageSize);
}
