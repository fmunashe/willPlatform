package zw.co.zim.willplatform.model;

import jakarta.persistence.*;
import zw.co.zim.willplatform.dto.AssetTrustDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import zw.co.zim.willplatform.enums.RecordStatus;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class AssetTrust extends BaseEntity {
    private String nameOfTrust;
    private double trustValue;
    @ManyToOne
    private Client userId;

    @Enumerated(EnumType.STRING)
    private RecordStatus recordStatus;

    public AssetTrust(AssetTrustDto assetTrustDto) {
        this.nameOfTrust = assetTrustDto.nameOfTrust();
        this.trustValue = assetTrustDto.value();
        this.userId = assetTrustDto.userId();
        this.recordStatus = getRecordStatus();
    }
}
