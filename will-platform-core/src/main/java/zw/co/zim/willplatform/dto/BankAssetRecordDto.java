package zw.co.zim.willplatform.dto;

import zw.co.zim.willplatform.enums.RecordStatus;
import zw.co.zim.willplatform.model.Client;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record BankAssetRecordDto(
    @NotNull(message = "Bank name is required")
    @NotBlank(message = "Bank name cannot be blank")
    String bankName,
    @NotNull(message = "Account number is required")
    @NotBlank(message = "Account number cannot be blank")
    String accountNumber,
    @NotNull(message = "Balance is required")
    Double balance,
    Client user,
    RecordStatus recordStatus
) {

}
