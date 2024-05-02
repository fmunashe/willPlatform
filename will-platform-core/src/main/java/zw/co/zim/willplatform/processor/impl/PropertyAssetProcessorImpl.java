package zw.co.zim.willplatform.processor.impl;

import zw.co.zim.willplatform.dto.PropertyAssetRecordDto;
import zw.co.zim.willplatform.dto.mapper.PropertyAssetDtoMapper;
import zw.co.zim.willplatform.model.PropertyAsset;
import zw.co.zim.willplatform.processor.PropertyAssetProcessor;
import zw.co.zim.willplatform.service.PropertyAssetService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PropertyAssetProcessorImpl implements PropertyAssetProcessor {
    private final PropertyAssetService propertyAssetService;
    private final PropertyAssetDtoMapper mapper;

    public PropertyAssetProcessorImpl(PropertyAssetService propertyAssetService, PropertyAssetDtoMapper mapper) {
        this.propertyAssetService = propertyAssetService;
        this.mapper = mapper;
    }

    @Override
    public List<PropertyAssetRecordDto> findAll() {
        return propertyAssetService.findAll()
            .stream()
            .map(mapper)
            .collect(Collectors.toList());
    }

    @Override
    public Optional<PropertyAssetRecordDto> findById(Long id) {
        Optional<PropertyAsset> propertyAsset = propertyAssetService.findById(id);
        return propertyAsset.map(mapper);
    }

    @Override
    public PropertyAssetRecordDto save(PropertyAssetRecordDto propertyAssetRecordDto) {
        PropertyAsset propertyAsset = propertyAssetService.save(new PropertyAsset(propertyAssetRecordDto));
        return mapper.apply(propertyAsset);
    }

    @Override
    public PropertyAssetRecordDto update(Long id, PropertyAssetRecordDto propertyAssetRecordDto) {
        PropertyAsset propertyAsset = propertyAssetService.update(id, new PropertyAsset(propertyAssetRecordDto));
        return mapper.apply(propertyAsset);

    }

    @Override
    public void deleteById(Long id) {
        propertyAssetService.deleteById(id);
    }
}
