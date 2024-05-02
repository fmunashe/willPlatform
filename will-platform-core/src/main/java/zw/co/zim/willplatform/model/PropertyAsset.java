package zw.co.zim.willplatform.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import zw.co.zim.willplatform.dto.PropertyAssetRecordDto;
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
public class PropertyAsset extends BaseEntity {
    private String propertyName;
    private String description;
    private Address address;
    private Double propertyValue;
    private Boolean haveABond;
    private String bondIsWith;
    private Boolean inYourName;
    private Boolean isFarm;
    private String personPropertyIsUnder;
    private Boolean youHoldDeed;
    private String personWhoHoldsDeed;
    private String additionalInformation;
    @ManyToOne
    @JoinColumn(name = "userId")
    @JsonBackReference
    private User userId;

    public PropertyAsset(PropertyAssetRecordDto propertyAssetRecordDto) {
        this.propertyName = propertyAssetRecordDto.propertyName();
        this.description = propertyAssetRecordDto.description();
        this.address = propertyAssetRecordDto.address();
        this.propertyValue = propertyAssetRecordDto.value();
        this.haveABond = propertyAssetRecordDto.haveABond();
        this.bondIsWith = propertyAssetRecordDto.bondIsWith();
        this.inYourName = propertyAssetRecordDto.inYourName();
        this.isFarm = propertyAssetRecordDto.isFarm();
        this.personPropertyIsUnder = propertyAssetRecordDto.personPropertyIsUnder();
        this.youHoldDeed = propertyAssetRecordDto.youHoldDeed();
        this.personWhoHoldsDeed = propertyAssetRecordDto.personWhoHoldsDeed();
        this.additionalInformation = propertyAssetRecordDto.additionalInformation();
        this.userId = propertyAssetRecordDto.user();
    }
}
