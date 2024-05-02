package zw.co.zim.willplatform.service.impl;

import zw.co.zim.willplatform.exceptions.RecordNotFoundException;
import zw.co.zim.willplatform.model.PropertyAsset;
import zw.co.zim.willplatform.repository.PropertyAssetRepository;
import zw.co.zim.willplatform.service.PropertyAssetService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PropertyAssetServiceImpl implements PropertyAssetService {
    private final PropertyAssetRepository propertyAssetRepository;

    public PropertyAssetServiceImpl(PropertyAssetRepository propertyAssetRepository) {
        this.propertyAssetRepository = propertyAssetRepository;
    }

    @Override
    public List<PropertyAsset> findAll() {
        return propertyAssetRepository.findAll();
    }

    @Override
    public Optional<PropertyAsset> findById(Long id) {
        return Optional.ofNullable(propertyAssetRepository.findById(id).orElseThrow(() -> new RecordNotFoundException("Property with id of " + id + " not found")));
    }

    @Override
    public PropertyAsset save(PropertyAsset propertyAsset) {
        return propertyAssetRepository.save(propertyAsset);
    }

    @Override
    public PropertyAsset update(Long id, PropertyAsset propertyAsset) {
        Optional<PropertyAsset> currentProperty = propertyAssetRepository.findById(id);
        if (currentProperty.isEmpty())
            throw new RecordNotFoundException("Property with id of " + id + " not found");
        propertyAsset.setId(currentProperty.get().getId());
        return propertyAssetRepository.save(propertyAsset);


    }

    @Override
    public void deleteById(Long id) {
        Optional<PropertyAsset> propertyOptional = propertyAssetRepository.findById(id);
        if (propertyOptional.isEmpty())
            throw new RecordNotFoundException("Property with id of " + id + " not found");
        propertyAssetRepository.deleteById(id);

    }
}
