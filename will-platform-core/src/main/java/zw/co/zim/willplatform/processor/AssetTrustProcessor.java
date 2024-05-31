package zw.co.zim.willplatform.processor;

import zw.co.zim.willplatform.common.BaseProcessor;
import zw.co.zim.willplatform.dto.AssetTrustDto;
import zw.co.zim.willplatform.utils.messages.request.AssetTrustRequest;
import zw.co.zim.willplatform.utils.messages.response.basic.ApiResponse;

public interface AssetTrustProcessor extends BaseProcessor<AssetTrustDto, AssetTrustRequest> {
    ApiResponse<AssetTrustDto> findAllByUserId(Long clientId, Integer pageNo, Integer pageSize);
}
