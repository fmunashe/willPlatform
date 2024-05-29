package zw.co.zim.willplatform.dto;

import lombok.Builder;
import zw.co.zim.willplatform.utils.enums.RecordStatus;
import zw.co.zim.willplatform.model.Client;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

@Builder
public record AssetTrustDto(
  Long id,
    @NotNull(message = "Name of trust is required")
    @NotBlank(message = "Name of trust cannot be blank")
    String nameOfTrust,
    @NotNull(message = "Value of trust is required")
    Double value,

    Client userId,
    RecordStatus recordStatus,
  LocalDateTime createdAt,
  LocalDateTime updatedAt) {
}
