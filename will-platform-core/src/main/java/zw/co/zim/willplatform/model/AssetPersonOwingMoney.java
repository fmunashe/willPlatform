package zw.co.zim.willplatform.model;

import jakarta.persistence.*;
import zw.co.zim.willplatform.dto.AssetPersonOwingMoneyRecordDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import zw.co.zim.willplatform.utils.enums.RecordStatus;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class AssetPersonOwingMoney extends BaseEntity {
    private String personDetails;
    private Double amountOwed;
    private String contactNumber;
    @ManyToOne
    @JoinColumn(name = "user_id",nullable = false)
    private Client userId;

    @Enumerated(EnumType.STRING)
    private RecordStatus recordStatus;

    public AssetPersonOwingMoney(AssetPersonOwingMoneyRecordDto recordDto) {
        this.personDetails = recordDto.personDetails();
        this.amountOwed = recordDto.amountOwed();
        this.contactNumber = recordDto.contactNumber();
        this.userId = recordDto.userId();
        this.recordStatus = recordDto.recordStatus();
    }
}
