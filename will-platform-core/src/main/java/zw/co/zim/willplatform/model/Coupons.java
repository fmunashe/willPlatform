package zw.co.zim.willplatform.model;

import jakarta.persistence.*;
import lombok.*;
import zw.co.zim.willplatform.utils.enums.RecordStatus;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Coupons extends BaseEntity {
    private String code;
    @ManyToOne
    @JoinColumn(name = "productId", nullable = false)
    private Products productId;
    private LocalDate expiryDate;
    private Double discount;
    private boolean applied;
    @OneToOne
    private Client userId;
    @Enumerated(EnumType.STRING)
    private RecordStatus recordStatus;
}
