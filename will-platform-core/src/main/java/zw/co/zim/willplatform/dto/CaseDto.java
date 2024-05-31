package zw.co.zim.willplatform.dto;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class CaseDto extends BaseDto {
    private String caseNumber;
    private String subject;
    private String description;
    private String priority;
    private String caseType;
    private Long clientId;
    private Long assignedAgent;
}
