package zw.co.zim.willplatform.dto.mapper;

import org.springframework.stereotype.Service;
import zw.co.zim.willplatform.dto.AssetOtherRecordDto;
import zw.co.zim.willplatform.model.AssetOther;

import java.util.function.Function;

@Service
public class AssetOtherDtoMapper implements Function<AssetOther, AssetOtherRecordDto> {
    @Override
    public AssetOtherRecordDto apply(AssetOther assetOther) {
        return AssetOtherRecordDto.builder()
            .id(assetOther.getId())
            .value(assetOther.getAssetValue())
            .description(assetOther.getDescription())
            .createdAt(assetOther.getCreatedAt())
            .updatedAt(assetOther.getUpdatedAt())
            .userId(assetOther.getUserId())
            .recordStatus(assetOther.getRecordStatus())
            .build();
    }
}
