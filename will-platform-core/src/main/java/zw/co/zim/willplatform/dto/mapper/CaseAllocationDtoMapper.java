package zw.co.zim.willplatform.dto.mapper;

import org.springframework.stereotype.Service;
import zw.co.zim.willplatform.dto.CaseAllocationDto;
import zw.co.zim.willplatform.model.CaseAllocation;

import java.util.function.Function;

@Service
public class CaseAllocationDtoMapper implements Function<CaseAllocation, CaseAllocationDto> {
    @Override
    public CaseAllocationDto apply(CaseAllocation caseAllocation) {
        return CaseAllocationDto.builder()
            .id(caseAllocation.getId())
            .caseId(caseAllocation.getCaseId().getId())
            .caseType(caseAllocation.getCaseType().getType())
            .allocatedAgent(caseAllocation.getAllocatedAgent())
            .allocationTime(caseAllocation.getAllocationTime())
            .recordStatus(caseAllocation.getRecordStatus().getStatus())
            .build();
    }
}
