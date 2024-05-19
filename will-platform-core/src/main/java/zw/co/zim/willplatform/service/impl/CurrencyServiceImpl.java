package zw.co.zim.willplatform.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import zw.co.zim.willplatform.enums.RecordStatus;
import zw.co.zim.willplatform.model.Currency;
import zw.co.zim.willplatform.repository.CurrencyRepository;
import zw.co.zim.willplatform.service.CurrencyService;
import zw.co.zim.willplatform.utils.PageableHelper;

import java.util.List;
import java.util.Optional;

@Service
public class CurrencyServiceImpl implements CurrencyService {
    private final CurrencyRepository currencyRepository;

    public CurrencyServiceImpl(CurrencyRepository currencyRepository) {
        this.currencyRepository = currencyRepository;
    }

    @Override
    public List<Currency> findAll() {
        return currencyRepository.findAll();
    }

    @Override
    public Page<Currency> findAll(Integer pageNo, Integer pageSize) {
        pageNo = PageableHelper.cleanPageNumber(pageNo);
        pageSize = PageableHelper.cleanPageSize(pageSize);
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.Direction.DESC, "id");
        return currencyRepository.findAllByRecordStatusNot(pageable, RecordStatus.DELETED);
    }

    @Override
    public Optional<Currency> findById(Long id) {
        return currencyRepository.findFirstByIdAndRecordStatusNot(id, RecordStatus.DELETED);
    }

    @Override
    public Currency save(Currency currency) {
        return currencyRepository.save(currency);
    }

    @Override
    public Currency update(Long id, Currency currency) {
        Optional<Currency> optionalCurrency = currencyRepository.findFirstByIdAndRecordStatusNot(id, RecordStatus.DELETED);
        if (optionalCurrency.isPresent()) {
            Currency currentRecord = optionalCurrency.get();
            currentRecord.setName(currency.getName());
            currentRecord.setIso(currency.getIso());
            currentRecord.setSymbol(currency.getSymbol());
            currentRecord.setConversionRate(currency.getConversionRate());
            currentRecord.setRecordStatus(currency.getRecordStatus());
            return currencyRepository.save(currentRecord);
        }
        return currency;
    }

    @Override
    public void deleteById(Long id) {
        Optional<Currency> currency = this.findById(id);
        if (currency.isPresent()) {
            Currency currency1 = currency.get();
            currency1.setRecordStatus(RecordStatus.DELETED);
            currencyRepository.save(currency1);
        }
    }

    @Override
    public Optional<Currency> findCurrencyByName(String name) {
        return currencyRepository.findFirstByNameAndRecordStatusNot(name, RecordStatus.DELETED);
    }
}
