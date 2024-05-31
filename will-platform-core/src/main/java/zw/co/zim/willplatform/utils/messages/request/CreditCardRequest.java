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
public class CreditCardRequest {
    @NotNull(message = "Name of institution is required")
    @NotBlank(message = "Name of institution cannot be blank")
    private String nameOfInstitution;
    @NotNull(message = "Card value is required")
    private Double cardValue;
    @NotNull(message = "Client id is required")
    private Long clientId;
}
