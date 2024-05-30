package zw.co.zim.willplatform.model;

import jakarta.persistence.*;
import zw.co.zim.willplatform.dto.AssetOffshoreRecordDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import zw.co.zim.willplatform.utils.enums.RecordStatus;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class AssetOffshore extends BaseEntity {
    private String description;
    private Double offshoreValue;
    @ManyToOne
    @JoinColumn(name = "user_id",nullable = false)
    private Client userId;
    @Enumerated(EnumType.STRING)
    private RecordStatus recordStatus;

    public AssetOffshore(AssetOffshoreRecordDto recordDto) {
        this.description = recordDto.description();
        this.offshoreValue = recordDto.value();
        this.userId = recordDto.userId();
        this.recordStatus = recordDto.recordStatus();
    }
}
