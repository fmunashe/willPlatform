package zw.co.zim.willplatform.service;

import zw.co.zim.willplatform.common.AppService;
import zw.co.zim.willplatform.model.AssetPolicy;

import java.util.Optional;

public interface AssetPolicyService extends AppService<AssetPolicy> {
    Optional<AssetPolicy> findFirstByPolicyNumber(String policyNumber);
}
