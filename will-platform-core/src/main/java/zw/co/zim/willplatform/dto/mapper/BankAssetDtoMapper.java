package zw.co.zim.willplatform.dto.mapper;

import org.springframework.stereotype.Service;
import zw.co.zim.willplatform.dto.BankAssetRecordDto;
import zw.co.zim.willplatform.model.BankAsset;

import java.util.function.Function;

@Service
public class BankAssetDtoMapper implements Function<BankAsset, BankAssetRecordDto> {
    @Override
    public BankAssetRecordDto apply(BankAsset bankAsset) {
        return BankAssetRecordDto.builder()
            .id(bankAsset.getId())
            .bankName(bankAsset.getBankName())
            .accountNumber(bankAsset.getAccountNumber())
            .balance(bankAsset.getBalance())
            .user(bankAsset.getUserId())
            .createdAt(bankAsset.getCreatedAt())
            .updatedAt(bankAsset.getUpdatedAt())
            .recordStatus(bankAsset.getRecordStatus())
            .build();
    }
}
