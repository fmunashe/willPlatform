package zw.co.zim.willplatform.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;
import zw.co.zim.willplatform.utils.enums.RecordStatus;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Currency extends BaseEntity {
    private String name;
    private String symbol;
    private String iso;
    private String conversionRate;
    @Enumerated(EnumType.STRING)
    private RecordStatus recordStatus;
}
