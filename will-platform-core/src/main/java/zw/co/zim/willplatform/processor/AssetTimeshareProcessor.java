package zw.co.zim.willplatform.processor;

import org.springframework.data.domain.Page;
import zw.co.zim.willplatform.common.AppService;
import zw.co.zim.willplatform.common.ProcessorService;
import zw.co.zim.willplatform.dto.AssetTimeShareDto;
import zw.co.zim.willplatform.model.AssetTimeShare;
import zw.co.zim.willplatform.model.Client;
import zw.co.zim.willplatform.utils.messages.request.TimeshareRequest;
import zw.co.zim.willplatform.utils.messages.response.basic.ApiResponse;

public interface AssetTimeshareProcessor extends ProcessorService<AssetTimeShareDto, TimeshareRequest> {
    ApiResponse<AssetTimeShareDto> findAllByUserId(Long clientId, Integer pageNo, Integer pageSize);
}
