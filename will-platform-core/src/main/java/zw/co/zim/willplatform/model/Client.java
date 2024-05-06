package zw.co.zim.willplatform.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import zw.co.zim.willplatform.dto.UserRecordDto;
import zw.co.zim.willplatform.enums.OTPDeliveryChannel;
import zw.co.zim.willplatform.enums.RecordStatus;

import java.time.LocalDate;

@Entity
@Table(name = "users")
@NoArgsConstructor
@Getter
@Setter
public class Client extends BaseEntity {
    @Column(nullable = false)
    @NotNull
    private String firstName;
    private String middleName;
    private String knownAs;
    @NotNull
    private String lastName;
    @NotNull
    private String email;
    private String firstLanguage;
    @NotNull
    private LocalDate dateOfBirth;
    @Column(unique = true)
    private String nationalIdNumber;
    @Column(unique = true)
    private String passportNumber;
    @Embedded
    private Address physicalAddress;

    private String password;
    private Boolean acceptedTermsAndConditions;
    private String OTP;

    @Enumerated(EnumType.STRING)
    private OTPDeliveryChannel sendOtpVia;

    @Enumerated(EnumType.STRING)
    private RecordStatus recordStatus;

    public Client(UserRecordDto userRecordDto) {
        this.firstName = userRecordDto.firstName();
        this.lastName = userRecordDto.lastName();
        this.dateOfBirth = userRecordDto.dob();
        this.nationalIdNumber = userRecordDto.nationalId();
        this.passportNumber = userRecordDto.passportNumber();
        this.physicalAddress = new Address(userRecordDto.streetNumber(), userRecordDto.suburb(), userRecordDto.city(), userRecordDto.province(), userRecordDto.country());
    }
}
