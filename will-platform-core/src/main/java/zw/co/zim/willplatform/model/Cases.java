package zw.co.zim.willplatform.model;

import jakarta.persistence.*;
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
    @JoinColumn(name = "user_id",nullable = false)
    private Client userId;
    @ManyToOne
    @JoinColumn(name = "assigned_agent_id",nullable = false)
    private SystemUser assignedAgent;
}
