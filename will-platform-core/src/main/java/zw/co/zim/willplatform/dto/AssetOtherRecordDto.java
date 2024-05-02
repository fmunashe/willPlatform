package zw.co.zim.willplatform.dto;

import zw.co.zim.willplatform.enums.RecordStatus;
import zw.co.zim.willplatform.model.Client;
import jakarta.validation.constraints.NotNull;

public record AssetOtherRecordDto(
    @NotNull(message = "Other asset description is required")
    @NotNull(message = "Other asset description cannot be blank")
    String description,

    @NotNull(message = "Other asset value is required")
    Double value,

    Client userId,
    RecordStatus recordStatus
) {
}
