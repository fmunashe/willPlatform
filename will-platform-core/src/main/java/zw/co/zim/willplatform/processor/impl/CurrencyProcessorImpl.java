package zw.co.zim.willplatform.processor.impl;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import zw.co.zim.willplatform.dto.CurrencyDto;
import zw.co.zim.willplatform.dto.mapper.CurrencyDtoMapper;
import zw.co.zim.willplatform.exceptions.RecordExistsException;
import zw.co.zim.willplatform.exceptions.RecordNotFoundException;
import zw.co.zim.willplatform.model.Currency;
import zw.co.zim.willplatform.processor.CurrencyProcessor;
import zw.co.zim.willplatform.service.CurrencyService;
import zw.co.zim.willplatform.utils.AppConstants;
import zw.co.zim.willplatform.utils.enums.RecordStatus;
import zw.co.zim.willplatform.utils.messages.request.CurrencyRequest;
import zw.co.zim.willplatform.utils.messages.response.basic.ApiResponse;
import zw.co.zim.willplatform.utils.messages.response.helper.HelperResponse;

import java.util.Objects;
import java.util.Optional;

@Service
public class CurrencyProcessorImpl implements CurrencyProcessor {
    private final CurrencyService currencyService;
    private final ModelMapper modelMapper;
    private final CurrencyDtoMapper mapper;

    public CurrencyProcessorImpl(CurrencyService currencyService, ModelMapper modelMapper, CurrencyDtoMapper mapper) {
        this.currencyService = currencyService;
        this.modelMapper = modelMapper;
        this.mapper = mapper;
    }

    @Override
    public ApiResponse<CurrencyDto> findAll(Integer pageNo, Integer pageSize) {
        Page<Currency> currencies = currencyService.findAll(pageNo, pageSize);
        return HelperResponse.buildApiResponse(currencies, mapper, true, 200, true, AppConstants.LIST_MESSAGE, null);

    }

    @Override
    public ApiResponse<CurrencyDto> findById(Long id) {
        Optional<Currency> optional = currencyService.findById(id);

        return optional.map(currency -> HelperResponse.buildApiResponse(null, null, false, 200, true, AppConstants.FOUND_MESSAGE, mapper.apply(currency)))
            .orElseThrow(() -> new RecordNotFoundException("Failed to find currency record with id of " + id));

    }

    @Override
    public ApiResponse<CurrencyDto> save(CurrencyRequest currencyRequest) {

        Optional<Currency> optional = currencyService.findCurrencyByName(currencyRequest.getName());
        if (optional.isPresent()) {
            throw new RecordExistsException("There is already a currency record with name " + currencyRequest.getName());
        }

        CurrencyDto recordDto = CurrencyDto.builder()
            .iso(currencyRequest.getIso())
            .name(currencyRequest.getName())
            .symbol(currencyRequest.getSymbol())
            .recordStatus(RecordStatus.ACTIVE)
            .build();

        Currency currency = modelMapper.map(recordDto, Currency.class);
        currency = currencyService.save(currency);
        return HelperResponse.buildApiResponse(null, null, false, 201, true, AppConstants.SUCCESS_MESSAGE, mapper.apply(currency));

    }

    @Override
    public ApiResponse<CurrencyDto> update(Long id, CurrencyDto currencyDto) {
        Optional<Currency> currency = currencyService.findById(id);

        if (currency.isEmpty() || !Objects.equals(currency.get().getId(), id)) {
            throw new RecordNotFoundException("Failed to find currency record with Id " + id);
        }
        Currency updatedRecord = currencyService.update(id, modelMapper.map(currencyDto, Currency.class));
        CurrencyDto mappedDto = mapper.apply(updatedRecord);
        return HelperResponse.buildApiResponse(null, null, false, 200, true, AppConstants.SUCCESS_MESSAGE, mappedDto);

    }

    @Override
    public ApiResponse<CurrencyDto> deleteById(Long id) {
        Optional<Currency> currency = currencyService.findById(id);

        if (currency.isEmpty() || !currency.get().getId().equals(id)) {
            throw new RecordNotFoundException("Failed to find currency record with Id " + id);
        }
        currencyService.deleteById(id);
        return HelperResponse.buildApiResponse(null, null, false, 200, true, AppConstants.SUCCESS_MESSAGE, null);

    }

    @Override
    public ApiResponse<CurrencyDto> findCurrencyByName(String name) {
        Optional<Currency> optional = currencyService.findCurrencyByName(name);

        return optional.map(currency -> HelperResponse.buildApiResponse(null, null, false, 200, true, AppConstants.FOUND_MESSAGE, mapper.apply(currency)))
            .orElseThrow(() -> new RecordNotFoundException("Failed to find currency record with name " + name));

    }
}
