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
public class CurrencyRequest {
    @NotNull(message = "Currency name is required")
    private String name;
    private String symbol;
    private String iso;
    @NotNull(message = "Conversion rate is required")
    private Double conversionRate;
}
