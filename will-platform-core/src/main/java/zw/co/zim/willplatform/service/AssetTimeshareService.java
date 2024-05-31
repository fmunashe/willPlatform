package zw.co.zim.willplatform.service;

import org.springframework.data.domain.Page;
import zw.co.zim.willplatform.common.BaseService;
import zw.co.zim.willplatform.model.AssetTimeShare;
import zw.co.zim.willplatform.model.Client;

public interface AssetTimeshareService extends BaseService<AssetTimeShare> {
    Page<AssetTimeShare> findAll(Integer pageNo, Integer pageSize);

    Page<AssetTimeShare> findAllByUserId(Client clientId, Integer pageNo, Integer pageSize);
}
