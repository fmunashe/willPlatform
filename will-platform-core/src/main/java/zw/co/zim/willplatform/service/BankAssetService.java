package zw.co.zim.willplatform.service;

import org.springframework.data.domain.Page;
import zw.co.zim.willplatform.common.AppService;
import zw.co.zim.willplatform.model.BankAsset;
import zw.co.zim.willplatform.model.Client;

import java.util.Optional;

public interface BankAssetService extends AppService<BankAsset> {
    Optional<BankAsset> findBankByAccountNumber(String accountNumber);

    Page<BankAsset> findAll(Integer pageNo, Integer pageSize);

    Page<BankAsset> findAllByUserId(Client clientId, Integer pageNo, Integer pageSize);

}
