package zw.co.zim.willplatform.scheduler.api;

import zw.co.zim.willplatform.model.SignupProgressJourney;

public interface LiabilitiesSection {
    boolean isLiabilitiesCreditCardComplete(SignupProgressJourney progressJourney);

    boolean isLiabilitiesOutstandingAccountComplete(SignupProgressJourney progressJourney);

    boolean isLiabilitiesOutstandingLoanComplete(SignupProgressJourney progressJourney);

    boolean isLiabilitiesUserOwesMoneyComplete(SignupProgressJourney progressJourney);

    boolean isLiabilitiesWrittenPublicationComplete(SignupProgressJourney progressJourney);
}
