package zw.co.zim.willplatform.service;

import org.springframework.data.domain.Page;
import zw.co.zim.willplatform.common.AppService;
import zw.co.zim.willplatform.model.Client;
import zw.co.zim.willplatform.model.LiabilitiesCreditCard;

import java.util.List;

public interface LiabilitiesCreditCardService extends AppService<LiabilitiesCreditCard> {
    Page<LiabilitiesCreditCard> findAll(Integer pageNo, Integer pageSize);

    List<LiabilitiesCreditCard> findAllByUserId(Client clientId);
}
