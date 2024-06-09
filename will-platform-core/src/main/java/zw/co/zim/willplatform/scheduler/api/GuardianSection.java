package zw.co.zim.willplatform.scheduler.api;

import zw.co.zim.willplatform.model.SignupProgressJourney;

public interface GuardianSection {
    boolean completeGuardianSection(SignupProgressJourney progressJourney);
}
