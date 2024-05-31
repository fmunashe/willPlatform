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
public class TotalDemiseRequest {
    @NotNull(message = "Beneficiary details is required")
    @NotBlank(message = "Beneficiary details cannot be blank")
    private String beneficiaryDetails;

    @NotNull(message = "Client id is required")
    private Long clientId;
}
