package zw.co.zim.willplatform.processor;

import zw.co.zim.willplatform.common.BaseProcessor;
import zw.co.zim.willplatform.dto.AssetInvestmentRecordDto;
import zw.co.zim.willplatform.utils.messages.request.AssetInvestmentRequest;
import zw.co.zim.willplatform.utils.messages.response.basic.ApiResponse;

public interface AssetInvestmentProcessor extends BaseProcessor<AssetInvestmentRecordDto, AssetInvestmentRequest> {
    ApiResponse<AssetInvestmentRecordDto> findAllByInvestmentType(String investmentType, Integer pageNo, Integer pageSize);

    ApiResponse<AssetInvestmentRecordDto> findAllByUserId(Long clientId, Integer pageNo, Integer pageSize);
}
