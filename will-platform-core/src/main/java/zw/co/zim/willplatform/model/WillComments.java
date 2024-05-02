package zw.co.zim.willplatform.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import lombok.*;
import zw.co.zim.willplatform.enums.RecordStatus;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class WillComments extends BaseEntity {
    private String comments;
    @ManyToOne
    private Client userId;
    @Enumerated(EnumType.STRING)
    private RecordStatus recordStatus;
}
