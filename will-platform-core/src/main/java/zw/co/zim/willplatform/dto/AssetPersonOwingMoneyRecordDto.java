package zw.co.zim.willplatform.dto;

import zw.co.zim.willplatform.enums.RecordStatus;
import zw.co.zim.willplatform.model.Client;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AssetPersonOwingMoneyRecordDto(
    @NotNull(message = "Person owing money details are required")
    @NotBlank(message = "Person owing money details cannot be blank")
    String personDetails,

    @NotNull(message = "Amount owed is required")
    Double amountOwed,
    @NotNull(message = "Person owing money contact number required")
    @NotBlank(message = "Person owing money contact number cannot be blank")
    String contactNumber,

    Client userId,
    RecordStatus recordStatus
) {
}
