package zw.co.zim.willplatform.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import zw.co.zim.willplatform.enums.RecordStatus;
import zw.co.zim.willplatform.model.Client;
import zw.co.zim.willplatform.model.PropertyAsset;
import zw.co.zim.willplatform.repository.PropertyAssetRepository;
import zw.co.zim.willplatform.service.PropertyAssetService;
import zw.co.zim.willplatform.utils.PageableHelper;

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
        return propertyAssetRepository.findAllByRecordStatusNot(RecordStatus.DELETED);
    }

    @Override
    public Page<PropertyAsset> findAll(Integer pageNo, Integer pageSize) {
        pageNo = PageableHelper.cleanPageNumber(pageNo);
        pageSize = PageableHelper.cleanPageSize(pageSize);
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.Direction.DESC, "id");
        return propertyAssetRepository.findAllByRecordStatusNot(pageable, RecordStatus.DELETED);
    }

    @Override
    public Page<PropertyAsset> findAllByUserId(Client clientId, Integer pageNo, Integer pageSize) {
        pageNo = PageableHelper.cleanPageNumber(pageNo);
        pageSize = PageableHelper.cleanPageSize(pageSize);
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.Direction.DESC, "id");
        return propertyAssetRepository.findAllByUserIdAndRecordStatusNot(pageable, clientId, RecordStatus.DELETED);
    }

    @Override
    public Optional<PropertyAsset> findById(Long id) {
        return propertyAssetRepository.findFirstByIdAndRecordStatusNot(id, RecordStatus.DELETED);
    }

    @Override
    public PropertyAsset save(PropertyAsset propertyAsset) {
        return propertyAssetRepository.save(propertyAsset);
    }

    @Override
    public PropertyAsset update(Long id, PropertyAsset propertyAsset) {
        Optional<PropertyAsset> currentProperty = this.findById(id);
        if (currentProperty.isPresent()) {
            propertyAsset.setId(currentProperty.get().getId());
            return propertyAssetRepository.save(propertyAsset);
        }
        return propertyAsset;
    }

    @Override
    public void deleteById(Long id) {
        Optional<PropertyAsset> propertyOptional = this.findById(id);
        if (propertyOptional.isPresent()) {
            PropertyAsset asset = propertyOptional.get();
            asset.setRecordStatus(RecordStatus.DELETED);
            propertyAssetRepository.save(asset);
        }
    }
}
