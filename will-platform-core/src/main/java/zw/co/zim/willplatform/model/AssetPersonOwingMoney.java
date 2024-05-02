package zw.co.zim.willplatform.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import zw.co.zim.willplatform.dto.AssetPersonOwingMoneyRecordDto;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class AssetPersonOwingMoney extends BaseEntity {
    private String personDetails;
    private Double amountOwed;
    private String contactNumber;
    @ManyToOne
    @JoinColumn(name = "userId")
    @JsonBackReference
    private User userId;

    public AssetPersonOwingMoney(AssetPersonOwingMoneyRecordDto recordDto) {
        this.personDetails = recordDto.personDetails();
        this.amountOwed = recordDto.amountOwed();
        this.contactNumber = recordDto.contactNumber();
        this.userId = recordDto.userId();
    }
}
