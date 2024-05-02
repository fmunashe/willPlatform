package zw.co.zim.willplatform.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import zw.co.zim.willplatform.dto.AssetTimeShareDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import zw.co.zim.willplatform.enums.RecordStatus;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class AssetTimeShare extends BaseEntity {
    private String description;
    private double timeshareValue;
    @ManyToOne
    private User userId;

    @Enumerated(EnumType.STRING)
    private RecordStatus recordStatus;

    public AssetTimeShare(AssetTimeShareDto assetTimeShareDto) {
        this.description = assetTimeShareDto.description();
        this.timeshareValue = assetTimeShareDto.value();
        this.userId = assetTimeShareDto.userId();
        this.recordStatus = getRecordStatus();
    }
}
