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
public class OutstandingLoanRequest {
    @NotNull(message = "Name of creditor is required")
    @NotBlank(message = "Name of creditor cannot be blank")
    private String nameOfCreditor;
    @NotNull(message = "Amount owed is required")
    private Double amountOwed;
    @NotNull(message = "Client id is required")
    private Long clientId;
}
