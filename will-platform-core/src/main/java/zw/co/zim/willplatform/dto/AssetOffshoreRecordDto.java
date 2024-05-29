package zw.co.zim.willplatform.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import zw.co.zim.willplatform.model.Client;
import zw.co.zim.willplatform.utils.enums.RecordStatus;

@Builder
public record AssetOffshoreRecordDto(
    Long id,
    @NotNull(message = "Asset offshore description is required")
    @NotNull(message = "Asset offshore description cannot be blank")
    String description,

    @NotNull(message = "Asset offshore value is required")
    Double value,

    Client userId,
    RecordStatus recordStatus
) {
}
