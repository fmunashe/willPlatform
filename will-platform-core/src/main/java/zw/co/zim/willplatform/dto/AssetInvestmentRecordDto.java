package zw.co.zim.willplatform.dto;

import lombok.Builder;
import lombok.Data;
import zw.co.zim.willplatform.model.User;
import jakarta.validation.constraints.NotNull;

public record AssetInvestmentRecordDto(
    @NotNull(message = "Investment type is required")
    @NotNull(message = "Investment type cannot be blank")
    String investmentType,

    @NotNull(message = "Investment value is required")
    Double value,

    @NotNull(message = "Investment company is required")
    @NotNull(message = "Investment company cannot be blank")
    String company,

    User userId
) {
}
