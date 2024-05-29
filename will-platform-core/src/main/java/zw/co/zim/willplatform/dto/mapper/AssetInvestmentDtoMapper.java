package zw.co.zim.willplatform.dto.mapper;

import org.springframework.stereotype.Service;
import zw.co.zim.willplatform.dto.AssetInvestmentRecordDto;
import zw.co.zim.willplatform.model.AssetInvestment;

import java.util.function.Function;

@Service
public class AssetInvestmentDtoMapper implements Function<AssetInvestment, AssetInvestmentRecordDto> {
    @Override
    public AssetInvestmentRecordDto apply(AssetInvestment assetInvestment) {
        return AssetInvestmentRecordDto.builder()
            .id(assetInvestment.getId())
            .investmentType(assetInvestment.getInvestmentType())
            .value(assetInvestment.getInvestmentValue())
            .company(assetInvestment.getCompany())
            .userId(assetInvestment.getUserId())
            .recordStatus(assetInvestment.getRecordStatus())
            .build();
    }
}
