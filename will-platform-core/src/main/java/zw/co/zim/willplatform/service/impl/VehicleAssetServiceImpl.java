package zw.co.zim.willplatform.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import zw.co.zim.willplatform.utils.enums.RecordStatus;
import zw.co.zim.willplatform.model.VehicleAsset;
import zw.co.zim.willplatform.repository.VehicleAssetRepository;
import zw.co.zim.willplatform.service.VehicleAssetService;
import zw.co.zim.willplatform.utils.PageableHelper;

import java.util.List;
import java.util.Optional;

@Service
public class VehicleAssetServiceImpl implements VehicleAssetService {
    private final VehicleAssetRepository vehicleAssetRepository;

    public VehicleAssetServiceImpl(VehicleAssetRepository vehicleAssetRepository) {
        this.vehicleAssetRepository = vehicleAssetRepository;
    }

    @Override
    public List<VehicleAsset> findAll() {
        return vehicleAssetRepository.findAllByRecordStatusNot(RecordStatus.DELETED);
    }

    @Override
    public Page<VehicleAsset> findAll(Integer pageNo, Integer pageSize) {
        pageNo = PageableHelper.cleanPageNumber(pageNo);
        pageSize = PageableHelper.cleanPageSize(pageSize);
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.Direction.DESC, "id");
        return vehicleAssetRepository.findAllByRecordStatusNot(pageable, RecordStatus.DELETED);
    }

    @Override
    public Optional<VehicleAsset> findVehicleByEngineNumber(String engineNumber) {
        return vehicleAssetRepository.findFirstByEngineNumberAndRecordStatusNot(engineNumber, RecordStatus.DELETED);
    }

    @Override
    public Optional<VehicleAsset> findById(Long id) {
        return vehicleAssetRepository.findFirstByIdAndRecordStatusNot(id, RecordStatus.DELETED);
    }

    @Override
    public VehicleAsset save(VehicleAsset vehicleAsset) {
        return vehicleAssetRepository.save(vehicleAsset);
    }

    @Override
    public VehicleAsset update(Long id, VehicleAsset vehicleAsset) {
        Optional<VehicleAsset> currentVehicle = this.findById(id);
        if (currentVehicle.isPresent()) {
            vehicleAsset.setId(currentVehicle.get().getId());
            return vehicleAssetRepository.save(vehicleAsset);
        }
        return vehicleAsset;
    }

    @Override
    public void deleteById(Long id) {
        Optional<VehicleAsset> vehicleOptional = this.findById(id);
        if (vehicleOptional.isPresent()) {
            VehicleAsset asset = vehicleOptional.get();
            asset.setRecordStatus(RecordStatus.DELETED);
            vehicleAssetRepository.save(asset);
        }
    }

    @Override
    public Optional<VehicleAsset> findVehicleByRegNumber(String regNumber) {
        return vehicleAssetRepository.findFirstByRegistrationNumberAndRecordStatusNot(regNumber, RecordStatus.DELETED);
    }
}
