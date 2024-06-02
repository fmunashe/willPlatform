package zw.co.zim.willplatform.utils.messages.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import zw.co.zim.willplatform.utils.enums.CiviallyMarriedStatus;
import zw.co.zim.willplatform.utils.enums.MaritalStatus;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SpouseRequest {
    @NotNull(message = "Name is required")
    private String name;
    @NotNull(message = "Surname is required")
    private String surname;
    private String idNumber;
    @NotNull(message = "Email is required")
    private String email;
    @NotNull(message = "Mobile is required")
    private String mobile;
    @NotNull(message = "Marital status is required")
    private MaritalStatus maritalStatus;
    private CiviallyMarriedStatus civillyMarriedStatus;
    @NotNull(message = "Client id is required")
    private Long clientId;
}
