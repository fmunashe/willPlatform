package zw.co.zim.willplatform.service;

import org.springframework.data.domain.Page;
import zw.co.zim.willplatform.common.BaseService;
import zw.co.zim.willplatform.model.Client;
import zw.co.zim.willplatform.model.Subscription;

import java.util.Optional;

public interface SubscriptionService extends BaseService<Subscription> {
    Page<Subscription> findAll(Integer pageNo, Integer pageSize);

    Optional<Subscription> findFirstByUserId(Client userId);
}
