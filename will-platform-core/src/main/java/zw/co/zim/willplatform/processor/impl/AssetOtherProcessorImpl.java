package zw.co.zim.willplatform.processor.impl;

import zw.co.zim.willplatform.dto.AssetOtherRecordDto;
import zw.co.zim.willplatform.dto.mapper.AssetOtherDtoMapper;
import zw.co.zim.willplatform.model.AssetOther;
import zw.co.zim.willplatform.processor.AssetOtherProcessor;
import zw.co.zim.willplatform.service.AssetOtherService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AssetOtherProcessorImpl implements AssetOtherProcessor {
    private final AssetOtherService service;
    private final AssetOtherDtoMapper mapper;

    public AssetOtherProcessorImpl(AssetOtherService service, AssetOtherDtoMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @Override
    public List<AssetOtherRecordDto> findAll() {
        return service.findAll()
            .stream()
            .map(mapper)
            .collect(Collectors.toList());
    }

    @Override
    public Optional<AssetOtherRecordDto> findById(Long id) {
        Optional<AssetOther> assetOther = service.findById(id);
        return assetOther.map(mapper);
    }

    @Override
    public AssetOtherRecordDto save(AssetOtherRecordDto recordDto) {
        AssetOther assetOther = service.save(new AssetOther(recordDto));
        return mapper.apply(assetOther);
    }

    @Override
    public AssetOtherRecordDto update(Long id, AssetOtherRecordDto recordDto) {
        AssetOther assetOther = service.update(id, new AssetOther(recordDto));
        return mapper.apply(assetOther);
    }

    @Override
    public void deleteById(Long id) {
        service.deleteById(id);
    }
}
