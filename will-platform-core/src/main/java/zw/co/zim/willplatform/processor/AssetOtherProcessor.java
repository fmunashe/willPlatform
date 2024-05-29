package zw.co.zim.willplatform.processor;

import zw.co.zim.willplatform.common.ProcessorService;
import zw.co.zim.willplatform.dto.AssetOtherRecordDto;
import zw.co.zim.willplatform.utils.messages.request.AssetOtherRequest;
import zw.co.zim.willplatform.utils.messages.response.basic.ApiResponse;

public interface AssetOtherProcessor extends ProcessorService<AssetOtherRecordDto, AssetOtherRequest> {
    ApiResponse<AssetOtherRecordDto> findAllByUserId(Long clientId, Integer pageNo, Integer pageSize);
}
