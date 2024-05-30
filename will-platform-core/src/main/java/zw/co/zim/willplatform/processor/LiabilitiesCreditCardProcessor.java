package zw.co.zim.willplatform.processor;

import zw.co.zim.willplatform.common.ProcessorService;
import zw.co.zim.willplatform.dto.LiabilitiesCreditCardDto;
import zw.co.zim.willplatform.utils.messages.request.CreditCardRequest;
import zw.co.zim.willplatform.utils.messages.response.basic.ApiResponse;

public interface LiabilitiesCreditCardProcessor extends ProcessorService<LiabilitiesCreditCardDto, CreditCardRequest> {
    ApiResponse<LiabilitiesCreditCardDto> findAllByUserId(Long clientId, Integer pageNo, Integer pageSize);
}
