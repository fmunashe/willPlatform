package zw.co.zim.willplatform.dto.mapper;

import zw.co.zim.willplatform.dto.AssetInvestmentRecordDto;
import zw.co.zim.willplatform.model.AssetInvestment;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class AssetInvestmentDtoMapper implements Function<AssetInvestment, AssetInvestmentRecordDto> {
    @Override
    public AssetInvestmentRecordDto apply(AssetInvestment assetInvestment) {
        return new AssetInvestmentRecordDto(assetInvestment.getInvestmentType(), assetInvestment.getInvestmentValue(), assetInvestment.getCompany(), assetInvestment.getUserId());
    }
}
