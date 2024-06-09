package zw.co.zim.willplatform.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import zw.co.zim.willplatform.dto.UserRecordDto;
import zw.co.zim.willplatform.utils.enums.OTPDeliveryChannel;
import zw.co.zim.willplatform.utils.enums.RecordStatus;

import java.time.LocalDate;

@Entity
@Table(name = "clients")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Client extends BaseEntity {
    @Column(nullable = false)
    @NotNull
    private String firstName;
    private String middleName;
    private String knownAs;
    @NotNull
    private String lastName;
    @NotNull
    @Column(unique = true)
    private String email;
    private String mobileNumber;
    private String firstLanguage;
    @NotNull
    private LocalDate dateOfBirth;
    @Column(unique = true)
    private String nationalIdNumber;
    private String passportNumber;
    @Embedded
    private Address physicalAddress;

    @JsonIgnore
    private String password;
    private Boolean acceptedTermsAndConditions;
    @JsonIgnore
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
        this.recordStatus =userRecordDto.recordStatus();
    }
}
