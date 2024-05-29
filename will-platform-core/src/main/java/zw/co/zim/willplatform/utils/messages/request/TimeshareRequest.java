package zw.co.zim.willplatform.utils.messages.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TimeshareRequest {
    @NotNull(message = "Asset timeshare description is required")
    @NotBlank(message = "Asset timeshare description cannot be blank")
    String description;

    @NotNull(message = "Asset timeshare value is required")
    double value;

    @NotNull(message = "Asset timeshare client id is required")
    @NotBlank(message = "Asset timeshare client id cannot be blank")
    Long clientId;
}
