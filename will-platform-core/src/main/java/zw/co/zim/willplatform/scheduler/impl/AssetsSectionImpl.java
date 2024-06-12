package zw.co.zim.willplatform.scheduler.impl;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import zw.co.zim.willplatform.model.*;
import zw.co.zim.willplatform.scheduler.api.AssetsSection;
import zw.co.zim.willplatform.service.*;
import zw.co.zim.willplatform.utils.AppConstants;

@Service
public class AssetsSectionImpl implements AssetsSection {
    private final QuestionnaireService questionnaireService;
    private final VehicleAssetService vehicleAssetService;
    private final PropertyAssetService propertyAssetService;
    private final BankAssetService bankAssetService;
    private final AssetTrustService trustService;
    private final AssetOtherService otherService;
    private final AssetTimeshareService timeshareService;
    private final AssetPolicyService policyService;
    private final AssetPersonOwingMoneyService personOwingMoneyService;
    private final AssetOffshoreService offshoreService;
    private final AssetInvestmentService assetInvestmentService;

    public AssetsSectionImpl(QuestionnaireService questionnaireService, VehicleAssetService vehicleAssetService, PropertyAssetService propertyAssetService, BankAssetService bankAssetService, AssetTrustService trustService, AssetOtherService otherService, AssetTimeshareService timeshareService, AssetPolicyService policyService, AssetPersonOwingMoneyService personOwingMoneyService, AssetOffshoreService offshoreService, AssetInvestmentService assetInvestmentService) {
        this.questionnaireService = questionnaireService;
        this.vehicleAssetService = vehicleAssetService;
        this.propertyAssetService = propertyAssetService;
        this.bankAssetService = bankAssetService;
        this.trustService = trustService;
        this.otherService = otherService;
        this.timeshareService = timeshareService;
        this.policyService = policyService;
        this.personOwingMoneyService = personOwingMoneyService;
        this.offshoreService = offshoreService;
        this.assetInvestmentService = assetInvestmentService;
    }

    @Override
    public boolean isAssetVehicleComplete(SignupProgressJourney progressJourney) {
        Client client = progressJourney.getUserId();

        return questionnaireService.findFirstByUserId(client)
            .map(questionnaire -> {
                if (!questionnaire.getHaveVehicle()) {
                    return true;
                }
                return hasVehicles(client);
            })
            .orElse(false);
    }

    @Override
    public boolean isAssetPropertyComplete(SignupProgressJourney progressJourney) {
        Client client = progressJourney.getUserId();

        return questionnaireService.findFirstByUserId(client)
            .map(questionnaire -> {
                if (!questionnaire.getHaveProperty()) {
                    return true;
                }
                return hasProperty(client);
            })
            .orElse(false);
    }

    @Override
    public boolean isAssetBankComplete(SignupProgressJourney progressJourney) {
        Client client = progressJourney.getUserId();

        return questionnaireService.findFirstByUserId(client)
            .map(questionnaire -> {
                if (!questionnaire.getHaveBankAccount()) {
                    return true;
                }
                return hasBankAccount(client);
            })
            .orElse(false);
    }

    @Override
    public boolean isAssetTrustComplete(SignupProgressJourney progressJourney) {
        Client client = progressJourney.getUserId();

        return questionnaireService.findFirstByUserId(client)
            .map(questionnaire -> {
                if (!questionnaire.getHaveAssetsTrust()) {
                    return true;
                }
                return hasAssetTrust(client);
            })
            .orElse(false);
    }

    @Override
    public boolean isAssetOtherComplete(SignupProgressJourney progressJourney) {
        Client client = progressJourney.getUserId();

        return questionnaireService.findFirstByUserId(client)
            .map(questionnaire -> {
                if (!questionnaire.getHaveOtherAssets()) {
                    return true;
                }
                return hasOtherAssets(client);
            })
            .orElse(false);
    }

    @Override
    public boolean isAssetTimeshareComplete(SignupProgressJourney progressJourney) {
        Client client = progressJourney.getUserId();

        return questionnaireService.findFirstByUserId(client)
            .map(questionnaire -> {
                if (!questionnaire.getHaveTimeShare()) {
                    return true;
                }
                return hasTimeshare(client);
            })
            .orElse(false);
    }

