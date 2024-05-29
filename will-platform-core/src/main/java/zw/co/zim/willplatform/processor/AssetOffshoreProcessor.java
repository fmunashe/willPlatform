package zw.co.zim.willplatform.processor;

import zw.co.zim.willplatform.common.ProcessorService;
import zw.co.zim.willplatform.dto.AssetOffshoreRecordDto;
import zw.co.zim.willplatform.utils.messages.request.AssetOffshoreRequest;
import zw.co.zim.willplatform.utils.messages.response.basic.ApiResponse;

public interface AssetOffshoreProcessor extends ProcessorService<AssetOffshoreRecordDto,AssetOffshoreRequest> {

    ApiResponse<AssetOffshoreRecordDto> findAllByUserId(Long clientId, Integer pageNo, Integer pageSize);
}
