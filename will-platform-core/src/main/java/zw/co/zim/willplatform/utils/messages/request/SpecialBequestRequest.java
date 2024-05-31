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
public class SpecialBequestRequest {
    @NotNull(message = "Person details is required")
    @NotBlank(message = "Person details cannot be blank")
    private String personDetails;

    @NotNull(message = "Special bequest is required")
    @NotBlank(message = "Special bequest cannot be blank")
    private String specialBequestInformation;

    @NotNull(message = "Client id is required")
    private Long clientId;
}
