package zw.co.zim.willplatform.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import zw.co.zim.willplatform.dto.BankAssetRecordDto;
import zw.co.zim.willplatform.utils.enums.RecordStatus;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class BankAsset extends BaseEntity {
    private String bankName;
    private String accountNumber;
    private Double balance;
    @ManyToOne
    @JoinColumn(name = "currency_id", nullable = false)
    private Currency currency;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private Client userId;
    @Enumerated(EnumType.STRING)
    private RecordStatus recordStatus;

    public BankAsset(BankAssetRecordDto bankAssetRecordDto) {
        this.bankName = bankAssetRecordDto.bankName();
        this.accountNumber = bankAssetRecordDto.accountNumber();
        this.balance = bankAssetRecordDto.balance();
        this.userId = bankAssetRecordDto.user();
        this.currency = bankAssetRecordDto.currency();
        this.recordStatus = bankAssetRecordDto.recordStatus();
    }
}
