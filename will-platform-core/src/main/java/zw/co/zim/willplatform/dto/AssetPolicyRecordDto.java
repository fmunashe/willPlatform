package zw.co.zim.willplatform.dto;

import lombok.Builder;
import zw.co.zim.willplatform.utils.enums.RecordStatus;
import zw.co.zim.willplatform.model.Client;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

@Builder
public record AssetPolicyRecordDto(
    Long id,
    @NotNull(message = "Policy type is required")
    @NotBlank(message = "Policy type cannot be blank")
    String policyType,
    @NotNull(message = "Policy number is required")
    @NotBlank(message = "Policy number cannot be blank")
    String policyNumber,

    @NotNull(message = "Policy company is required")
    @NotBlank(message = "Policy company cannot be blank")
    String company,
    @NotNull(message = "Policy value is required")
    Double value,
    Client userId,
    RecordStatus recordStatus,
    LocalDateTime createdAt,
    LocalDateTime updatedAt
) {
}
