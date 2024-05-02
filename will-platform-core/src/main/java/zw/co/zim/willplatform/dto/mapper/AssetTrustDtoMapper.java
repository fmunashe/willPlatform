package zw.co.zim.willplatform.dto.mapper;

import zw.co.zim.willplatform.dto.AssetTrustDto;
import zw.co.zim.willplatform.model.AssetTrust;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class AssetTrustDtoMapper implements Function<AssetTrust, AssetTrustDto> {
    @Override
    public AssetTrustDto apply(AssetTrust assetTrust) {
        return new AssetTrustDto(assetTrust.getNameOfTrust(), assetTrust.getTrustValue(), assetTrust.getUserId());
    }
}
