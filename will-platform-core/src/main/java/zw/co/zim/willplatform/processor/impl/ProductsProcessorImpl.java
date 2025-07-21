package zw.co.zim.willplatform.processor.impl;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import zw.co.zim.willplatform.dto.ProductsDto;
import zw.co.zim.willplatform.dto.mapper.ProductsDtoMapper;
import zw.co.zim.willplatform.exceptions.RecordExistsException;
import zw.co.zim.willplatform.exceptions.RecordNotFoundException;
import zw.co.zim.willplatform.model.Currency;
import zw.co.zim.willplatform.model.Products;
import zw.co.zim.willplatform.processor.ProductsProcessor;
import zw.co.zim.willplatform.service.CurrencyService;
import zw.co.zim.willplatform.service.ProductsService;
import zw.co.zim.willplatform.utils.AppConstants;
import zw.co.zim.willplatform.utils.enums.ProductNames;
import zw.co.zim.willplatform.utils.enums.RecordStatus;
import zw.co.zim.willplatform.utils.messages.request.ProductRequest;
import zw.co.zim.willplatform.utils.messages.response.basic.ApiResponse;
import zw.co.zim.willplatform.utils.messages.response.helper.HelperResponse;

import java.util.Objects;
import java.util.Optional;

@Service
public class ProductsProcessorImpl implements ProductsProcessor {
    private final ProductsService service;
    private final ModelMapper modelMapper;
    private final ProductsDtoMapper mapper;
    private final CurrencyService currencyService;

    public ProductsProcessorImpl(ProductsService service, ModelMapper modelMapper, ProductsDtoMapper mapper, CurrencyService currencyService) {
        this.service = service;
        this.modelMapper = modelMapper;
        this.mapper = mapper;
        this.currencyService = currencyService;
    }

    @Override
    public ApiResponse<ProductsDto> findAll(Integer pageNo, Integer pageSize) {
        Page<Products> products = service.findAll(pageNo, pageSize);
        return HelperResponse.buildApiResponse(products, mapper, true, 200, true, AppConstants.LIST_MESSAGE, null);

    }

    @Override
    public ApiResponse<ProductsDto> findById(Long id) {
        Optional<Products> optional = service.findById(id);

        return optional.map(product -> HelperResponse.buildApiResponse(null, null, false, 200, true, AppConstants.FOUND_MESSAGE, mapper.apply(product)))
            .orElseThrow(() -> new RecordNotFoundException("Failed to find product record with id of " + id));

    }

    @Override
    public ApiResponse<ProductsDto> save(ProductRequest productRequest) {
        ProductNames name = ProductNames.valueOf(productRequest.getName());
        Optional<Products> optional = service.findProductByName(name);
        if (optional.isPresent()) {
            throw new RecordExistsException("There is already a product record with name " + productRequest.getName());
        }

        Optional<Currency> optionalCurrency = currencyService.findCurrencyByName(productRequest.getCurrency());
        if (optionalCurrency.isEmpty()) {
            throw new RecordNotFoundException("Failed to find currency with name " + productRequest.getCurrency());
        }

        Currency currency = optionalCurrency.get();


        ProductsDto recordDto = ProductsDto.builder()
            .name(name)
            .price(productRequest.getPrice())
            .description(productRequest.getDescription())
            .currency(currency)
            .recordStatus(RecordStatus.ACTIVE)
            .build();

        Products product = modelMapper.map(recordDto, Products.class);
        product = service.save(product);
        return HelperResponse.buildApiResponse(null, null, false, 201, true, AppConstants.SUCCESS_MESSAGE, mapper.apply(product));

    }

    @Override
    public ApiResponse<ProductsDto> update(Long id, ProductsDto productsDto) {
        Optional<Products> product = service.findById(id);

        if (product.isEmpty() || !Objects.equals(product.get().getId(), id)) {
            throw new RecordNotFoundException("Failed to find product record with Id " + id);
        }
        Products updatedRecord = service.update(id, modelMapper.map(productsDto, Products.class));
        ProductsDto mappedDto = mapper.apply(updatedRecord);
        return HelperResponse.buildApiResponse(null, null, false, 200, true, AppConstants.SUCCESS_MESSAGE, mappedDto);
    }

    @Override
    public ApiResponse<ProductsDto> deleteById(Long id) {
        Optional<Products> product = service.findById(id);

        if (product.isEmpty() || !product.get().getId().equals(id)) {
            throw new RecordNotFoundException("Failed to find product record with Id " + id);
        }
        service.deleteById(id);
        return HelperResponse.buildApiResponse(null, null, false, 200, true, AppConstants.SUCCESS_MESSAGE, null);

    }

    @Override
    public ApiResponse<ProductsDto> findAllByCurrency(String currencyName, Integer pageNo, Integer pageSize) {
        Optional<Currency> optionalCurrency = currencyService.findCurrencyByName(currencyName);
        if (optionalCurrency.isEmpty()) {
            throw new RecordExistsException("Failed to find currency with name " + currencyName);
        }

        Currency currency = optionalCurrency.get();

        Page<Products> products = service.findAllByCurrency(currency, pageNo, pageSize);
        return HelperResponse.buildApiResponse(products, mapper, true, 200, true, AppConstants.LIST_MESSAGE, null);
    }

    @Override
    public ApiResponse<ProductsDto> findProductByName(ProductNames name) {
        Optional<Products> optional = service.findProductByName(name);

        return optional.map(product -> HelperResponse.buildApiResponse(null, null, false, 200, true, AppConstants.FOUND_MESSAGE, mapper.apply(product)))
            .orElseThrow(() -> new RecordNotFoundException("Failed to find product record with name of " + name));

    }
}
