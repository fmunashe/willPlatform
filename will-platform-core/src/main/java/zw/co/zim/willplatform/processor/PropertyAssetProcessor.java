package zw.co.zim.willplatform.processor;

import zw.co.zim.willplatform.common.BaseProcessor;
import zw.co.zim.willplatform.dto.PropertyAssetRecordDto;
import zw.co.zim.willplatform.utils.messages.request.PropertyAssetRequest;
import zw.co.zim.willplatform.utils.messages.response.basic.ApiResponse;

public interface PropertyAssetProcessor extends BaseProcessor<PropertyAssetRecordDto, PropertyAssetRequest> {
    ApiResponse<PropertyAssetRecordDto> findAllByUserId(Long clientId, Integer pageNo, Integer pageSize);
}
