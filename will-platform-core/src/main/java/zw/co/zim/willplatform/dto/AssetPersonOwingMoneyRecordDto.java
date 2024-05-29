package zw.co.zim.willplatform.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import zw.co.zim.willplatform.model.Client;
import zw.co.zim.willplatform.utils.enums.RecordStatus;

import java.time.LocalDateTime;

@Builder
public record AssetPersonOwingMoneyRecordDto(
    Long id,
    @NotNull(message = "Person owing money details are required")
    @NotBlank(message = "Person owing money details cannot be blank")
    String personDetails,

    @NotNull(message = "Amount owed is required")
    Double amountOwed,
    @NotNull(message = "Person owing money contact number required")
    @NotBlank(message = "Person owing money contact number cannot be blank")
    String contactNumber,

    Client userId,
    RecordStatus recordStatus,
    LocalDateTime createdAt,
    LocalDateTime updatedAt
) {
}
