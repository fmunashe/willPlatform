package zw.co.zim.willplatform.processor.impl;

import zw.co.zim.willplatform.dto.AssetTimeShareDto;
import zw.co.zim.willplatform.dto.mapper.AssetTimeshareDtoMapper;
import zw.co.zim.willplatform.model.AssetTimeShare;
import zw.co.zim.willplatform.processor.AssetTimeshareProcessor;
import zw.co.zim.willplatform.service.AssetTimeshareService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AssetTimeshareProcessorImpl implements AssetTimeshareProcessor {
    private final AssetTimeshareService assetTimeshareService;
    private final AssetTimeshareDtoMapper mapper;

    public AssetTimeshareProcessorImpl(AssetTimeshareService assetTimeshareService, AssetTimeshareDtoMapper mapper) {
        this.assetTimeshareService = assetTimeshareService;
        this.mapper = mapper;
    }


    @Override
    public List<AssetTimeShareDto> findAll() {
        return assetTimeshareService.findAll()
            .stream()
            .map(mapper)
            .collect(Collectors.toList());
    }

    @Override
    public Optional<AssetTimeShareDto> findById(Long id) {
        Optional<AssetTimeShare> optionalAssetTimeShare = assetTimeshareService.findById(id);
        return optionalAssetTimeShare.map(mapper);
    }

    @Override
    public AssetTimeShareDto save(AssetTimeShareDto assetTimeShareDto) {
        AssetTimeShare timeShare = assetTimeshareService.save(new AssetTimeShare(assetTimeShareDto));
        return mapper.apply(timeShare);
    }

    @Override
    public AssetTimeShareDto update(Long id, AssetTimeShareDto assetTimeShareDto) {
        AssetTimeShare timeShare = assetTimeshareService.update(id, new AssetTimeShare(assetTimeShareDto));
        return mapper.apply(timeShare);
    }

    @Override
    public void deleteById(Long id) {
        assetTimeshareService.deleteById(id);
    }
}
