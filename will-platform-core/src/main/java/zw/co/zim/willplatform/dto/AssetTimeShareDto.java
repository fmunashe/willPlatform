package zw.co.zim.willplatform.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import zw.co.zim.willplatform.model.Client;
import zw.co.zim.willplatform.utils.enums.RecordStatus;

import java.time.LocalDateTime;

@Builder
public record AssetTimeShareDto(
  Long id,
    @NotNull(message = "Asset timeshare description is required")
    @NotBlank(message = "Asset timeshare description cannot be blank")
    String description,

    @NotNull(message = "Asset timeshare value is required")
    double value,

    Client userId,
    RecordStatus recordStatus,
    LocalDateTime createdAt,
    LocalDateTime updatedAt
) {
}
