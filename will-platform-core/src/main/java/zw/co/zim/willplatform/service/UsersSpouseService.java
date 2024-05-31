package zw.co.zim.willplatform.service;

import org.springframework.data.domain.Page;
import zw.co.zim.willplatform.common.BaseService;
import zw.co.zim.willplatform.model.Client;
import zw.co.zim.willplatform.model.UsersSpouse;

public interface UsersSpouseService extends BaseService<UsersSpouse> {
    Page<UsersSpouse> findAll(Integer pageNo, Integer pageSize);

    Page<UsersSpouse> findAllByUserId(Client userId, Integer pageNo, Integer pageSize);
}
