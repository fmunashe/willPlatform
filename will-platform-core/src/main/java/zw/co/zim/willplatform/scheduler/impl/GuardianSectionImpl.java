package zw.co.zim.willplatform.scheduler.impl;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import zw.co.zim.willplatform.model.Client;
import zw.co.zim.willplatform.model.SignupProgressJourney;
import zw.co.zim.willplatform.model.UsersChild;
import zw.co.zim.willplatform.model.UsersGuardian;
import zw.co.zim.willplatform.scheduler.api.GuardianSection;
import zw.co.zim.willplatform.service.QuestionnaireService;
import zw.co.zim.willplatform.service.UsersChildService;
import zw.co.zim.willplatform.service.UsersGuardianService;
import zw.co.zim.willplatform.utils.AppConstants;

import java.time.LocalDate;
import java.time.Period;

@Service
public class GuardianSectionImpl implements GuardianSection {
    private final QuestionnaireService questionnaireService;
    private final UsersChildService childService;
    private final UsersGuardianService guardianService;

    public GuardianSectionImpl(QuestionnaireService questionnaireService, UsersChildService childService, UsersGuardianService guardianService) {
        this.questionnaireService = questionnaireService;
        this.childService = childService;
        this.guardianService = guardianService;
    }

    @Override
    public boolean completeGuardianSection(SignupProgressJourney progressJourney) {
        Client client = progressJourney.getUserId();

        return questionnaireService.findFirstByUserId(client)
            .map(questionnaire -> {
                if (!questionnaire.getHaveChildren()) {
                    return true;
                }
                boolean hasEligibleChildren = hasEligibleChildren(client);
                boolean hasGuardians = hasGuardians(client);
                return hasEligibleChildren || hasGuardians;
            })
            .orElse(false);
    }

    private boolean hasEligibleChildren(Client client) {
        Page<UsersChild> childPage = childService.findAllByUserId(client, getPageNumber(), getPageSize());
        return childPage.hasContent() && childPage.getContent().stream().anyMatch(child -> calculateAgeInYears(child.getDob()) < 18);
    }

    private boolean hasGuardians(Client client) {
        Page<UsersGuardian> guardianPage = guardianService.findAllByUserId(client, getPageNumber(), getPageSize());
        return guardianPage.hasContent();
    }

    private int getPageNumber() {
        return Integer.parseInt(AppConstants.DEFAULT_PAGE_NUMBER);
    }

    private int getPageSize() {
        return Integer.parseInt(AppConstants.DEFAULT_PAGE_SIZE);
    }

    private int calculateAgeInYears(LocalDate dob) {
        return Period.between(dob, LocalDate.now()).getYears();
    }
}
