package zw.co.zim.willplatform.service;

import org.springframework.data.domain.Page;
import zw.co.zim.willplatform.common.AppService;
import zw.co.zim.willplatform.model.AssetInvestment;
import zw.co.zim.willplatform.model.Client;

public interface AssetInvestmentService extends AppService<AssetInvestment> {
    Page<AssetInvestment> findAll(Integer pageNo, Integer pageSize);

    Page<AssetInvestment> findAllByInvestmentType(String investmentType, Integer pageNo, Integer pageSize);

    Page<AssetInvestment> findAllByUserId(Client clientId, Integer pageNo, Integer pageSize);
}
