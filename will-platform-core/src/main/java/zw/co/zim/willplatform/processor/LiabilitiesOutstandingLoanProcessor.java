package zw.co.zim.willplatform.processor;

import zw.co.zim.willplatform.common.BaseProcessor;
import zw.co.zim.willplatform.dto.LiabilitiesOutstandingLoanDto;
import zw.co.zim.willplatform.utils.messages.request.OutstandingLoanRequest;
import zw.co.zim.willplatform.utils.messages.response.basic.ApiResponse;

public interface LiabilitiesOutstandingLoanProcessor extends BaseProcessor<LiabilitiesOutstandingLoanDto, OutstandingLoanRequest> {
    ApiResponse<LiabilitiesOutstandingLoanDto> findAllByUserId(Long clientId, Integer pageNo, Integer pageSize);
}
