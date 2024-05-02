package zw.co.zim.willplatform.dto;

import zw.co.zim.willplatform.enums.RecordStatus;
import zw.co.zim.willplatform.model.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AssetPolicyRecordDto(
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
    User userId,
    RecordStatus recordStatus
) {
}
