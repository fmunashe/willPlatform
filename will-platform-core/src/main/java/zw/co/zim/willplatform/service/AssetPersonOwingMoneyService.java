package zw.co.zim.willplatform.service;

import org.springframework.data.domain.Page;
import zw.co.zim.willplatform.common.AppService;
import zw.co.zim.willplatform.model.AssetPersonOwingMoney;
import zw.co.zim.willplatform.model.Client;

public interface AssetPersonOwingMoneyService extends AppService<AssetPersonOwingMoney> {
    Page<AssetPersonOwingMoney> findAll(Integer pageNo, Integer pageSize);

    Page<AssetPersonOwingMoney> findAllByUserId(Client clientId, Integer pageNo, Integer pageSize);
}
