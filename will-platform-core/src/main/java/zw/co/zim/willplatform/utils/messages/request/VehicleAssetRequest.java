package zw.co.zim.willplatform.utils.messages.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VehicleAssetRequest {
    @NotNull(message = "Make field is required")
    @NotBlank(message = "Make field cannot be blank")
    String make;

    @NotNull(message = "Model field is required")
    @NotBlank(message = "Model field cannot be blank")
    String model;

    @NotNull(message = "Color field is required")
    @NotBlank(message = "Color field cannot be blank")
    String color;

    @NotNull(message = "Registration number field is required")
    @NotBlank(message = "Registration number field cannot be blank")
    String registrationNumber;

    @NotNull(message = "Engine number field is required")
    @NotBlank(message = "Engine number field cannot be blank")
    String engineNumber;

    @NotNull(message = "manufacture is required")
    LocalDate manufactureYear;

    @NotNull(message = "Vehicle value field is required")
    Double value;

    Boolean fullyPaid;

    @NotNull(message = "Registration papers with field is required")
    @NotBlank(message = "Registration papers with field cannot be blank")
    String registrationPaperWith;

    @NotNull(message = "Client id field is required")
    Long clientId;
}
