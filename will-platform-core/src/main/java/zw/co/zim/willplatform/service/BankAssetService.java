package zw.co.zim.willplatform.service;

import zw.co.zim.willplatform.common.AppService;
import zw.co.zim.willplatform.model.BankAsset;

import java.util.Optional;

public interface BankAssetService extends AppService<BankAsset> {
    Optional<BankAsset> findBankByAccountNumber(String accountNumber);
}
