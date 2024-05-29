package zw.co.zim.willplatform.service;

import org.springframework.data.domain.Page;
import zw.co.zim.willplatform.common.AppService;
import zw.co.zim.willplatform.utils.enums.ProductNames;
import zw.co.zim.willplatform.model.Currency;
import zw.co.zim.willplatform.model.Products;

import java.util.Optional;

public interface ProductsService extends AppService<Products> {
    Page<Products> findAll(Integer pageNo,Integer pageSize);

    Page<Products> findAllByCurrency( Currency currency,Integer pageNo,Integer pageSize);

    Optional<Products> findProductByName(ProductNames name);
}
