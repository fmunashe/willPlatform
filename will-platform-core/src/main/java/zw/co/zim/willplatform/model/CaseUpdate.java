package zw.co.zim.willplatform.model;


import jakarta.persistence.*;
import lombok.*;
import zw.co.zim.willplatform.utils.enums.RecordStatus;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class CaseUpdate extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "case_id",nullable = false)
    private Cases caseId;
    private String name;
    private String description;
    private String createdBy;
    @Enumerated(EnumType.STRING)
    private RecordStatus status;
}
