package zw.co.zim.willplatform.dto.mapper;

import org.springframework.stereotype.Service;
import zw.co.zim.willplatform.dto.LiabilitiesOutstandingAccountDto;
import zw.co.zim.willplatform.model.LiabilitiesOutstandingAccount;

import java.util.function.Function;

@Service
public class LiabilitiesOutstandingAccountDtoMapper implements Function<LiabilitiesOutstandingAccount, LiabilitiesOutstandingAccountDto> {

    @Override
    public LiabilitiesOutstandingAccountDto apply(LiabilitiesOutstandingAccount liabilitiesOutstandingAccount) {
        return LiabilitiesOutstandingAccountDto.builder()
            .id(liabilitiesOutstandingAccount.getId())
            .nameOfAccount(liabilitiesOutstandingAccount.getNameOfAccount())
            .accountValue(liabilitiesOutstandingAccount.getAccountValue())
            .userId(liabilitiesOutstandingAccount.getUserId())
            .createdAt(liabilitiesOutstandingAccount.getCreatedAt())
            .updatedAt(liabilitiesOutstandingAccount.getUpdatedAt())
            .recordStatus(liabilitiesOutstandingAccount.getRecordStatus())
            .build();
    }
}
