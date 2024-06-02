package zw.co.zim.willplatform.service;

import org.springframework.data.domain.Page;
import zw.co.zim.willplatform.common.BaseService;
import zw.co.zim.willplatform.model.Client;
import zw.co.zim.willplatform.model.WillAlternativeExecutor;

import java.util.Optional;

public interface WillAlternativeExecutorService extends BaseService<WillAlternativeExecutor> {
    Page<WillAlternativeExecutor> findAll(Integer pageNo, Integer pageSize);

    Page<WillAlternativeExecutor> findAllByUserId(Client userId, Integer pageNo, Integer pageSize);
}
