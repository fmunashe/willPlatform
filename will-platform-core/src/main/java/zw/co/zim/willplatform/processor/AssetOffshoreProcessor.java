package zw.co.zim.willplatform.processor;

import zw.co.zim.willplatform.common.BaseProcessor;
import zw.co.zim.willplatform.dto.AssetOffshoreRecordDto;
import zw.co.zim.willplatform.utils.messages.request.AssetOffshoreRequest;
import zw.co.zim.willplatform.utils.messages.response.basic.ApiResponse;

public interface AssetOffshoreProcessor extends BaseProcessor<AssetOffshoreRecordDto,AssetOffshoreRequest> {

    ApiResponse<AssetOffshoreRecordDto> findAllByUserId(Long clientId, Integer pageNo, Integer pageSize);
}
