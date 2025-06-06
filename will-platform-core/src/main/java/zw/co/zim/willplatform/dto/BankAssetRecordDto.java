package zw.co.zim.willplatform.dto;

import lombok.Builder;
import zw.co.zim.willplatform.model.Currency;
import zw.co.zim.willplatform.utils.enums.RecordStatus;
import zw.co.zim.willplatform.model.Client;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

@Builder
public record BankAssetRecordDto(
    Long id,
    @NotNull(message = "Bank name is required")
    @NotBlank(message = "Bank name cannot be blank")
    String bankName,
    @NotNull(message = "Account number is required")
    @NotBlank(message = "Account number cannot be blank")
    String accountNumber,
    @NotNull(message = "Balance is required")
    Double balance,
    @NotNull(message = "Currency is required")
    Currency currency,
    Client user,
    RecordStatus recordStatus,
    LocalDateTime createdAt,
    LocalDateTime updatedAt

) {

}
