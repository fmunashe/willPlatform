package zw.co.zim.willplatform.service;

import org.springframework.data.domain.Page;
import zw.co.zim.willplatform.common.BaseService;
import zw.co.zim.willplatform.model.Client;
import zw.co.zim.willplatform.model.LiabilitiesCreditCard;

public interface LiabilitiesCreditCardService extends BaseService<LiabilitiesCreditCard> {
    Page<LiabilitiesCreditCard> findAll(Integer pageNo, Integer pageSize);

    Page<LiabilitiesCreditCard> findAllByUserId(Client clientId, Integer pageNo, Integer pageSize);
}
