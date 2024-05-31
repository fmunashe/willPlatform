package zw.co.zim.willplatform.service;

import org.springframework.data.domain.Page;
import zw.co.zim.willplatform.common.BaseService;
import zw.co.zim.willplatform.model.AssetPolicy;
import zw.co.zim.willplatform.model.Client;

import java.util.Optional;

public interface AssetPolicyService extends BaseService<AssetPolicy> {
    Optional<AssetPolicy> findFirstByPolicyNumber(String policyNumber);

    Page<AssetPolicy> findAll(Integer pageNo, Integer pageSize);

    Page<AssetPolicy> findAllByUserId(Client clientId, Integer pageNo, Integer pageSize);
}
