package zw.co.zim.willplatform.service;

import org.springframework.data.domain.Page;
import zw.co.zim.willplatform.common.AppService;
import zw.co.zim.willplatform.model.Client;
import zw.co.zim.willplatform.model.LiabilitiesUserOwesMoney;

public interface LiabilitiesUserOweMoneyService extends AppService<LiabilitiesUserOwesMoney> {
    Page<LiabilitiesUserOwesMoney> findAll(Integer pageNo, Integer pageSize);

    Page<LiabilitiesUserOwesMoney> findAllByUserId(Client clientId, Integer pageNo, Integer pageSize);
}
