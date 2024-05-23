package zw.co.zim.willplatform.service;

import org.springframework.data.domain.Page;
import zw.co.zim.willplatform.common.AppService;
import zw.co.zim.willplatform.model.Client;
import zw.co.zim.willplatform.model.TotalDemise;

public interface TotalDemiseService extends AppService<TotalDemise> {

    Page<TotalDemise> findAll(Integer pageNo, Integer pageSize);

    Page<TotalDemise> findAllByUserId(Client userId, Integer pageNo, Integer pageSize);

}
