package zw.co.zim.willplatform.service;

import org.springframework.data.domain.Page;
import zw.co.zim.willplatform.common.AppService;
import zw.co.zim.willplatform.model.Client;
import zw.co.zim.willplatform.model.WillAlternativeExecutor;

import java.util.Optional;

public interface WillAlternativeExecutorService extends AppService<WillAlternativeExecutor> {
    Page<WillAlternativeExecutor> findAll(Integer pageNo, Integer pageSize);

    Optional<WillAlternativeExecutor> findFirstByUserId(Client userId, Integer pageNo, Integer pageSize);
}
