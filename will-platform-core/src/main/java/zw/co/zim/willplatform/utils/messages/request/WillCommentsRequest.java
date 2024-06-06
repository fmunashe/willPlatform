package zw.co.zim.willplatform.utils.messages.request;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class WillCommentsRequest {
    @NotNull(message = "Will comments are required")
    private String comments;
    @NotNull(message = "Client Id is required")
    private Long clientId;
}
