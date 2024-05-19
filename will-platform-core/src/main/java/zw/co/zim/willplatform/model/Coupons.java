package zw.co.zim.willplatform.model;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import lombok.*;
import zw.co.zim.willplatform.enums.ProductNames;
import zw.co.zim.willplatform.enums.RecordStatus;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Coupons extends BaseEntity {
    private String code;
    private List<ProductNames> packages;
    private LocalDate expiryDate;
    private Double discount;
    private boolean applied;
    @OneToOne
    private Client userId;
    private RecordStatus recordStatus;
}
