package zw.co.zim.willplatform.dto.mapper;

import zw.co.zim.willplatform.dto.BankAssetRecordDto;
import zw.co.zim.willplatform.model.BankAsset;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class BankAssetDtoMapper implements Function<BankAsset, BankAssetRecordDto> {
    @Override
    public BankAssetRecordDto apply(BankAsset bankAsset) {
        return new BankAssetRecordDto(bankAsset.getBankName(), bankAsset.getAccountNumber(),bankAsset.getBalance(), bankAsset.getUserId());
    }
}
