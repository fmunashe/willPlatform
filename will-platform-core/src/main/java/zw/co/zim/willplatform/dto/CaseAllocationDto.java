package zw.co.zim.willplatform.dto;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class CaseAllocationDto extends BaseDto {
    private Long caseId;
    private String caseType;
    private LocalDateTime allocationTime;
    private String allocatedAgent;
}
