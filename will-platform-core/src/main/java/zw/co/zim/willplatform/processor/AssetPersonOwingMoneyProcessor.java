package zw.co.zim.willplatform.processor;

import org.springframework.data.domain.Page;
import zw.co.zim.willplatform.common.ProcessorService;
import zw.co.zim.willplatform.dto.AssetPersonOwingMoneyRecordDto;
import zw.co.zim.willplatform.model.AssetPersonOwingMoney;
import zw.co.zim.willplatform.model.Client;
import zw.co.zim.willplatform.utils.messages.request.AssetPersonOwingMoneyRequest;
import zw.co.zim.willplatform.utils.messages.response.basic.ApiResponse;

public interface AssetPersonOwingMoneyProcessor extends ProcessorService<AssetPersonOwingMoneyRecordDto, AssetPersonOwingMoneyRequest> {
    ApiResponse<AssetPersonOwingMoneyRecordDto> findAllByUserId(Long clientId, Integer pageNo, Integer pageSize);
}
