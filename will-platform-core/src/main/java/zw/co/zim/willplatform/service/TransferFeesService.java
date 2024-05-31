package zw.co.zim.willplatform.service;

import org.springframework.data.domain.Page;
import zw.co.zim.willplatform.common.BaseService;
import zw.co.zim.willplatform.model.TransferFees;


public interface TransferFeesService extends BaseService<TransferFees> {
    Page<TransferFees> findAll(Integer pageNo, Integer pageSize);
}
