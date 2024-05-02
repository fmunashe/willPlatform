package zw.co.zim.willplatform.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import zw.co.zim.willplatform.dto.AssetInvestmentRecordDto;
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
public class AssetInvestment extends BaseEntity {
    private String investmentType;
    private Double investmentValue;
    private String company;
    @ManyToOne
    @JoinColumn(name = "userId")
    @JsonBackReference
    private User userId;

    public AssetInvestment(AssetInvestmentRecordDto recordDto) {
        this.investmentType = recordDto.investmentType();
        this.investmentValue = recordDto.value();
        this.company = recordDto.company();
        this.userId = recordDto.userId();
    }
}
