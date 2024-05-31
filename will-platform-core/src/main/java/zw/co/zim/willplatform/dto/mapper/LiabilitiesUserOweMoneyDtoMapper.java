package zw.co.zim.willplatform.dto.mapper;

import org.springframework.stereotype.Service;
import zw.co.zim.willplatform.dto.LiabilitiesUserOwesMoneyDto;
import zw.co.zim.willplatform.model.LiabilitiesUserOwesMoney;

import java.util.function.Function;

@Service
public class LiabilitiesUserOweMoneyDtoMapper implements Function<LiabilitiesUserOwesMoney, LiabilitiesUserOwesMoneyDto> {
    @Override
    public LiabilitiesUserOwesMoneyDto apply(LiabilitiesUserOwesMoney liabilitiesUserOwesMoney) {
        return LiabilitiesUserOwesMoneyDto.builder()
            .id(liabilitiesUserOwesMoney.getId())
            .userId(liabilitiesUserOwesMoney.getUserId())
            .personDetails(liabilitiesUserOwesMoney.getPersonDetails())
            .personContactDetails(liabilitiesUserOwesMoney.getPersonContactDetails())
            .amountOwed(liabilitiesUserOwesMoney.getAmountOwed())
            .createdAt(liabilitiesUserOwesMoney.getCreatedAt())
            .updatedAt(liabilitiesUserOwesMoney.getUpdatedAt())
            .recordStatus(liabilitiesUserOwesMoney.getRecordStatus())
            .build();
    }
}
