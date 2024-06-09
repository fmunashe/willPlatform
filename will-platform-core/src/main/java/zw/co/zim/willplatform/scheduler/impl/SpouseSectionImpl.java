package zw.co.zim.willplatform.scheduler.impl;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import zw.co.zim.willplatform.model.Client;
import zw.co.zim.willplatform.model.SignupProgressJourney;
import zw.co.zim.willplatform.model.UsersSpouse;
import zw.co.zim.willplatform.scheduler.api.SpouseSection;
import zw.co.zim.willplatform.service.QuestionnaireService;
import zw.co.zim.willplatform.service.UsersSpouseService;
import zw.co.zim.willplatform.utils.AppConstants;
import zw.co.zim.willplatform.utils.enums.MaritalStatus;

@Service
public class SpouseSectionImpl implements SpouseSection {
    private final QuestionnaireService questionnaireService;
    private final UsersSpouseService spouseService;

    public SpouseSectionImpl(QuestionnaireService questionnaireService, UsersSpouseService spouseService) {
        this.questionnaireService = questionnaireService;
        this.spouseService = spouseService;
    }

    @Override
    public boolean completeSpouseSection(SignupProgressJourney progressJourney) {
        Client client = progressJourney.getUserId();

        return questionnaireService.findFirstByUserId(client)
            .map(questionnaire -> {
                MaritalStatus maritalStatus = questionnaire.getMaritalStatus();
                if (questionnaire.getFormerSpouse() || maritalStatus == MaritalStatus.MARRIED || maritalStatus == MaritalStatus.DIVORCED) {
                    return hasSpouse(client);
                }
                return false;
            })
            .orElse(false);
    }

    private boolean hasSpouse(Client client) {
        Page<UsersSpouse> spousePage = spouseService.findAllByUserId(client, getPageNumber(), getPageSize());
        return spousePage.hasContent();
    }

    private int getPageNumber() {
        return Integer.parseInt(AppConstants.DEFAULT_PAGE_NUMBER);
    }

    private int getPageSize() {
        return Integer.parseInt(AppConstants.DEFAULT_PAGE_SIZE);
    }

}
