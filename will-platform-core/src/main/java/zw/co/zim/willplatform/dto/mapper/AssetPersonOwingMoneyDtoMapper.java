package zw.co.zim.willplatform.dto.mapper;

import org.springframework.stereotype.Service;
import zw.co.zim.willplatform.dto.AssetPersonOwingMoneyRecordDto;
import zw.co.zim.willplatform.model.AssetPersonOwingMoney;

import java.util.function.Function;

@Service
public class AssetPersonOwingMoneyDtoMapper implements Function<AssetPersonOwingMoney, AssetPersonOwingMoneyRecordDto> {
    @Override
    public AssetPersonOwingMoneyRecordDto apply(AssetPersonOwingMoney assetPersonOwingMoney) {
        return AssetPersonOwingMoneyRecordDto.builder()
            .id(assetPersonOwingMoney.getId())
            .amountOwed(assetPersonOwingMoney.getAmountOwed())
            .personDetails(assetPersonOwingMoney.getPersonDetails())
            .contactNumber(assetPersonOwingMoney.getContactNumber())
            .userId(assetPersonOwingMoney.getUserId())
            .recordStatus(assetPersonOwingMoney.getRecordStatus())
            .createdAt(assetPersonOwingMoney.getCreatedAt())
            .updatedAt(assetPersonOwingMoney.getUpdatedAt())
            .build();
    }
}
