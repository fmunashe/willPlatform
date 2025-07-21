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
public class ProductRequest {
    @NotNull(message = "Product name is required")
    private String name;
    private String description;
    @NotNull(message = "Product price is required")
    private Double price;
    @NotNull(message = "Product currency is required")
    private String currency;
}
