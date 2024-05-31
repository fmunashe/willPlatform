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
public class WrittenPublicationRequest {
    @NotNull(message = "Name of publication is required")
    @NotBlank(message = "Name of publication cannot be blank")
    private String nameOfPublication;
    @NotNull(message = "Client id is required")
    private Long clientId;
}
