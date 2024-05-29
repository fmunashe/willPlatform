package zw.co.zim.willplatform.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CaseAllocationDto {
    private Long id;
    private Long caseId;
    private String caseType;
    private LocalDateTime allocationTime;
    private String allocatedAgent;
    private String recordStatus;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;


}
