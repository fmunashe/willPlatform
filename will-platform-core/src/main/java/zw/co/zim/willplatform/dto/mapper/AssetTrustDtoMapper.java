package zw.co.zim.willplatform.dto.mapper;

import org.springframework.stereotype.Service;
import zw.co.zim.willplatform.dto.AssetTrustDto;
import zw.co.zim.willplatform.model.AssetTrust;

import java.util.function.Function;

@Service
public class AssetTrustDtoMapper implements Function<AssetTrust, AssetTrustDto> {
    @Override
    public AssetTrustDto apply(AssetTrust assetTrust) {
        return AssetTrustDto.builder()
            .id(assetTrust.getId())
            .nameOfTrust(assetTrust.getNameOfTrust())
            .value(assetTrust.getTrustValue())
            .userId(assetTrust.getUserId())
            .createdAt(assetTrust.getCreatedAt())
            .updatedAt(assetTrust.getUpdatedAt())
            .recordStatus(assetTrust.getRecordStatus())
            .build();
    }
}
