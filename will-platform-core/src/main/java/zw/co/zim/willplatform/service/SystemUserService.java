package zw.co.zim.willplatform.service;

import org.springframework.data.domain.Page;
import zw.co.zim.willplatform.common.BaseService;
import zw.co.zim.willplatform.utils.enums.RoleEnum;
import zw.co.zim.willplatform.model.SystemUser;

import java.util.Optional;

public interface SystemUserService extends BaseService<SystemUser> {
    Page<SystemUser> findAll(Integer pageNo, Integer pageSize);

    Page<SystemUser> findAllByRole(RoleEnum role, Integer pageNo, Integer pageSize);

    Optional<SystemUser> findFirstByEmail(String email);
}
