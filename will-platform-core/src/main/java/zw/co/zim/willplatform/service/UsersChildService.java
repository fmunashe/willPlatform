package zw.co.zim.willplatform.service;

import org.springframework.data.domain.Page;
import zw.co.zim.willplatform.common.AppService;
import zw.co.zim.willplatform.model.Client;
import zw.co.zim.willplatform.model.UsersChild;

public interface UsersChildService extends AppService<UsersChild> {
    Page<UsersChild> findAll(Integer pageNo, Integer pageSize);

    Page<UsersChild> findAllByUserId(Client userId, Integer pageNo, Integer pageSize);
}
