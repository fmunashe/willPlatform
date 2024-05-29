package zw.co.zim.willplatform.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToOne;
import lombok.*;
import zw.co.zim.willplatform.utils.enums.RecordStatus;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Questionnaire extends BaseEntity {
    @OneToOne
    private Client userId;
    private Boolean formerSpouse;
    private Boolean haveChildren;
    private Boolean registeredTaxPayer;
    private Boolean businessOwner;
    private Boolean haveProperty;
    private Boolean haveVehicle;
    private Boolean haveAssetsOffshore;
    private Boolean haveOverseaWill;
    private Boolean haveAssetsTrust;
    private Boolean haveOtherAssets;
    private Boolean haveTimeShare;
    private Boolean someOneOweMoney;
    private Boolean haveBankAccount;
    private Boolean haveCreditCard;
    private Boolean haveInvestment;
    private Boolean havePolicy;
    private Boolean haveOutstandingLoans;
    private Boolean haveOutstandingAccounts;
    private Boolean userOweMoney;
    private Boolean subscribedWrittenPublication;
    private Boolean haveFireArm;
    private Boolean numberOfFireArms;
    private Boolean haveMedicalAid;
    private Boolean medicalAidName;
    private Boolean haveDependenciesOverSeas;
    private String occupation;
    private String educationalLevel;
    private Double monthlyIncome;
    private Double monthlyHouseholdExpense;
    private Boolean smoker;
    @Enumerated(EnumType.STRING)
    private RecordStatus recordStatus;
}
