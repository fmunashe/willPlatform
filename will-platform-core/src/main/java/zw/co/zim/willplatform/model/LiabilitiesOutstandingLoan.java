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
public class LiabilitiesOutstandingLoan extends BaseEntity {
    private String nameOfCreditor;
    private Double amountOwed;
    @ManyToOne
    @JoinColumn(name = "user_id",nullable = false)
    private Client userId;
    @Enumerated(EnumType.STRING)
    private RecordStatus recordStatus;
}
