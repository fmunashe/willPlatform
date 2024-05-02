package zw.co.zim.willplatform.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Builder;
import zw.co.zim.willplatform.dto.BankAssetRecordDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class BankAsset extends BaseEntity{
    private String bankName;
    private String accountNumber;
    private Double balance;
    @ManyToOne
    @JoinColumn(name = "userId")
    @JsonBackReference
    private User userId;

    public BankAsset(BankAssetRecordDto bankAssetRecordDto) {
        this.bankName = bankAssetRecordDto.bankName();
        this.accountNumber = bankAssetRecordDto.accountNumber();
        this.balance=bankAssetRecordDto.balance();
        this.userId = bankAssetRecordDto.user();
    }
}
