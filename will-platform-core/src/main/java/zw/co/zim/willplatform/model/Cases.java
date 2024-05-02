package zw.co.zim.willplatform.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import lombok.*;
import zw.co.zim.willplatform.enums.CasePriority;
import zw.co.zim.willplatform.enums.CaseType;
import zw.co.zim.willplatform.enums.RecordStatus;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Cases extends BaseEntity {
    private String caseNumber;
    private String subject;
    private String description;
    @Enumerated(EnumType.STRING)
    private CasePriority priority;
    @Enumerated(EnumType.STRING)
    private RecordStatus status;
    @Enumerated(EnumType.STRING)
    private CaseType caseType;
    @ManyToOne
    private User userId;
    @ManyToOne
    private SystemUser assignedAgent;
}
