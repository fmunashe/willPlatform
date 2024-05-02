package zw.co.zim.willplatform.processor.impl;

import zw.co.zim.willplatform.dto.BankAssetRecordDto;
import zw.co.zim.willplatform.dto.mapper.BankAssetDtoMapper;
import zw.co.zim.willplatform.model.BankAsset;
import zw.co.zim.willplatform.processor.BankAssetProcessor;
import zw.co.zim.willplatform.service.BankAssetService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BankAssetProcessorImpl implements BankAssetProcessor {
    private final BankAssetService bankAssetService;
    private final BankAssetDtoMapper mapper;

    public BankAssetProcessorImpl(BankAssetService bankAssetService, BankAssetDtoMapper mapper) {
        this.bankAssetService = bankAssetService;
        this.mapper = mapper;
    }

    @Override
    public List<BankAssetRecordDto> findAll() {
        return bankAssetService.findAll()
            .stream()
            .map(mapper)
            .collect(Collectors.toList());
    }

    @Override
    public Optional<BankAssetRecordDto> findById(Long id) {
        Optional<BankAsset> bankAsset = bankAssetService.findById(id);
        return bankAsset.map(mapper);
    }

    @Override
    public BankAssetRecordDto save(BankAssetRecordDto bankAssetRecordDto) {
        BankAsset bankAsset = bankAssetService.save(new BankAsset(bankAssetRecordDto));
        return  mapper.apply(bankAsset);
    }

    @Override
    public BankAssetRecordDto update(Long id, BankAssetRecordDto bankAssetRecordDto) {
      BankAsset bankAsset = bankAssetService.update(id, new BankAsset(bankAssetRecordDto));
      return mapper.apply(bankAsset);
    }

    @Override
    public void deleteById(Long id) {
        bankAssetService.deleteById(id);
    }
}
