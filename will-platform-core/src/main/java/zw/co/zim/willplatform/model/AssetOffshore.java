package zw.co.zim.willplatform.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import zw.co.zim.willplatform.dto.AssetOffshoreRecordDto;
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
public class AssetOffshore extends BaseEntity {
    private String description;
    private Double offshoreValue;
    @ManyToOne
    @JoinColumn(name = "userId")
    @JsonBackReference
    private User userId;

    public AssetOffshore(AssetOffshoreRecordDto recordDto) {
        this.description = recordDto.description();
        this.offshoreValue = recordDto.value();
        this.userId = recordDto.userId();
    }
}
