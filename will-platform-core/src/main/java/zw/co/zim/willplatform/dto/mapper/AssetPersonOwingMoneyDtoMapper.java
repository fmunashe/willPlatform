package zw.co.zim.willplatform.dto.mapper;

import zw.co.zim.willplatform.dto.AssetPersonOwingMoneyRecordDto;
import zw.co.zim.willplatform.model.AssetPersonOwingMoney;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class AssetPersonOwingMoneyDtoMapper implements Function<AssetPersonOwingMoney, AssetPersonOwingMoneyRecordDto> {
    @Override
    public AssetPersonOwingMoneyRecordDto apply(AssetPersonOwingMoney assetPersonOwingMoney) {
        return new AssetPersonOwingMoneyRecordDto(assetPersonOwingMoney.getPersonDetails(), assetPersonOwingMoney.getAmountOwed(), assetPersonOwingMoney.getContactNumber(), assetPersonOwingMoney.getUserId());
    }
}
