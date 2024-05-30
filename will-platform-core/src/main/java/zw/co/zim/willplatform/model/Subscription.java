package zw.co.zim.willplatform.model;

import jakarta.persistence.*;
import lombok.*;
import zw.co.zim.willplatform.utils.enums.BillingCycle;
import zw.co.zim.willplatform.utils.enums.RecordStatus;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Subscription extends BaseEntity {
    @OneToOne
    private Client userId;
    @ManyToMany(mappedBy = "subscriptions")
    private Set<Products> products = new HashSet<>();
    private Double totalDueNow;
    private Double recurringAmount;
    private LocalDate subscriptionDate;
    @Enumerated(EnumType.STRING)
    private BillingCycle billingCycle;
    private LocalDate billingDate;
    private LocalDate nextBillingDate;
    @Enumerated(EnumType.STRING)
    private RecordStatus recordStatus;
}
