package zw.co.zim.willplatform.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import zw.co.zim.willplatform.utils.enums.RecordStatus;

import java.time.LocalDate;


public record UserRecordDto(Long id,
                            @NotNull(message = "First name is required")
                            @NotBlank(message = "First name field cannot be blank")
                            String firstName,
                            @NotNull(message = "Last name is required")
                            @NotBlank(message = "Last name field cannot be blank")
                            String lastName,
                            @NotNull(message = "Date of birth is required")
                            LocalDate dob,
                            @NotNull(message = "National ID is required") @Column(unique = true)
                            @NotBlank(message = "National ID field cannot be blank")
                            String nationalId,
                            @NotNull(message = "Passport number is required")
                            @Column(unique = true)
                            @NotBlank(message = "Passport number field cannot be blank")
                            String passportNumber,
                            @NotNull(message = "Street number is required")
                            @NotBlank(message = "Street number field cannot be blank")
                            String streetNumber,
                            @NotNull(message = "Suburb is required")
                            @NotBlank(message = "Suburb field cannot be blank")
                            String suburb,
                            @NotNull(message = "City is required")
                            @NotBlank(message = "City field cannot be blank")
                            String city,
                            @NotNull(message = "Province is required")
                            @NotBlank(message = "Province field cannot be blank")
                            String province,
                            @NotNull(message = "Country is required")
                            @NotBlank(message = "Country field cannot be blank")
                            String country,
                            RecordStatus recordStatus) {
}
