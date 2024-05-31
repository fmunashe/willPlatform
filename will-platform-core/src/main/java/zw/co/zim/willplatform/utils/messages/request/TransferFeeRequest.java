package zw.co.zim.willplatform.utils.messages.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransferFeeRequest {
    @NotNull(message = "Transfer fee value is required")
    private Double transferValue;
    @NotNull(message = "Transfer fee is required")
    private Double transferFee;
    @NotNull(message = "Vat is required")
    private Double vat;
    @NotNull(message = "Levy is required")
    private Double levy;
}
