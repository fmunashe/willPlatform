package zw.co.zim.willplatform.dto;

import zw.co.zim.willplatform.enums.RecordStatus;
import zw.co.zim.willplatform.model.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AssetTrustDto(
    @NotNull(message = "Name of trust is required")
    @NotBlank(message = "Name of trust cannot be blank")
    String nameOfTrust,
    @NotNull(message = "Value of trust is required")
    Double value,

    User userId,
    RecordStatus recordStatus
) {
}
