package zw.co.zim.willplatform.scheduler.api;

import zw.co.zim.willplatform.model.SignupProgressJourney;

public interface AssetsSection {
    boolean isAssetVehicleComplete(SignupProgressJourney progressJourney);

    boolean isAssetPropertyComplete(SignupProgressJourney progressJourney);

    boolean isAssetBankComplete(SignupProgressJourney progressJourney);

    boolean isAssetTrustComplete(SignupProgressJourney progressJourney);

    boolean isAssetOtherComplete(SignupProgressJourney progressJourney);

    boolean isAssetTimeshareComplete(SignupProgressJourney progressJourney);

    boolean isAssetPolicyComplete(SignupProgressJourney progressJourney);

    boolean isAssetPersonOwingMoneyComplete(SignupProgressJourney progressJourney);

    boolean isAssetOffshoreComplete(SignupProgressJourney progressJourney);

    boolean isAssetInvestmentComplete(SignupProgressJourney progressJourney);
}
