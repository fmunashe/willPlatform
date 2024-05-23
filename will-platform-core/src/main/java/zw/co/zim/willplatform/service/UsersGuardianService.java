package zw.co.zim.willplatform.service;

import org.springframework.data.domain.Page;
import zw.co.zim.willplatform.common.AppService;
import zw.co.zim.willplatform.model.Client;
import zw.co.zim.willplatform.model.UsersGuardian;

public interface UsersGuardianService extends AppService<UsersGuardian> {
    Page<UsersGuardian> findAll(Integer pageNo, Integer pageSize);

    Page<UsersGuardian> findAllByUserId(Client userId, Integer pageNo, Integer pageSize);
}
