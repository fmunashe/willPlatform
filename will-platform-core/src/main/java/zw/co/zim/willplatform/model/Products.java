package zw.co.zim.willplatform.model;

import jakarta.persistence.*;
import lombok.*;
import zw.co.zim.willplatform.utils.enums.ProductNames;
import zw.co.zim.willplatform.utils.enums.RecordStatus;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Products extends BaseEntity {
    @Enumerated(EnumType.STRING)
    private ProductNames name;
    private String description;
    private Double price;
    @ManyToOne
    private Currency currency;
    @Enumerated(EnumType.STRING)
    private RecordStatus recordStatus;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
        name = "product_subscription",
        joinColumns = @JoinColumn(name = "product_id"),
        inverseJoinColumns = @JoinColumn(name = "subscription_id")
    )
    private Set<Subscription> subscriptions = new HashSet<>();
}
