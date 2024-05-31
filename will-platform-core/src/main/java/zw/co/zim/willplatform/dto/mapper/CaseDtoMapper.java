package zw.co.zim.willplatform.dto.mapper;

import org.springframework.stereotype.Service;
import zw.co.zim.willplatform.dto.CaseDto;
import zw.co.zim.willplatform.model.Cases;

import java.util.function.Function;

@Service
public class CaseDtoMapper implements Function<Cases, CaseDto> {
    @Override
    public CaseDto apply(Cases cases) {
        return CaseDto.builder()
            .id(cases.getId())
            .caseType(cases.getCaseType().getType())
            .caseNumber(cases.getCaseNumber())
            .description(cases.getDescription())
            .subject(cases.getSubject())
            .priority(cases.getPriority().getPriority())
            .clientId(cases.getUserId().getId())
            .assignedAgent(cases.getAssignedAgent().getId())
            .recordStatus(cases.getStatus())
            .createdAt(cases.getCreatedAt())
            .updatedAt(cases.getUpdatedAt())
            .build();
    }
}
