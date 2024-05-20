package zw.co.zim.willplatform.service;

import org.springframework.data.domain.Page;
import zw.co.zim.willplatform.common.AppService;
import zw.co.zim.willplatform.model.Client;
import zw.co.zim.willplatform.model.LiabilitiesSubscribedWrittenPublication;

public interface LiabilitiesSubscribedWrittenPublicationService extends AppService<LiabilitiesSubscribedWrittenPublication> {
    Page<LiabilitiesSubscribedWrittenPublication> findAll(Integer pageNo, Integer pageSize);

    Page<LiabilitiesSubscribedWrittenPublication> findAllByUserId(Client clientId, Integer pageNo, Integer pageSize);

}
