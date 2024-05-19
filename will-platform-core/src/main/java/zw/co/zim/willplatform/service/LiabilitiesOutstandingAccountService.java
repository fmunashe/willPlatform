package zw.co.zim.willplatform.service;

import org.springframework.data.domain.Page;
import zw.co.zim.willplatform.common.AppService;
import zw.co.zim.willplatform.model.Client;
import zw.co.zim.willplatform.model.LiabilitiesOutstandingAccount;

public interface LiabilitiesOutstandingAccountService extends AppService<LiabilitiesOutstandingAccount> {
    Page<LiabilitiesOutstandingAccount> findAll(Integer pageNo, Integer pageSize);

    Page<LiabilitiesOutstandingAccount> findAllByUserId(Client clientId, Integer pageNo, Integer pageSize);
}
