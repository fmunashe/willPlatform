package zw.co.zim.willplatform.processor;

import zw.co.zim.willplatform.common.ProcessorService;
import zw.co.zim.willplatform.dto.AssetTrustDto;
import zw.co.zim.willplatform.model.AssetTrust;
import zw.co.zim.willplatform.utils.messages.request.AssetTrustRequest;
import zw.co.zim.willplatform.utils.messages.response.basic.ApiResponse;

public interface AssetTrustProcessor extends ProcessorService<AssetTrustDto, AssetTrustRequest> {
    ApiResponse<AssetTrustDto> findAllByUserId(Long clientId, Integer pageNo, Integer pageSize);
}
