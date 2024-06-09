package zw.co.zim.willplatform.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import zw.co.zim.willplatform.model.Address;
import zw.co.zim.willplatform.utils.enums.OTPDeliveryChannel;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class ClientDto extends BaseDto {
    private String firstName;
    private String middleName;
    private String knownAs;
    private String lastName;
    private String email;
    private String mobileNumber;
    private String firstLanguage;
    private LocalDate dateOfBirth;
    private String nationalIdNumber;
    private String passportNumber;
    private Address physicalAddress;
    private String password;
    private Boolean acceptedTermsAndConditions;
    private String OTP;
    private OTPDeliveryChannel sendOtpVia;
}
