package zw.co.zim.willplatform.utils.messages.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AlternativeExecutorRequest {
    @NotNull(message = "Executor details required")
    private String executorDetails;
    @NotNull(message = "Email is required")
    private String email;
    @NotNull(message = "Executor's contact is required")
    private String contactNumber;
    @NotNull(message = "Client id is required")
    private Long clientId;
    private String street;
    private String suburb;
    private String city;
    private String province;
    private String country;
}
