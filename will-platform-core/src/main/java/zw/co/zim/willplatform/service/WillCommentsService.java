package zw.co.zim.willplatform.service;

import org.springframework.data.domain.Page;
import zw.co.zim.willplatform.common.BaseService;
import zw.co.zim.willplatform.model.Client;
import zw.co.zim.willplatform.model.WillComments;

public interface WillCommentsService extends BaseService<WillComments> {
    Page<WillComments> findAll(Integer pageNo, Integer pageSize);

    Page<WillComments> findAllByUserId(Client userId, Integer pageNo, Integer pageSize);
}
