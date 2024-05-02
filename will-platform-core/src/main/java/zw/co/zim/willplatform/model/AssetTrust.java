package zw.co.zim.willplatform.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import zw.co.zim.willplatform.dto.AssetTrustDto;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class AssetTrust extends BaseEntity {
    private String nameOfTrust;
    private double trustValue;
    @ManyToOne
    @JoinColumn(name = "userId")
    @JsonBackReference
    private User userId;

    public AssetTrust(AssetTrustDto assetTrustDto) {
        this.nameOfTrust = assetTrustDto.nameOfTrust();
        this.trustValue = assetTrustDto.value();
        this.userId = assetTrustDto.userId();
    }
}
