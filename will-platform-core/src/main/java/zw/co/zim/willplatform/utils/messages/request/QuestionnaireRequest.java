package zw.co.zim.willplatform.utils.messages.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QuestionnaireRequest {
    private Long clientId;
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
}
