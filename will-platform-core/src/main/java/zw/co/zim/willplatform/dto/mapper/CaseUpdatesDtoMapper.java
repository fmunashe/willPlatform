package zw.co.zim.willplatform.dto.mapper;

import org.springframework.stereotype.Service;
import zw.co.zim.willplatform.dto.CaseUpdatesDto;
import zw.co.zim.willplatform.model.CaseUpdate;

import java.util.function.Function;

@Service
public class CaseUpdatesDtoMapper implements Function<CaseUpdate, CaseUpdatesDto> {
    @Override
    public CaseUpdatesDto apply(CaseUpdate caseUpdate) {
        return CaseUpdatesDto.builder()
            .id(caseUpdate.getId())
            .caseId(caseUpdate.getCaseId().getId())
            .name(caseUpdate.getName())
            .description(caseUpdate.getDescription())
            .createdBy(caseUpdate.getCreatedBy())
            .status(caseUpdate.getStatus().getStatus())
            .createdAt(caseUpdate.getCreatedAt())
            .updatedAt(caseUpdate.getUpdatedAt())
            .build();
    }
}
