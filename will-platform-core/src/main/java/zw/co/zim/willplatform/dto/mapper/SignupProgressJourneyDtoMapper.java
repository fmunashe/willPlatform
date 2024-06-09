package zw.co.zim.willplatform.dto.mapper;

import org.springframework.stereotype.Service;
import zw.co.zim.willplatform.dto.SignupProgressJourneyDto;
import zw.co.zim.willplatform.model.SignupProgressJourney;

import java.util.function.Function;

@Service
public class SignupProgressJourneyDtoMapper implements Function<SignupProgressJourney, SignupProgressJourneyDto> {
    @Override
    public SignupProgressJourneyDto apply(SignupProgressJourney signupProgressJourney) {
        return SignupProgressJourneyDto.builder()
            .id(signupProgressJourney.getId())
            .userId(signupProgressJourney.getUserId())
            .completedQuestionnaire(signupProgressJourney.getCompletedQuestionnaire())
            .completedPersonalSection(signupProgressJourney.getCompletedPersonalSection())
            .completedSpouseSection(signupProgressJourney.getCompletedSpouseSection())
            .completedAssetsSection(signupProgressJourney.getCompletedAssetsSection())
            .completedLiabilitiesSection(signupProgressJourney.getCompletedLiabilitiesSection())
            .completedWillSection(signupProgressJourney.getCompletedWillSection())
            .willComplete(signupProgressJourney.getWillComplete())
            .completedBeneficiarySection(signupProgressJourney.getCompletedBeneficiarySection())
            .completedChildrenSection(signupProgressJourney.getCompletedChildrenSection())
            .completedGuardianSection(signupProgressJourney.getCompletedGuardianSection())
            .finalisedWill(signupProgressJourney.getFinalisedWill())
            .subscribed(signupProgressJourney.getSubscribed())
            .recordStatus(signupProgressJourney.getRecordStatus())
            .createdAt(signupProgressJourney.getCreatedAt())
            .updatedAt(signupProgressJourney.getUpdatedAt())
            .build();
    }
}
