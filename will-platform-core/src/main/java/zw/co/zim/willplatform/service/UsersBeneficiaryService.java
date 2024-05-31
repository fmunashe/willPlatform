package zw.co.zim.willplatform.service;

import org.springframework.data.domain.Page;
import zw.co.zim.willplatform.common.BaseService;
import zw.co.zim.willplatform.model.Client;
import zw.co.zim.willplatform.model.UsersBeneficiary;

import java.util.Optional;

public interface UsersBeneficiaryService extends BaseService<UsersBeneficiary> {
    Page<UsersBeneficiary> findAll(Integer pageNo, Integer pageSize);

    Page<UsersBeneficiary> findAllByUserId(Client userId, Integer pageNo, Integer pageSize);

    Optional<UsersBeneficiary> findFirstByClientIdAndEmail(Client clientId, String email);

    Optional<UsersBeneficiary> findFirstByClientIdAndIDNumber(Client clientId, String idNumber);
}
