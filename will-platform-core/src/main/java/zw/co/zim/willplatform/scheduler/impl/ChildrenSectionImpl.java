package zw.co.zim.willplatform.scheduler.impl;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import zw.co.zim.willplatform.model.Client;
import zw.co.zim.willplatform.model.Questionnaire;
import zw.co.zim.willplatform.model.SignupProgressJourney;
import zw.co.zim.willplatform.model.UsersChild;
import zw.co.zim.willplatform.scheduler.api.ChildrenSection;
import zw.co.zim.willplatform.service.QuestionnaireService;
import zw.co.zim.willplatform.service.UsersChildService;
import zw.co.zim.willplatform.utils.AppConstants;

@Service
public class ChildrenSectionImpl implements ChildrenSection {
    private final QuestionnaireService questionnaireService;
    private final UsersChildService childService;

    public ChildrenSectionImpl(QuestionnaireService questionnaireService, UsersChildService childService) {
        this.questionnaireService = questionnaireService;
        this.childService = childService;
    }

    @Override
    public boolean completeChildrenSection(SignupProgressJourney progressJourney) {
        Client client = progressJourney.getUserId();

        return questionnaireService.findFirstByUserId(client)
            .map(Questionnaire::getHaveChildren)
            .filter(Boolean::booleanValue)
            .map(haveChildren -> hasChildren(client))
            .orElse(false);
    }

    private boolean hasChildren(Client client) {
        Page<UsersChild> childPage = childService.findAllByUserId(client, getPageNumber(), getPageSize());
        return childPage.hasContent();
    }

    private int getPageNumber() {
        return Integer.parseInt(AppConstants.DEFAULT_PAGE_NUMBER);
    }

    private int getPageSize() {
        return Integer.parseInt(AppConstants.DEFAULT_PAGE_SIZE);
    }
}
