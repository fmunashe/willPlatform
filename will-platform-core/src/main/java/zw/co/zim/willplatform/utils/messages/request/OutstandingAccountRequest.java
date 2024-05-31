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
public class OutstandingAccountRequest {
    @NotNull(message = "Account name is required")
    @NotBlank(message = "Account name cannot be blank")
    private String nameOfAccount;
    @NotNull(message = "Account value is required")
    private Double accountValue;
    @NotNull(message = "Client id is required")
    private Long clientId;
}
