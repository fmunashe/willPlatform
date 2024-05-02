package zw.co.zim.willplatform.processor.impl;

import zw.co.zim.willplatform.dto.AssetPersonOwingMoneyRecordDto;
import zw.co.zim.willplatform.dto.mapper.AssetPersonOwingMoneyDtoMapper;
import zw.co.zim.willplatform.model.AssetPersonOwingMoney;
import zw.co.zim.willplatform.processor.AssetPersonOwingMoneyProcessor;
import zw.co.zim.willplatform.service.AssetPersonOwingMoneyService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AssetPersonOwingMoneyProcessorImpl implements AssetPersonOwingMoneyProcessor {
    private final AssetPersonOwingMoneyService service;
    private final AssetPersonOwingMoneyDtoMapper mapper;

    public AssetPersonOwingMoneyProcessorImpl(AssetPersonOwingMoneyService service, AssetPersonOwingMoneyDtoMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @Override
    public List<AssetPersonOwingMoneyRecordDto> findAll() {
        return service.findAll()
            .stream()
            .map(mapper)
            .collect(Collectors.toList());
    }

    @Override
    public Optional<AssetPersonOwingMoneyRecordDto> findById(Long id) {
        Optional<AssetPersonOwingMoney> optionalAssetPersonOwingMoney = service.findById(id);
        return optionalAssetPersonOwingMoney.map(mapper);
    }

    @Override
    public AssetPersonOwingMoneyRecordDto save(AssetPersonOwingMoneyRecordDto recordDto) {
        AssetPersonOwingMoney personOwingMoney = service.save(new AssetPersonOwingMoney(recordDto));
        return mapper.apply(personOwingMoney);
    }

    @Override
    public AssetPersonOwingMoneyRecordDto update(Long id, AssetPersonOwingMoneyRecordDto recordDto) {
        AssetPersonOwingMoney updatedPersonOwingMoney = service.update(id, new AssetPersonOwingMoney(recordDto));
        return mapper.apply(updatedPersonOwingMoney);
    }

    @Override
    public void deleteById(Long id) {
        service.deleteById(id);
    }
}
