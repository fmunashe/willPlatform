package zw.co.zim.willplatform.utils.messages.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import zw.co.zim.willplatform.utils.enums.BurialType;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BurialRequest {
    @NotNull(message = "Burial type is required")
    private BurialType burialType;

    @NotNull(message = "Burial information is required")
    private String burialInformation;

    @NotNull(message = "Living will field is required")
    private Boolean livingWill;

    @NotNull(message = "Client Id is required")
    private Long clientId;
}
