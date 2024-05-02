package zw.co.zim.willplatform.model;


import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class CaseUpdate extends BaseEntity{
    @ManyToOne
    private Cases caseId;
    private String name;
    private String description;
    private String createdBy;
}
