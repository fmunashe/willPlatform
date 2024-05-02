package zw.co.zim.willplatform.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import zw.co.zim.willplatform.dto.UserRecordDto;
import zw.co.zim.willplatform.enums.RecordStatus;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "users")
@NoArgsConstructor
@Getter
@Setter
public class User extends BaseEntity {
    @Column(nullable = false)
    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
    @NotNull
    private LocalDate dateOfBirth;
    @Column(unique = true)
    private String nationalIdNumber;
    @Column(unique = true)
    private String passportNumber;
    @Embedded
    private Address address;

    @OneToMany(mappedBy = "userId", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<VehicleAsset> vehicles;

    @OneToMany(mappedBy = "userId", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<PropertyAsset> properties;

    @OneToMany(mappedBy = "userId", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<BankAsset> bankAccounts;

    @OneToMany(mappedBy = "userId", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<AssetPolicy> policies;

    @OneToMany(mappedBy = "userId", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<AssetTrust> trusts;

    @OneToMany(mappedBy = "userId", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<AssetTimeShare> timeShares;

    @OneToMany(mappedBy = "userId", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<AssetPersonOwingMoney> personOwingMonies;

    @OneToMany(mappedBy = "userId", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<AssetOther> assetOthers;

    @OneToMany(mappedBy = "userId", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<AssetOffshore> assetOffShores;

    @OneToMany(mappedBy = "userId", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<AssetInvestment> assetInvestments;

    @Enumerated(EnumType.STRING)
    private RecordStatus recordStatus;

    public User(UserRecordDto userRecordDto) {
        this.firstName = userRecordDto.firstName();
        this.lastName = userRecordDto.lastName();
        this.dateOfBirth = userRecordDto.dob();
        this.nationalIdNumber = userRecordDto.nationalId();
        this.passportNumber = userRecordDto.passportNumber();
        this.address = new Address(userRecordDto.streetNumber(), userRecordDto.suburb(), userRecordDto.city(), userRecordDto.province(), userRecordDto.country());
    }
}
