package zw.co.zim.willplatform.scheduler.impl;

import org.springframework.stereotype.Service;
import zw.co.zim.willplatform.model.Client;
import zw.co.zim.willplatform.model.SignupProgressJourney;
import zw.co.zim.willplatform.scheduler.api.PersonalSection;

@Service
public class PersonalSectionImpl implements PersonalSection {
    @Override
    public boolean isPersonalSectionComplete(SignupProgressJourney progressJourney) {
        Client client = progressJourney.getUserId();
        return client.getAcceptedTermsAndConditions()
            && client.getFirstName() != null
            && client.getLastName() != null
            && client.getDateOfBirth() != null
            && client.getNationalIdNumber() != null
            && client.getEmail() != null
            && client.getMobileNumber() != null;
    }
}
