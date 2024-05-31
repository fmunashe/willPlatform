package zw.co.zim.willplatform.dto.mapper;

import org.springframework.stereotype.Service;
import zw.co.zim.willplatform.dto.LiabilitiesOutstandingLoanDto;
import zw.co.zim.willplatform.model.LiabilitiesOutstandingLoan;

import java.util.function.Function;

@Service
public class LiabilitiesOutStandingLoanDtoMapper implements Function<LiabilitiesOutstandingLoan, LiabilitiesOutstandingLoanDto> {
    @Override
    public LiabilitiesOutstandingLoanDto apply(LiabilitiesOutstandingLoan liabilitiesOutstandingLoan) {
        return LiabilitiesOutstandingLoanDto.builder()
            .id(liabilitiesOutstandingLoan.getId())
            .userId(liabilitiesOutstandingLoan.getUserId())
            .nameOfCreditor(liabilitiesOutstandingLoan.getNameOfCreditor())
            .amountOwed(liabilitiesOutstandingLoan.getAmountOwed())
            .recordStatus(liabilitiesOutstandingLoan.getRecordStatus())
            .createdAt(liabilitiesOutstandingLoan.getCreatedAt())
            .updatedAt(liabilitiesOutstandingLoan.getUpdatedAt())
            .build();
    }
}
