package zw.co.zim.willplatform.dto;

import zw.co.zim.willplatform.enums.RecordStatus;
import zw.co.zim.willplatform.model.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AssetTimeShareDto(
    @NotNull(message = "Asset timeshare description is required")
    @NotBlank(message = "Asset timeshare description cannot be blank")
    String description,

    @NotNull(message = "Asset timeshare value is required")
    double value,

    User userId,
    RecordStatus recordStatus
) {
}
