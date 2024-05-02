package zw.co.zim.willplatform.processor.impl;

import zw.co.zim.willplatform.dto.AssetInvestmentRecordDto;
import zw.co.zim.willplatform.dto.mapper.AssetInvestmentDtoMapper;
import zw.co.zim.willplatform.model.AssetInvestment;
import zw.co.zim.willplatform.processor.AssetInvestmentProcessor;
import zw.co.zim.willplatform.service.AssetInvestmentService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AssetInvestmentProcessorImpl implements AssetInvestmentProcessor {
    private final AssetInvestmentService service;
    private final AssetInvestmentDtoMapper mapper;

    public AssetInvestmentProcessorImpl(AssetInvestmentService service, AssetInvestmentDtoMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @Override
    public List<AssetInvestmentRecordDto> findAll() {
        return service.findAll()
            .stream()
            .map(mapper)
            .collect(Collectors.toList());
    }

    @Override
    public Optional<AssetInvestmentRecordDto> findById(Long id) {
        Optional<AssetInvestment> investment = service.findById(id);
        return investment.map(mapper);
    }

    @Override
    public AssetInvestmentRecordDto save(AssetInvestmentRecordDto recordDto) {
        AssetInvestment investment = service.save(new AssetInvestment(recordDto));
        return mapper.apply(investment);
    }

    @Override
    public AssetInvestmentRecordDto update(Long id, AssetInvestmentRecordDto recordDto) {
        AssetInvestment investment = service.update(id, new AssetInvestment(recordDto));
        return mapper.apply(investment);
    }

    @Override
    public void deleteById(Long id) {
        service.deleteById(id);
    }
}
