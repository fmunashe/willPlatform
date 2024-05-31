package zw.co.zim.willplatform.processor;

import zw.co.zim.willplatform.common.BaseProcessor;
import zw.co.zim.willplatform.dto.LiabilitiesSubscribedWrittenPublicationDto;
import zw.co.zim.willplatform.utils.messages.request.WrittenPublicationRequest;
import zw.co.zim.willplatform.utils.messages.response.basic.ApiResponse;

public interface LiabilitiesSubscribedWrittenPublicationProcessor extends BaseProcessor<LiabilitiesSubscribedWrittenPublicationDto, WrittenPublicationRequest> {
    ApiResponse<LiabilitiesSubscribedWrittenPublicationDto> findAllByUserId(Long clientId, Integer pageNo, Integer pageSize);
}
