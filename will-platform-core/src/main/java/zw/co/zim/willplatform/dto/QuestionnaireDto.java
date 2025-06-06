package zw.co.zim.willplatform.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import zw.co.zim.willplatform.model.Client;
import zw.co.zim.willplatform.utils.enums.MaritalStatus;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class QuestionnaireDto extends BaseDto {
    private Client userId;
    private Boolean formerSpouse;
    private MaritalStatus maritalStatus;
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
    private int numberOfFireArms;
    private Boolean haveMedicalAid;
    private String medicalAidName;
    private Boolean haveDependenciesOverSeas;
    private String occupation;
    private String educationalLevel;
    private Double monthlyIncome;
    private Double monthlyHouseholdExpense;
    private Boolean smoker;
}
