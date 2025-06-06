package zw.co.zim.willplatform.utils.messages.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AssetPolicyRequest {
    @NotNull(message = "Policy type is required")
    @NotBlank(message = "Policy type cannot be blank")
    String policyType;

    @NotNull(message = "Policy number is required")
    @NotBlank(message = "Policy number cannot be blank")
    String policyNumber;

    @NotNull(message = "Policy company is required")
    @NotBlank(message = "Policy company cannot be blank")
    String company;

    @NotNull(message = "Policy value is required")
    Double value;

    @NotNull(message = "Policy client id is required")
    Long clientId;
}
