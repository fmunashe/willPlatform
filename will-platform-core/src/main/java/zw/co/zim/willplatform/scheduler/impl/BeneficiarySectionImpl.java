package zw.co.zim.willplatform.scheduler.impl;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import zw.co.zim.willplatform.model.Client;
import zw.co.zim.willplatform.model.SignupProgressJourney;
import zw.co.zim.willplatform.model.UsersBeneficiary;
import zw.co.zim.willplatform.scheduler.api.BeneficiarySection;
import zw.co.zim.willplatform.service.UsersBeneficiaryService;
import zw.co.zim.willplatform.utils.AppConstants;

@Service
public class BeneficiarySectionImpl implements BeneficiarySection {
    private final UsersBeneficiaryService beneficiaryService;

    public BeneficiarySectionImpl(UsersBeneficiaryService beneficiaryService) {
        this.beneficiaryService = beneficiaryService;
    }

    @Override
    public boolean completeBeneficiarySection(SignupProgressJourney progressJourney) {
        Client client = progressJourney.getUserId();
        Page<UsersBeneficiary> beneficiaries = beneficiaryService.findAllByUserId(client, getPageNumber(), getPageSize());

        if (beneficiaries.hasContent()) {
            double totalPercentage = beneficiaries.getContent().stream().mapToDouble(UsersBeneficiary::getPercentage).sum();
            return totalPercentage == 100;
        }

        return false;
    }

    private int getPageNumber() {
        return Integer.parseInt(AppConstants.DEFAULT_PAGE_NUMBER);
    }

    private int getPageSize() {
        return Integer.parseInt(AppConstants.DEFAULT_PAGE_SIZE);
    }
}
