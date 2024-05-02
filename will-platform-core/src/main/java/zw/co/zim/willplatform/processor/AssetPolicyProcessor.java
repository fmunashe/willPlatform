package zw.co.zim.willplatform.processor;

import zw.co.zim.willplatform.common.AppService;
import zw.co.zim.willplatform.dto.AssetPolicyRecordDto;

import java.util.Optional;

public interface AssetPolicyProcessor extends AppService<AssetPolicyRecordDto> {
    Optional<AssetPolicyRecordDto> findFirstByPolicyNumber(String policyNumber);
}
