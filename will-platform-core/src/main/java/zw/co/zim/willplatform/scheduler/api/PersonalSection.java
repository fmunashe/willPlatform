package zw.co.zim.willplatform.scheduler.api;

import zw.co.zim.willplatform.model.SignupProgressJourney;

public interface PersonalSection {
    boolean isPersonalSectionComplete(SignupProgressJourney progressJourney);
}
