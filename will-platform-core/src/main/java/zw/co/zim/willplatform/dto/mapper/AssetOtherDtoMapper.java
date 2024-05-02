package zw.co.zim.willplatform.dto.mapper;

import zw.co.zim.willplatform.dto.AssetOtherRecordDto;
import zw.co.zim.willplatform.model.AssetOther;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class AssetOtherDtoMapper implements Function<AssetOther, AssetOtherRecordDto> {
    @Override
    public AssetOtherRecordDto apply(AssetOther assetOther) {
        return new AssetOtherRecordDto(assetOther.getDescription(), assetOther.getAssetValue(), assetOther.getUserId());
    }
}
