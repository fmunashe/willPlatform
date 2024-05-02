package zw.co.zim.willplatform.dto;

import jakarta.validation.constraints.NotNull;
import zw.co.zim.willplatform.enums.RecordStatus;
import zw.co.zim.willplatform.model.Client;

public record AssetInvestmentRecordDto(
    @NotNull(message = "Investment type is required")
    @NotNull(message = "Investment type cannot be blank")
    String investmentType,

    @NotNull(message = "Investment value is required")
    Double value,

    @NotNull(message = "Investment company is required")
    @NotNull(message = "Investment company cannot be blank")
    String company,

    Client userId,

    RecordStatus recordStatus
) {
}
