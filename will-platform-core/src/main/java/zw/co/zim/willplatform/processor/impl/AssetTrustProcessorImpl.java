package zw.co.zim.willplatform.processor.impl;

import zw.co.zim.willplatform.dto.AssetTrustDto;
import zw.co.zim.willplatform.dto.mapper.AssetTrustDtoMapper;
import zw.co.zim.willplatform.model.AssetTrust;
import zw.co.zim.willplatform.processor.AssetTrustProcessor;
import zw.co.zim.willplatform.service.AssetTrustService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AssetTrustProcessorImpl implements AssetTrustProcessor {
    private final AssetTrustService assetTrustService;
    private final AssetTrustDtoMapper mapper;

    public AssetTrustProcessorImpl(AssetTrustService assetTrustService, AssetTrustDtoMapper mapper) {
        this.assetTrustService = assetTrustService;
        this.mapper = mapper;
    }

    @Override
    public List<AssetTrustDto> findAll() {
        return assetTrustService.findAll()
            .stream()
            .map(mapper)
            .collect(Collectors.toList());
    }

    @Override
    public Optional<AssetTrustDto> findById(Long id) {
        Optional<AssetTrust> assetTrust = assetTrustService.findById(id);
        return assetTrust.map(mapper);
    }

    @Override
    public AssetTrustDto save(AssetTrustDto assetTrustDto) {
        AssetTrust trust = assetTrustService.save(new AssetTrust(assetTrustDto));
        return mapper.apply(trust);
    }

    @Override
    public AssetTrustDto update(Long id, AssetTrustDto assetTrustDto) {
        AssetTrust trust = assetTrustService.update(id, new AssetTrust(assetTrustDto));
        return mapper.apply(trust);
    }

    @Override
    public void deleteById(Long id) {
        assetTrustService.deleteById(id);
    }
}
