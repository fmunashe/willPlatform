package zw.co.zim.willplatform.service;

import org.springframework.data.domain.Page;
import zw.co.zim.willplatform.common.AppService;
import zw.co.zim.willplatform.model.AssetTrust;
import zw.co.zim.willplatform.model.Client;

public interface AssetTrustService extends AppService<AssetTrust> {
    Page<AssetTrust> findAll(Integer pageNo, Integer pageSize);

    Page<AssetTrust> findAllByUserId(Client clientId, Integer pageNo, Integer pageSize);
}
