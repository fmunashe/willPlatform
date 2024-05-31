package zw.co.zim.willplatform.dto.mapper;

import org.springframework.stereotype.Service;
import zw.co.zim.willplatform.dto.TransferFeesDto;
import zw.co.zim.willplatform.model.TransferFees;

import java.util.function.Function;

@Service
public class TransferFeesDtoMapper implements Function<TransferFees, TransferFeesDto> {
    @Override
    public TransferFeesDto apply(TransferFees transferFees) {
        return TransferFeesDto.builder()
            .id(transferFees.getId())
            .transferFee(transferFees.getTransferFee())
            .transferValue(transferFees.getTransferValue())
            .vat(transferFees.getVat())
            .levy(transferFees.getLevy())
            .recordStatus(transferFees.getRecordStatus())
            .createdAt(transferFees.getCreatedAt())
            .updatedAt(transferFees.getUpdatedAt())
            .build();
    }
}
