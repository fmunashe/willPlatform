package zw.co.zim.willplatform.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import zw.co.zim.willplatform.dto.AssetPolicyRecordDto;
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
public class AssetPolicy extends BaseEntity {
    private String policyType;
    private String policyNumber;
    private String company;
    private Double policyValue;
    @ManyToOne
    @JoinColumn(name = "userId")
    @JsonBackReference
    private User userId;

    public AssetPolicy(AssetPolicyRecordDto policyRecordDto) {
        this.policyType = policyRecordDto.policyType();
        this.policyNumber = policyRecordDto.policyNumber();
        this.company = policyRecordDto.company();
        this.policyValue = policyRecordDto.value();
        this.userId = policyRecordDto.userId();
    }
}
