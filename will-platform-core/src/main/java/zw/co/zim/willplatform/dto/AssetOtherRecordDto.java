package zw.co.zim.willplatform.dto;

import zw.co.zim.willplatform.model.User;
import jakarta.validation.constraints.NotNull;

public record AssetOtherRecordDto(
    @NotNull(message = "Other asset description is required")
    @NotNull(message = "Other asset description cannot be blank")
    String description,

    @NotNull(message = "Other asset value is required")
    Double value,

    User userId
) {
}
