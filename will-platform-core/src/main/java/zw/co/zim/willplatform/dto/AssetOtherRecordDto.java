package zw.co.zim.willplatform.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import zw.co.zim.willplatform.model.Client;
import zw.co.zim.willplatform.utils.enums.RecordStatus;

import java.time.LocalDateTime;

@Builder
public record AssetOtherRecordDto(
    Long id,
    @NotNull(message = "Other asset description is required")
    @NotNull(message = "Other asset description cannot be blank")
    String description,

    @NotNull(message = "Other asset value is required")
    Double value,

    Client userId,
    RecordStatus recordStatus,
    LocalDateTime createdAt,
    LocalDateTime updatedAt
) {
}
