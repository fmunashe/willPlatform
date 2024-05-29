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
public class TransferFees extends BaseEntity {
    private Double transferValue;
    private Double transferFee;
    private Double vat;
    private Double levy;
    @Enumerated(EnumType.STRING)
    private RecordStatus recordStatus;
}
