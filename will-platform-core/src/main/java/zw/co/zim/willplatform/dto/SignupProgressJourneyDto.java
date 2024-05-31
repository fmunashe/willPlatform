package zw.co.zim.willplatform.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import zw.co.zim.willplatform.model.Client;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@SuperBuilder
public class SignupProgressJourneyDto extends BaseDto {
    private Client userId;
    private Boolean completedQuestionnaire;
    private Boolean completedPersonalSection;
    private Boolean completedSpouseSection;
    private Boolean completedAssetsSection;
    private Boolean completedLiabilitiesSection;
    private Boolean completedWillSection;
    private Boolean willComplete;
    private Boolean finalisedWill;
    private Boolean subscribed;
}
