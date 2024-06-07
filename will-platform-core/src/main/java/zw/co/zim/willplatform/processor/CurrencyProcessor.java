package zw.co.zim.willplatform.processor;

import zw.co.zim.willplatform.common.BaseProcessor;
import zw.co.zim.willplatform.dto.CurrencyDto;
import zw.co.zim.willplatform.utils.messages.request.CurrencyRequest;
import zw.co.zim.willplatform.utils.messages.response.basic.ApiResponse;

public interface CurrencyProcessor extends BaseProcessor<CurrencyDto, CurrencyRequest> {
    ApiResponse<CurrencyDto> findCurrencyByName(String name);
}
