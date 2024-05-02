package zw.co.zim.willplatform.dto.mapper;

import zw.co.zim.willplatform.dto.AssetTimeShareDto;
import zw.co.zim.willplatform.model.AssetTimeShare;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class AssetTimeshareDtoMapper implements Function<AssetTimeShare, AssetTimeShareDto> {
    @Override
    public AssetTimeShareDto apply(AssetTimeShare assetTimeShare) {
        return new AssetTimeShareDto(assetTimeShare.getDescription(), assetTimeShare.getTimeshareValue(), assetTimeShare.getUserId());
    }
}
