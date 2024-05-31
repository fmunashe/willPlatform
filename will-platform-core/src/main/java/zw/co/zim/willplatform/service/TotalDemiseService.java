package zw.co.zim.willplatform.service;

import org.springframework.data.domain.Page;
import zw.co.zim.willplatform.common.BaseService;
import zw.co.zim.willplatform.model.Client;
import zw.co.zim.willplatform.model.TotalDemise;

public interface TotalDemiseService extends BaseService<TotalDemise> {

    Page<TotalDemise> findAll(Integer pageNo, Integer pageSize);

    Page<TotalDemise> findAllByUserId(Client userId, Integer pageNo, Integer pageSize);

}
