package zw.co.zim.willplatform.service;

import org.springframework.data.domain.Page;
import zw.co.zim.willplatform.common.AppService;
import zw.co.zim.willplatform.model.Client;
import zw.co.zim.willplatform.model.UsersBeneficiary;

public interface UsersBeneficiaryService extends AppService<UsersBeneficiary> {
    Page<UsersBeneficiary> findAll(Integer pageNo, Integer pageSize);

    Page<UsersBeneficiary> findAllByUserId(Client userId, Integer pageNo, Integer pageSize);
}
