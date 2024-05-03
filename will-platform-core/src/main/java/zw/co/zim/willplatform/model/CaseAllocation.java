package zw.co.zim.willplatform.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToOne;
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

    @Enumerated(EnumType.STRING)
    private RecordStatus recordStatus;
}
