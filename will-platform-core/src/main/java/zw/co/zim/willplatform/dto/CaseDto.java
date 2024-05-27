package zw.co.zim.willplatform.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CaseDto {
    private Long id;
    private String caseNumber;
    private String subject;
    private String description;
    private String priority;
    private String caseType;
    private Long clientId;
    private Long assignedAgent;
    private String recordStatus;
}
