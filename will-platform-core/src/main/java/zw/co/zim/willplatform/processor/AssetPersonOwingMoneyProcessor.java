package zw.co.zim.willplatform.processor;

import zw.co.zim.willplatform.common.BaseProcessor;
import zw.co.zim.willplatform.dto.AssetPersonOwingMoneyRecordDto;
import zw.co.zim.willplatform.utils.messages.request.AssetPersonOwingMoneyRequest;
import zw.co.zim.willplatform.utils.messages.response.basic.ApiResponse;

public interface AssetPersonOwingMoneyProcessor extends BaseProcessor<AssetPersonOwingMoneyRecordDto, AssetPersonOwingMoneyRequest> {
    ApiResponse<AssetPersonOwingMoneyRecordDto> findAllByUserId(Long clientId, Integer pageNo, Integer pageSize);
}
