package zw.co.zim.willplatform.processor;

import zw.co.zim.willplatform.common.ProcessorService;
import zw.co.zim.willplatform.dto.BankAssetRecordDto;
import zw.co.zim.willplatform.utils.messages.request.BankAssetRequest;
import zw.co.zim.willplatform.utils.messages.response.basic.ApiResponse;

public interface BankAssetProcessor extends ProcessorService<BankAssetRecordDto, BankAssetRequest> {
    ApiResponse<BankAssetRecordDto> findBankByAccountNumber(String accountNumber);

    ApiResponse<BankAssetRecordDto> findAllByUserId(Long clientId, Integer pageNo, Integer pageSize);
}
