package zw.co.zim.willplatform.dto.mapper;

import zw.co.zim.willplatform.dto.AssetOffshoreRecordDto;
import zw.co.zim.willplatform.model.AssetOffshore;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class AssetOffshoreDtoMapper implements Function<AssetOffshore, AssetOffshoreRecordDto> {
    @Override
    public AssetOffshoreRecordDto apply(AssetOffshore assetOffshore) {
        return new AssetOffshoreRecordDto(assetOffshore.getDescription(), assetOffshore.getOffshoreValue(), assetOffshore.getUserId(),assetOffshore.getRecordStatus());
    }
}
