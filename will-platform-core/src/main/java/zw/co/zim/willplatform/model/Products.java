package zw.co.zim.willplatform.model;

import jakarta.persistence.*;
import lombok.*;
import zw.co.zim.willplatform.enums.ProductNames;
import zw.co.zim.willplatform.enums.RecordStatus;

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
    @OneToOne
    private Currency currency;
    @Enumerated(EnumType.STRING)
    private RecordStatus recordStatus;
    @ManyToOne
    @JoinColumn(name="subscription_id")
    private Subscription subscription;
}
