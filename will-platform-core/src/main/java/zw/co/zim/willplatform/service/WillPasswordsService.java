package zw.co.zim.willplatform.service;

import org.springframework.data.domain.Page;
import zw.co.zim.willplatform.common.AppService;
import zw.co.zim.willplatform.model.Client;
import zw.co.zim.willplatform.model.WillPasswordsInstructions;

public interface WillPasswordsService extends AppService<WillPasswordsInstructions> {
    Page<WillPasswordsInstructions> findAll(Integer pageNo, Integer pageSize);

    Page<WillPasswordsInstructions> findAllByUserId(Client userId, Integer pageNo, Integer pageSize);
}
