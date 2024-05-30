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
public class AssetPersonOwingMoneyRequest {
    @NotNull(message = "Person owing money details are required")
    @NotBlank(message = "Person owing money details cannot be blank")
    String personDetails;

    @NotNull(message = "Amount owed is required")
    Double amountOwed;

    @NotNull(message = "Person owing money contact number required")
    @NotBlank(message = "Person owing money contact number cannot be blank")
    String contactNumber;

    @NotNull(message = "Person owing money contact number required")
    Long clientId;
}
