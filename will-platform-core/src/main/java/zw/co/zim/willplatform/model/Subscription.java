package zw.co.zim.willplatform.model;

import jakarta.persistence.*;
import lombok.*;
import zw.co.zim.willplatform.enums.BillingCycle;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Subscription extends BaseEntity {
    @OneToOne
    private Client userId;
    @OneToMany
    private List<Products> products;
    private Double totalDueNow;
    private Double recurringAmount;
    private LocalDate subscriptionDate;
    @Enumerated(EnumType.STRING)
    private BillingCycle billingCycle;
    private LocalDate billingDate;
    private LocalDate nextBillingDate;
}
