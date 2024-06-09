package zw.co.zim.willplatform.dto.mapper;

import org.springframework.stereotype.Service;
import zw.co.zim.willplatform.dto.QuestionnaireDto;
import zw.co.zim.willplatform.model.Questionnaire;

import java.util.function.Function;

@Service
public class QuestionnaireDtoMapper implements Function<Questionnaire, QuestionnaireDto> {
    @Override
    public QuestionnaireDto apply(Questionnaire questionnaire) {
        return QuestionnaireDto.builder()
            .id(questionnaire.getId())
            .userId(questionnaire.getUserId())
            .formerSpouse(questionnaire.getFormerSpouse())
            .maritalStatus(questionnaire.getMaritalStatus())
            .haveChildren(questionnaire.getHaveChildren())
            .registeredTaxPayer(questionnaire.getRegisteredTaxPayer())
            .businessOwner(questionnaire.getBusinessOwner())
            .haveProperty(questionnaire.getHaveProperty())
            .haveVehicle(questionnaire.getHaveVehicle())
            .haveAssetsOffshore(questionnaire.getHaveAssetsOffshore())
            .haveOverseaWill(questionnaire.getHaveOverseaWill())
            .haveAssetsTrust(questionnaire.getHaveAssetsTrust())
            .haveOtherAssets(questionnaire.getHaveOtherAssets())
            .haveTimeShare(questionnaire.getHaveTimeShare())
            .someOneOweMoney(questionnaire.getSomeOneOweMoney())
            .haveBankAccount(questionnaire.getHaveBankAccount())
            .haveCreditCard(questionnaire.getHaveCreditCard())
            .haveInvestment(questionnaire.getHaveInvestment())
            .havePolicy(questionnaire.getHavePolicy())
            .haveOutstandingLoans(questionnaire.getHaveOutstandingLoans())
            .haveOutstandingAccounts(questionnaire.getHaveOutstandingAccounts())
            .userOweMoney(questionnaire.getUserOweMoney())
            .subscribedWrittenPublication(questionnaire.getSubscribedWrittenPublication())
            .haveFireArm(questionnaire.getHaveFireArm())
            .numberOfFireArms(questionnaire.getNumberOfFireArms())
            .haveMedicalAid(questionnaire.getHaveMedicalAid())
            .medicalAidName(questionnaire.getMedicalAidName())
            .haveDependenciesOverSeas(questionnaire.getHaveDependenciesOverSeas())
            .occupation(questionnaire.getOccupation())
            .educationalLevel(questionnaire.getEducationalLevel())
            .monthlyIncome(questionnaire.getMonthlyIncome())
            .monthlyHouseholdExpense(questionnaire.getMonthlyHouseholdExpense())
            .smoker(questionnaire.getSmoker())
            .recordStatus(questionnaire.getRecordStatus())
            .createdAt(questionnaire.getCreatedAt())
            .updatedAt(questionnaire.getUpdatedAt())
            .build();
    }
}
