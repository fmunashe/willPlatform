package zw.co.zim.willplatform.processor.impl;

import zw.co.zim.willplatform.dto.AssetOffshoreRecordDto;
import zw.co.zim.willplatform.dto.mapper.AssetOffshoreDtoMapper;
import zw.co.zim.willplatform.model.AssetOffshore;
import zw.co.zim.willplatform.processor.AssetOffshoreProcessor;
import zw.co.zim.willplatform.service.AssetOffshoreService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AssetOffshoreProcessorImpl implements AssetOffshoreProcessor {
    private final AssetOffshoreService service;
    private final AssetOffshoreDtoMapper mapper;

    public AssetOffshoreProcessorImpl(AssetOffshoreService service, AssetOffshoreDtoMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @Override
    public List<AssetOffshoreRecordDto> findAll() {
        return service.findAll()
            .stream()
            .map(mapper)
            .collect(Collectors.toList());
    }

    @Override
    public Optional<AssetOffshoreRecordDto> findById(Long id) {
        Optional<AssetOffshore> optionalAssetOffshore = service.findById(id);
        return optionalAssetOffshore.map(mapper);
    }

    @Override
    public AssetOffshoreRecordDto save(AssetOffshoreRecordDto recordDto) {
        AssetOffshore offshore = service.save(new AssetOffshore(recordDto));
        return mapper.apply(offshore);
    }

    @Override
    public AssetOffshoreRecordDto update(Long id, AssetOffshoreRecordDto recordDto) {
        AssetOffshore offshore = service.update(id, new AssetOffshore(recordDto));
        return mapper.apply(offshore);
    }

    @Override
    public void deleteById(Long id) {
        service.deleteById(id);
    }
}
