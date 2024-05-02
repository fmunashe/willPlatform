package zw.co.zim.willplatform.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import zw.co.zim.willplatform.dto.AssetTimeShareDto;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class AssetTimeShare extends BaseEntity {
    private String description;
    private double timeshareValue;
    @ManyToOne
    @JoinColumn(name = "userId")
    @JsonBackReference
    private User userId;

    public AssetTimeShare(AssetTimeShareDto assetTimeShareDto) {
        this.description = assetTimeShareDto.description();
        this.timeshareValue = assetTimeShareDto.value();
        this.userId = assetTimeShareDto.userId();
    }
}
