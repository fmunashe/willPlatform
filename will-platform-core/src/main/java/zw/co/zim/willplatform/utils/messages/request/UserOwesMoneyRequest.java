package zw.co.zim.willplatform.utils.messages.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserOwesMoneyRequest {
    @NotNull(message = "Person details is required")
    private String personDetails;
    @NotNull(message = "Person contact details is required")
    private String personContactDetails;
    @NotNull(message = "Amount owed is required")
    private Double amountOwed;
    @NotNull(message = "Client id is required")
    private Long clientId;
}
