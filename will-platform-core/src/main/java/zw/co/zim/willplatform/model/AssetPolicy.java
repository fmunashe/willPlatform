package zw.co.zim.willplatform.model;

import jakarta.persistence.*;
import zw.co.zim.willplatform.dto.AssetPolicyRecordDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import zw.co.zim.willplatform.utils.enums.RecordStatus;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class AssetPolicy extends BaseEntity {
    private String policyType;
    private String policyNumber;
    private String company;
    private Double policyValue;
    @ManyToOne
    @JoinColumn(name = "user_id",nullable = false)
    private Client userId;

    @Enumerated(EnumType.STRING)
    private RecordStatus recordStatus;

    public AssetPolicy(AssetPolicyRecordDto policyRecordDto) {
        this.policyType = policyRecordDto.policyType();
        this.policyNumber = policyRecordDto.policyNumber();
        this.company = policyRecordDto.company();
        this.policyValue = policyRecordDto.value();
        this.userId = policyRecordDto.userId();
        this.recordStatus = getRecordStatus();
    }
}
