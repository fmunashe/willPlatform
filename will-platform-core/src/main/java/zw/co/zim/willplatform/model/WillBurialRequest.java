package zw.co.zim.willplatform.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToOne;
import lombok.*;
import zw.co.zim.willplatform.utils.enums.BurialType;
import zw.co.zim.willplatform.utils.enums.RecordStatus;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class WillBurialRequest extends BaseEntity {
    @Enumerated(EnumType.STRING)
    private BurialType burialType;
    private String burialInformation;
    private Boolean livingWill;
    @OneToOne
    private Client userId;
    @Enumerated(EnumType.STRING)
    private RecordStatus recordStatus;
}
