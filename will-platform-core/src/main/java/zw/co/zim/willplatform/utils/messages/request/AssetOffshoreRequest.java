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
public class AssetOffshoreRequest {
    @NotNull(message = "Asset offshore description is required")
    String description;

    @NotNull(message = "Asset offshore value is required")
    Double assetValue;

    @NotNull(message = "Asset client id is required")
    Long clientId;
}
