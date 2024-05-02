package zw.co.zim.willplatform.dto;

import zw.co.zim.willplatform.enums.RecordStatus;
import zw.co.zim.willplatform.model.Client;
import jakarta.validation.constraints.NotNull;

public record AssetOffshoreRecordDto(
    @NotNull(message = "Asset offshore description is required")
    @NotNull(message = "Asset offshore description cannot be blank")
    String description,

    @NotNull(message = "Asset offshore value is required")
    Double value,

    Client userId,
    RecordStatus recordStatus
) {
}
