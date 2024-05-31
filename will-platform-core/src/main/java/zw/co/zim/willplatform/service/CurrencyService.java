package zw.co.zim.willplatform.service;

import org.springframework.data.domain.Page;
import zw.co.zim.willplatform.common.BaseService;
import zw.co.zim.willplatform.model.Currency;

import java.util.Optional;

public interface CurrencyService extends BaseService<Currency> {
    Page<Currency> findAll(Integer pageNo, Integer pageSize);

    Optional<Currency> findCurrencyByName(String name);
}
