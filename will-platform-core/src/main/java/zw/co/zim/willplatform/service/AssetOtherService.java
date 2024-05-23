package zw.co.zim.willplatform.service;

import org.springframework.data.domain.Page;
import zw.co.zim.willplatform.common.AppService;
import zw.co.zim.willplatform.model.AssetOther;
import zw.co.zim.willplatform.model.Client;

public interface AssetOtherService extends AppService<AssetOther> {
    Page<AssetOther> findAll(Integer pageNo, Integer pageSize);

    Page<AssetOther> findAllByUserId(Client clientId, Integer pageNo, Integer pageSize);
}
