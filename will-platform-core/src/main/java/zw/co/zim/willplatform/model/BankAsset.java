package zw.co.zim.willplatform.model;

import zw.co.zim.willplatform.dto.BankAssetRecordDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import zw.co.zim.willplatform.enums.RecordStatus;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class BankAsset extends BaseEntity{
    private String bankName;
    private String accountNumber;
    private Double balance;
    @ManyToOne
    private Client userId;
    @Enumerated(EnumType.STRING)
    private RecordStatus recordStatus;

    public BankAsset(BankAssetRecordDto bankAssetRecordDto) {
        this.bankName = bankAssetRecordDto.bankName();
        this.accountNumber = bankAssetRecordDto.accountNumber();
        this.balance=bankAssetRecordDto.balance();
        this.userId = bankAssetRecordDto.user();
        this.recordStatus = getRecordStatus();
    }
}
