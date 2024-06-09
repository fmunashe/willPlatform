package zw.co.zim.willplatform.scheduler.impl;

import org.springframework.stereotype.Service;
import zw.co.zim.willplatform.model.SignupProgressJourney;
import zw.co.zim.willplatform.scheduler.api.AssetsSection;
import zw.co.zim.willplatform.service.QuestionnaireService;

@Service
public class AssetsSectionImpl implements AssetsSection {
    private final QuestionnaireService questionnaireService;

    public AssetsSectionImpl(QuestionnaireService questionnaireService) {
        this.questionnaireService = questionnaireService;
    }

    @Override
    public boolean isAssetVehicleComplete(SignupProgressJourney progressJourney) {
        return false;
    }

    @Override
    public boolean isAssetPropertyComplete(SignupProgressJourney progressJourney) {
        return false;
    }

    @Override
    public boolean isAssetBankComplete(SignupProgressJourney progressJourney) {
        return false;
    }

    @Override
    public boolean isAssetTrustComplete(SignupProgressJourney progressJourney) {
        return false;
    }

    @Override
    public boolean isAssetOtherComplete(SignupProgressJourney progressJourney) {
        return false;
    }

    @Override
    public boolean isAssetTimeshareComplete(SignupProgressJourney progressJourney) {
        return false;
    }

    @Override
    public boolean isAssetPolicyComplete(SignupProgressJourney progressJourney) {
        return false;
    }

    @Override
    public boolean isAssetPersonOwingMoneyComplete(SignupProgressJourney progressJourney) {
        return false;
    }

    @Override
    public boolean isAssetOffshoreComplete(SignupProgressJourney progressJourney) {
        return false;
    }

    @Override
    public boolean isAssetInvestmentComplete(SignupProgressJourney progressJourney) {
        return false;
    }


}
