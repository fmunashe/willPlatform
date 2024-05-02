package zw.co.zim.willplatform.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import zw.co.zim.willplatform.dto.AssetInvestmentRecordDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import zw.co.zim.willplatform.enums.RecordStatus;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class AssetInvestment extends BaseEntity {
    private String investmentType;
    private Double investmentValue;
    private String company;
    @ManyToOne
    private User userId;

    @Enumerated(EnumType.STRING)
    private RecordStatus recordStatus;

    public AssetInvestment(AssetInvestmentRecordDto recordDto) {
        this.investmentType = recordDto.investmentType();
        this.investmentValue = recordDto.value();
        this.company = recordDto.company();
        this.userId = recordDto.userId();
        this.recordStatus = getRecordStatus();
    }
}
