package zw.co.zim.willplatform.utils.messages.request;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class WillPasswordInstructionsRequest {
    @NotNull(message = "Will password instructions are required")
    private String instructions;
    @NotNull(message = "Client id is required")
    private Long clientId;
}
