package zw.co.zim.willplatform.model;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import lombok.*;

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
    private Boolean willComplete;
    private Boolean finalisedWill;
    private Boolean subscribed;
}
