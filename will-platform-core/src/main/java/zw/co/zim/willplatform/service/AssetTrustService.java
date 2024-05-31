package zw.co.zim.willplatform.service;

import org.springframework.data.domain.Page;
import zw.co.zim.willplatform.common.BaseService;
import zw.co.zim.willplatform.model.AssetTrust;
import zw.co.zim.willplatform.model.Client;

public interface AssetTrustService extends BaseService<AssetTrust> {
    Page<AssetTrust> findAll(Integer pageNo, Integer pageSize);

    Page<AssetTrust> findAllByUserId(Client clientId, Integer pageNo, Integer pageSize);
}
