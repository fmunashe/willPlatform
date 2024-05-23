package zw.co.zim.willplatform.service;

import org.springframework.data.domain.Page;
import zw.co.zim.willplatform.common.AppService;
import zw.co.zim.willplatform.model.TransferFees;


public interface TransferFeesService extends AppService<TransferFees> {
    Page<TransferFees> findAll(Integer pageNo, Integer pageSize);
}
