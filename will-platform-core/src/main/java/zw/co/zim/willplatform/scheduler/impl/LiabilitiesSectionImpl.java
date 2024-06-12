package zw.co.zim.willplatform.scheduler.impl;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import zw.co.zim.willplatform.model.*;
import zw.co.zim.willplatform.scheduler.api.LiabilitiesSection;
import zw.co.zim.willplatform.service.*;
import zw.co.zim.willplatform.utils.AppConstants;

@Service
public class LiabilitiesSectionImpl implements LiabilitiesSection {

    private final QuestionnaireService questionnaireService;
    private final LiabilitiesCreditCardService creditCardService;
    private final LiabilitiesOutstandingLoanService loanService;
    private final LiabilitiesSubscribedWrittenPublicationService writtenPublicationService;
    private final LiabilitiesOutstandingAccountService accountService;
    private final LiabilitiesUserOweMoneyService moneyService;

    public LiabilitiesSectionImpl(QuestionnaireService questionnaireService, LiabilitiesCreditCardService creditCardService, LiabilitiesOutstandingLoanService loanService, LiabilitiesSubscribedWrittenPublicationService writtenPublicationService, LiabilitiesOutstandingAccountService accountService, LiabilitiesUserOweMoneyService moneyService) {
        this.questionnaireService = questionnaireService;
        this.creditCardService = creditCardService;
        this.loanService = loanService;
        this.writtenPublicationService = writtenPublicationService;
        this.accountService = accountService;
        this.moneyService = moneyService;
    }

    @Override
    public boolean isLiabilitiesCreditCardComplete(SignupProgressJourney progressJourney) {
        Client client = progressJourney.getUserId();

        return questionnaireService.findFirstByUserId(client)
            .map(questionnaire -> {
                if (!questionnaire.getHaveCreditCard()) {
                    return true;
                }
                return hasCreditCards(client);
            })
            .orElse(false);
    }

    @Override
    public boolean isLiabilitiesOutstandingAccountComplete(SignupProgressJourney progressJourney) {
        Client client = progressJourney.getUserId();

        return questionnaireService.findFirstByUserId(client)
            .map(questionnaire -> {
                if (!questionnaire.getHaveOutstandingAccounts()) {
                    return true;
                }
                return hasOutstandingAccounts(client);
            })
            .orElse(false);
    }

    @Override
    public boolean isLiabilitiesOutstandingLoanComplete(SignupProgressJourney progressJourney) {
        Client client = progressJourney.getUserId();

        return questionnaireService.findFirstByUserId(client)
            .map(questionnaire -> {
                if (!questionnaire.getHaveOutstandingLoans()) {
                    return true;
                }
                return hasOutstandingLoans(client);
            })
            .orElse(false);
    }

    @Override
    public boolean isLiabilitiesUserOwesMoneyComplete(SignupProgressJourney progressJourney) {
        Client client = progressJourney.getUserId();

        return questionnaireService.findFirstByUserId(client)
            .map(questionnaire -> {
                if (!questionnaire.getUserOweMoney()) {
                    return true;
                }
                return userOwesMoney(client);
            })
            .orElse(false);
    }

    @Override
    public boolean isLiabilitiesWrittenPublicationComplete(SignupProgressJourney progressJourney) {
        Client client = progressJourney.getUserId();

        return questionnaireService.findFirstByUserId(client)
            .map(questionnaire -> {
                if (!questionnaire.getSubscribedWrittenPublication()) {
                    return true;
                }
                return hasSubscribedToWrittenPublications(client);
            })
            .orElse(false);
    }

    private boolean hasCreditCards(Client client) {
        Page<LiabilitiesCreditCard> creditCards = creditCardService.findAllByUserId(client, getPageNumber(), getPageSize());
        return creditCards.hasContent();
    }

    private boolean hasOutstandingAccounts(Client client) {
        Page<LiabilitiesOutstandingAccount> outstandingAccounts = accountService.findAllByUserId(client, getPageNumber(), getPageSize());
        return outstandingAccounts.hasContent();
    }

    private boolean hasOutstandingLoans(Client client) {
        Page<LiabilitiesOutstandingLoan> outstandingLoans = loanService.findAllByUserId(client, getPageNumber(), getPageSize());
        return outstandingLoans.hasContent();
    }

    private boolean hasSubscribedToWrittenPublications(Client client) {
        Page<LiabilitiesSubscribedWrittenPublication> publications = writtenPublicationService.findAllByUserId(client, getPageNumber(), getPageSize());
        return publications.hasContent();
    }

    private boolean userOwesMoney(Client client) {
        Page<LiabilitiesUserOwesMoney> owesMonies = moneyService.findAllByUserId(client, getPageNumber(), getPageSize());
        return owesMonies.hasContent();
    }

    private int getPageNumber() {
        return Integer.parseInt(AppConstants.DEFAULT_PAGE_NUMBER);
    }

    private int getPageSize() {
        return Integer.parseInt(AppConstants.DEFAULT_PAGE_SIZE);
    }
}
