package zw.co.zim.willplatform.service;

import org.springframework.data.domain.Page;
import zw.co.zim.willplatform.common.BaseService;
import zw.co.zim.willplatform.model.Client;
import zw.co.zim.willplatform.model.WillDocuments;

public interface WillDocumentsService extends BaseService<WillDocuments> {
    Page<WillDocuments> findAll(Integer pageNo, Integer pageSize);

    Page<WillDocuments> findAllByUserId(Client userId, Integer pageNo, Integer pageSize);
}
