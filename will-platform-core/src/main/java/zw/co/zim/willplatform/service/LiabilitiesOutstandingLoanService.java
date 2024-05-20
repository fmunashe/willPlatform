package zw.co.zim.willplatform.service;

import org.springframework.data.domain.Page;
import zw.co.zim.willplatform.common.AppService;
import zw.co.zim.willplatform.model.Client;
import zw.co.zim.willplatform.model.LiabilitiesOutstandingLoan;

public interface LiabilitiesOutstandingLoanService extends AppService<LiabilitiesOutstandingLoan> {
    Page<LiabilitiesOutstandingLoan> findAll(Integer pageNo, Integer pageSize);

    Page<LiabilitiesOutstandingLoan> findAllByUserId(Client clientId, Integer pageNo, Integer pageSize);

}
