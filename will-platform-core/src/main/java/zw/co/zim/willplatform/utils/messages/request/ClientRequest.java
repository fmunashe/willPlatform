package zw.co.zim.willplatform.utils.messages.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import zw.co.zim.willplatform.utils.enums.OTPDeliveryChannel;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClientRequest {
    @NotNull(message = "First name is required")
    @NotBlank(message = "First name cannot be blank")
    private String firstName;

    private String middleName;

    private String knownAs;

    @NotNull(message = "Last name is required")
    @NotBlank(message = "Last name cannot be blank")
    private String lastName;

    @NotNull(message = "Email is required")
    @NotBlank(message = "Email cannot be blank")
    private String email;

    @NotNull(message = "Mobile number is required")
    @NotBlank(message = "Mobile number cannot be blank")
    private String mobileNumber;
    private String firstLanguage;

    @NotNull(message = "Date of birth is required")
    private LocalDate dateOfBirth;

    @NotNull(message = "National Id number is required")
    @NotBlank(message = "National Id number cannot be blank")
    private String nationalIdNumber;

    @NotNull(message = "Password is required")
    @NotBlank(message = "Password field cannot be blank")
    private String password;

    @NotNull(message = "Terms and conditions is required")
    private Boolean acceptedTermsAndConditions;

    @NotNull(message = "OTP delivery channel is required")
    private OTPDeliveryChannel sendOtpVia;

    @NotNull(message = "Street is required")
    @NotBlank(message = "Street field cannot be blank")
    private String street;

    @NotNull(message = "Suburb is required")
    @NotBlank(message = "Suburb field cannot be blank")
    private String suburb;

    @NotNull(message = "City is required")
    @NotBlank(message = "City field cannot be blank")
    private String city;

    @NotNull(message = "Province is required")
    @NotBlank(message = "Province field cannot be blank")
    private String province;

    @NotNull(message = "Country is required")
    @NotBlank(message = "Country field cannot be blank")
    private String country;
}
