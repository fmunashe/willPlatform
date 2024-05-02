package zw.co.zim.willplatform.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import zw.co.zim.willplatform.dto.AssetOtherRecordDto;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class AssetOther extends BaseEntity {
    private String description;
    private Double assetValue;
    @ManyToOne
    @JoinColumn(name = "userId")
    @JsonBackReference
    private User userId;

    public AssetOther(AssetOtherRecordDto recordDto) {
        this.description = recordDto.description();
        this.assetValue = recordDto.value();
        this.userId = recordDto.userId();
    }
}
