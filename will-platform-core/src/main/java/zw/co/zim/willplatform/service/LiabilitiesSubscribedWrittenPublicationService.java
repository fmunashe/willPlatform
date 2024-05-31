package zw.co.zim.willplatform.service;

import org.springframework.data.domain.Page;
import zw.co.zim.willplatform.common.BaseService;
import zw.co.zim.willplatform.model.Client;
import zw.co.zim.willplatform.model.LiabilitiesSubscribedWrittenPublication;

public interface LiabilitiesSubscribedWrittenPublicationService extends BaseService<LiabilitiesSubscribedWrittenPublication> {
    Page<LiabilitiesSubscribedWrittenPublication> findAll(Integer pageNo, Integer pageSize);

    Page<LiabilitiesSubscribedWrittenPublication> findAllByUserId(Client clientId, Integer pageNo, Integer pageSize);

}
