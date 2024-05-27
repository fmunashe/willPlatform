package zw.co.zim.willplatform.model;

import jakarta.persistence.*;
import lombok.*;
import zw.co.zim.willplatform.enums.CaseType;
import zw.co.zim.willplatform.enums.RecordStatus;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class CaseAllocation extends BaseEntity {
    @OneToOne
    private Cases caseId;

    @Enumerated(EnumType.STRING)
    private CaseType caseType;

    private LocalDateTime allocationTime;

    private String allocatedAgent;

    @Enumerated(EnumType.STRING)
    private RecordStatus recordStatus;
}