    @Override
    public boolean isAssetPolicyComplete(SignupProgressJourney progressJourney) {
        Client client = progressJourney.getUserId();

        return questionnaireService.findFirstByUserId(client)
            .map(questionnaire -> {
                if (!questionnaire.getHavePolicy()) {
                    return true;
                }
                return hasPolicy(client);
            })
            .orElse(false);
    }

    @Override
    public boolean isAssetPersonOwingMoneyComplete(SignupProgressJourney progressJourney) {
        Client client = progressJourney.getUserId();

        return questionnaireService.findFirstByUserId(client)
            .map(questionnaire -> {
                if (!questionnaire.getSomeOneOweMoney()) {
                    return true;
                }
                return hasSomeoneOwingMoney(client);
            })
            .orElse(false);
    }

    @Override
    public boolean isAssetOffshoreComplete(SignupProgressJourney progressJourney) {
        Client client = progressJourney.getUserId();

        return questionnaireService.findFirstByUserId(client)
            .map(questionnaire -> {
                if (!questionnaire.getHaveAssetsOffshore()) {
                    return true;
                }
                return hasOffshoreAssets(client);
            })
            .orElse(false);
    }

    @Override
    public boolean isAssetInvestmentComplete(SignupProgressJourney progressJourney) {
        Client client = progressJourney.getUserId();

        return questionnaireService.findFirstByUserId(client)
            .map(questionnaire -> {
                if (!questionnaire.getHaveInvestment()) {
                    return true;
                }
                return hasInvestments(client);
            })
            .orElse(false);
    }


    private boolean hasVehicles(Client client) {
        Page<VehicleAsset> vehicleAssets = vehicleAssetService.findAllByUserId(client, getPageNumber(), getPageSize());
        return vehicleAssets.hasContent();
    }

    private boolean hasProperty(Client client) {
        Page<PropertyAsset> property = propertyAssetService.findAllByUserId(client, getPageNumber(), getPageSize());
        return property.hasContent();
    }

    private boolean hasBankAccount(Client client) {
        Page<BankAsset> bankAsset = bankAssetService.findAllByUserId(client, getPageNumber(), getPageSize());
        return bankAsset.hasContent();
    }

    private boolean hasAssetTrust(Client client) {
        Page<AssetTrust> trustPage = trustService.findAllByUserId(client, getPageNumber(), getPageSize());
        return trustPage.hasContent();
    }

    private boolean hasOtherAssets(Client client) {
        Page<AssetOther> otherPage = otherService.findAllByUserId(client, getPageNumber(), getPageSize());
        return otherPage.hasContent();
    }

    private boolean hasTimeshare(Client client) {
        Page<AssetTimeShare> timeSharePage = timeshareService.findAllByUserId(client, getPageNumber(), getPageSize());
        return timeSharePage.hasContent();
    }

    private boolean hasPolicy(Client client) {
        Page<AssetPolicy> policy = policyService.findAllByUserId(client, getPageNumber(), getPageSize());
        return policy.hasContent();
    }

    private boolean hasSomeoneOwingMoney(Client client) {
        Page<AssetPersonOwingMoney> personOwingMoneyPage = personOwingMoneyService.findAllByUserId(client, getPageNumber(), getPageSize());
        return personOwingMoneyPage.hasContent();
    }

    private boolean hasOffshoreAssets(Client client) {
        Page<AssetOffshore> offshore = offshoreService.findAllByUserId(client, getPageNumber(), getPageSize());
        return offshore.hasContent();
    }

    private boolean hasInvestments(Client client) {
        Page<AssetInvestment> investments = assetInvestmentService.findAllByUserId(client, getPageNumber(), getPageSize());
        return investments.hasContent();
    }

    private int getPageNumber() {
        return Integer.parseInt(AppConstants.DEFAULT_PAGE_NUMBER);
    }

    private int getPageSize() {
        return Integer.parseInt(AppConstants.DEFAULT_PAGE_SIZE);
    }


}
