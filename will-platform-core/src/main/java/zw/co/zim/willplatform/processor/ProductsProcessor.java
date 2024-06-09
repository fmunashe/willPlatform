package zw.co.zim.willplatform.processor;

import zw.co.zim.willplatform.common.BaseProcessor;
import zw.co.zim.willplatform.dto.ProductsDto;
import zw.co.zim.willplatform.utils.enums.ProductNames;
import zw.co.zim.willplatform.utils.messages.request.ProductRequest;
import zw.co.zim.willplatform.utils.messages.response.basic.ApiResponse;

public interface ProductsProcessor extends BaseProcessor<ProductsDto, ProductRequest> {
    ApiResponse<ProductsDto> findAllByCurrency(String currencyName, Integer pageNo, Integer pageSize);

    ApiResponse<ProductsDto> findProductByName(ProductNames name);
}
