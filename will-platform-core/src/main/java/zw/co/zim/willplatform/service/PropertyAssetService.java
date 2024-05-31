package zw.co.zim.willplatform.service;

import org.springframework.data.domain.Page;
import zw.co.zim.willplatform.common.BaseService;
import zw.co.zim.willplatform.model.Client;
import zw.co.zim.willplatform.model.PropertyAsset;

public interface PropertyAssetService extends BaseService<PropertyAsset> {
    Page<PropertyAsset> findAll(Integer pageNo, Integer pageSize);

    Page<PropertyAsset> findAllByUserId(Client clientId, Integer pageNo, Integer pageSize);
}
