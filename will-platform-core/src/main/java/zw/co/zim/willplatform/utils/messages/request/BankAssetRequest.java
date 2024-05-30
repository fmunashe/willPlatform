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
public class BankAssetRequest {
    @NotNull(message = "Bank name is required")
    @NotBlank(message = "Bank name cannot be blank")
    String bankName;

    @NotNull(message = "Account number is required")
    @NotBlank(message = "Account number cannot be blank")
    String accountNumber;

    @NotNull(message = "Balance is required")
    Double balance;

    @NotNull(message = "Currency is required")
    String currency;

    @NotNull(message = "Client id is required")
    Long clientId;
}
