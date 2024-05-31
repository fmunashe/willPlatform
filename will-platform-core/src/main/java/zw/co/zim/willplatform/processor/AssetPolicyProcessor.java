package zw.co.zim.willplatform.processor;

import zw.co.zim.willplatform.common.BaseProcessor;
import zw.co.zim.willplatform.dto.AssetPolicyRecordDto;
import zw.co.zim.willplatform.utils.messages.request.AssetPolicyRequest;
import zw.co.zim.willplatform.utils.messages.response.basic.ApiResponse;

public interface AssetPolicyProcessor extends BaseProcessor<AssetPolicyRecordDto, AssetPolicyRequest> {
    ApiResponse<AssetPolicyRecordDto> findFirstByPolicyNumber(String policyNumber);

    ApiResponse<AssetPolicyRecordDto> findAllByUserId(Long clientId, Integer pageNo, Integer pageSize);

}
