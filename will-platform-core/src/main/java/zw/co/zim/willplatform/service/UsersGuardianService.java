package zw.co.zim.willplatform.service;

import org.springframework.data.domain.Page;
import zw.co.zim.willplatform.common.BaseService;
import zw.co.zim.willplatform.model.Client;
import zw.co.zim.willplatform.model.UsersGuardian;

public interface UsersGuardianService extends BaseService<UsersGuardian> {
    Page<UsersGuardian> findAll(Integer pageNo, Integer pageSize);

    Page<UsersGuardian> findAllByUserId(Client userId, Integer pageNo, Integer pageSize);
}
