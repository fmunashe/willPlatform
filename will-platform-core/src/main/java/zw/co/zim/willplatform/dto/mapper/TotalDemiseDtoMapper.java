package zw.co.zim.willplatform.dto.mapper;

import org.springframework.stereotype.Service;
import zw.co.zim.willplatform.dto.TotalDemiseDto;
import zw.co.zim.willplatform.model.TotalDemise;

import java.util.function.Function;

@Service
public class TotalDemiseDtoMapper implements Function<TotalDemise, TotalDemiseDto> {
    @Override
    public TotalDemiseDto apply(TotalDemise totalDemise) {
        return TotalDemiseDto.builder()
            .id(totalDemise.getId())
            .userId(totalDemise.getUserId())
            .beneficiaryDetails(totalDemise.getBeneficiaryDetails())
            .recordStatus(totalDemise.getRecordStatus())
            .createdAt(totalDemise.getCreatedAt())
            .updatedAt(totalDemise.getUpdatedAt())
            .build();
    }
}
