package zw.co.zim.willplatform.service;

import org.springframework.data.domain.Page;
import zw.co.zim.willplatform.common.BaseService;
import zw.co.zim.willplatform.model.Client;
import zw.co.zim.willplatform.model.WillBurialRequest;

import java.util.Optional;

public interface WillBurialRequestService extends BaseService<WillBurialRequest> {
    Page<WillBurialRequest> findAll(Integer pageNo, Integer pageSize);

    Optional<WillBurialRequest> findFirstByUserId(Client userId);
}
