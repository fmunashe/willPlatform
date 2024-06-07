package zw.co.zim.willplatform.dto.mapper;

import org.springframework.stereotype.Service;
import zw.co.zim.willplatform.dto.CurrencyDto;
import zw.co.zim.willplatform.model.Currency;

import java.util.function.Function;

@Service
public class CurrencyDtoMapper implements Function<Currency, CurrencyDto> {
    @Override
    public CurrencyDto apply(Currency currency) {
        return CurrencyDto.builder()
            .id(currency.getId())
            .name(currency.getName())
            .iso(currency.getIso())
            .symbol(currency.getSymbol())
            .conversionRate(currency.getConversionRate())
            .createdAt(currency.getCreatedAt())
            .updatedAt(currency.getUpdatedAt())
            .recordStatus(currency.getRecordStatus())
            .build();
    }
}
