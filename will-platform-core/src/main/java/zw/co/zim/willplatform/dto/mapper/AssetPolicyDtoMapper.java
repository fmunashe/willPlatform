package zw.co.zim.willplatform.dto.mapper;

import org.springframework.stereotype.Service;
import zw.co.zim.willplatform.dto.AssetPolicyRecordDto;
import zw.co.zim.willplatform.model.AssetPolicy;

import java.util.function.Function;

@Service
public class AssetPolicyDtoMapper implements Function<AssetPolicy, AssetPolicyRecordDto> {
    @Override
    public AssetPolicyRecordDto apply(AssetPolicy assetPolicy) {
        return AssetPolicyRecordDto.builder()
            .id(assetPolicy.getId())
            .policyNumber(assetPolicy.getPolicyNumber())
            .policyType(assetPolicy.getPolicyType())
            .company(assetPolicy.getCompany())
            .value(assetPolicy.getPolicyValue())
            .userId(assetPolicy.getUserId())
            .recordStatus(assetPolicy.getRecordStatus())
            .createdAt(assetPolicy.getCreatedAt())
            .updatedAt(assetPolicy.getUpdatedAt())
            .build();
    }
}
