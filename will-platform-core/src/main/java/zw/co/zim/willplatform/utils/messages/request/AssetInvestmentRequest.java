package zw.co.zim.willplatform.utils.messages.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AssetInvestmentRequest {
    @NotNull(message = "Investment type is required")
    @NotNull(message = "Investment type cannot be blank")
    String investmentType;

    @NotNull(message = "Investment value is required")
    Double investmentValue;

    @NotNull(message = "Investment company is required")
    @NotNull(message = "Investment company cannot be blank")
    String company;

    @NotNull(message = "Investment company is required")
    @NotNull(message = "Investment company cannot be blank")
    Long clientId;
}
