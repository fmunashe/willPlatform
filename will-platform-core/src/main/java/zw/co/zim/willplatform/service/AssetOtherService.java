package zw.co.zim.willplatform.service;

import org.springframework.data.domain.Page;
import zw.co.zim.willplatform.common.BaseService;
import zw.co.zim.willplatform.model.AssetOther;
import zw.co.zim.willplatform.model.Client;

public interface AssetOtherService extends BaseService<AssetOther> {
    Page<AssetOther> findAll(Integer pageNo, Integer pageSize);

    Page<AssetOther> findAllByUserId(Client clientId, Integer pageNo, Integer pageSize);
}
