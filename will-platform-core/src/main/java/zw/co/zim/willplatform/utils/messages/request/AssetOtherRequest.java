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
public class AssetOtherRequest {
    @NotNull(message = "Other asset description is required")
    String description;
    @NotNull(message = "Other asset value is required")
    Double value;
    @NotNull(message = "Other asset client id is required")
    Long clientId;
}
