package zw.co.zim.willplatform.dto.mapper;

import zw.co.zim.willplatform.dto.AssetPolicyRecordDto;
import zw.co.zim.willplatform.model.AssetPolicy;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class AssetPolicyDtoMapper implements Function<AssetPolicy, AssetPolicyRecordDto> {
    @Override
    public AssetPolicyRecordDto apply(AssetPolicy assetPolicy) {
        return new AssetPolicyRecordDto(assetPolicy.getPolicyType(), assetPolicy.getPolicyNumber(), assetPolicy.getCompany(), assetPolicy.getPolicyValue(), assetPolicy.getUserId());
    }
}
