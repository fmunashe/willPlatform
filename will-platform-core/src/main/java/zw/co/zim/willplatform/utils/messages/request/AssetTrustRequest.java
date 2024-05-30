package zw.co.zim.willplatform.utils.messages.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AssetTrustRequest {
    @NotNull(message = "Name of trust is required")
    @NotBlank(message = "Name of trust cannot be blank")
    String nameOfTrust;
    @NotNull(message = "Value of trust is required")
    Double value;

    @NotNull(message = "Trust client id is required")
    Long clientId;
}
