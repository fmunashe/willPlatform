package zw.co.zim.willplatform.service;

import org.springframework.data.domain.Page;
import zw.co.zim.willplatform.common.AppService;
import zw.co.zim.willplatform.model.AssetOffshore;
import zw.co.zim.willplatform.model.Client;

public interface AssetOffshoreService extends AppService<AssetOffshore> {
    Page<AssetOffshore> findAll(Integer pageNo, Integer pageSize);

    Page<AssetOffshore> findAllByUserId(Client clientId, Integer pageNo, Integer pageSize);
}
