package zw.co.zim.willplatform.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import zw.co.zim.willplatform.utils.enums.RecordStatus;
import zw.co.zim.willplatform.model.Client;

import java.time.LocalDate;

public record VehicleAssetRecordDto(
    Long id,
    @NotNull(message = "Make field is required")
    @NotBlank(message = "Make field cannot be blank")
    String make,
    @NotNull(message = "Model field is required")
    @NotBlank(message = "Model field cannot be blank")
    String model,

    @NotNull(message = "Color field is required")
    @NotBlank(message = "Color field cannot be blank")
    String color,

    @NotNull(message = "Registration number field is required")
    @NotBlank(message = "Registration number field cannot be blank")
    String registrationNumber,

    @NotNull(message = "Engine number field is required")
    @NotBlank(message = "Engine number field cannot be blank")
    String engineNumber,

    @NotNull(message = "manufacture is required")
    LocalDate manufactureYear,

    @NotNull(message = "Vehicle value field is required")
    Double value,
    Boolean fullyPaid,
    @NotNull(message = "Registration papers with field is required")
    @NotBlank(message = "Registration papers with field cannot be blank")
    String registrationPaperWith,
    Client user,
    RecordStatus recordStatus
) {
}
