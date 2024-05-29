package zw.co.zim.willplatform.dto.mapper;

import org.springframework.stereotype.Service;
import zw.co.zim.willplatform.dto.AssetTimeShareDto;
import zw.co.zim.willplatform.model.AssetTimeShare;

import java.util.function.Function;

@Service
public class AssetTimeshareDtoMapper implements Function<AssetTimeShare, AssetTimeShareDto> {
    @Override
    public AssetTimeShareDto apply(AssetTimeShare assetTimeShare) {
        return AssetTimeShareDto.builder()
            .id(assetTimeShare.getId())
            .userId(assetTimeShare.getUserId())
            .description(assetTimeShare.getDescription())
            .value(assetTimeShare.getTimeshareValue())
            .createdAt(assetTimeShare.getCreatedAt())
            .updatedAt(assetTimeShare.getUpdatedAt())
            .recordStatus(assetTimeShare.getRecordStatus())
            .build();
    }
}
