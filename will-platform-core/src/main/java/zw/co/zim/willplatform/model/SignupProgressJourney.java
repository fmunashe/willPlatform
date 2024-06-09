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
public class SignupProgressJourney extends BaseEntity {
    @OneToOne
    private Client userId;
    private Boolean completedQuestionnaire;
    private Boolean completedPersonalSection;
    private Boolean completedSpouseSection;
    private Boolean completedAssetsSection;
    private Boolean completedLiabilitiesSection;
    private Boolean completedWillSection;
    private Boolean completedBeneficiarySection;
    private Boolean completedChildrenSection;
    private Boolean completedGuardianSection;
    private Boolean willComplete;
    private Boolean finalisedWill;
    private Boolean subscribed;
    @Enumerated(EnumType.STRING)
    private RecordStatus recordStatus;
}
