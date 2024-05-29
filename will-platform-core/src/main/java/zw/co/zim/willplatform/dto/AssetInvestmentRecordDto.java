package zw.co.zim.willplatform.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import zw.co.zim.willplatform.model.Client;
import zw.co.zim.willplatform.utils.enums.RecordStatus;

@Builder
public record AssetInvestmentRecordDto(
    Long id,
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
