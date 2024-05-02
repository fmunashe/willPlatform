package zw.co.zim.willplatform.model;

import jakarta.persistence.*;
import zw.co.zim.willplatform.dto.AssetOtherRecordDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import zw.co.zim.willplatform.enums.RecordStatus;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class AssetOther extends BaseEntity {
    private String description;
    private Double assetValue;
    @ManyToOne
    private Client userId;
    @Enumerated(EnumType.STRING)
    private RecordStatus recordStatus;

    public AssetOther(AssetOtherRecordDto recordDto) {
        this.description = recordDto.description();
        this.assetValue = recordDto.value();
        this.userId = recordDto.userId();
        this.recordStatus = getRecordStatus();
    }
}
