package zw.co.zim.willplatform.dto.mapper;

import org.springframework.stereotype.Service;
import zw.co.zim.willplatform.dto.AssetOffshoreRecordDto;
import zw.co.zim.willplatform.model.AssetOffshore;

import java.util.function.Function;

@Service
public class AssetOffshoreDtoMapper implements Function<AssetOffshore, AssetOffshoreRecordDto> {
    @Override
    public AssetOffshoreRecordDto apply(AssetOffshore assetOffshore) {
        return AssetOffshoreRecordDto.builder()
            .id(assetOffshore.getId())
            .value(assetOffshore.getOffshoreValue())
            .description(assetOffshore.getDescription())
            .userId(assetOffshore.getUserId())
            .recordStatus(assetOffshore.getRecordStatus())
            .build();
    }
}
