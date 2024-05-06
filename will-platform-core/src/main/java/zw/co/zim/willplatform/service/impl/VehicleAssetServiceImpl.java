package zw.co.zim.willplatform.service.impl;

import org.springframework.stereotype.Service;
import zw.co.zim.willplatform.enums.RecordStatus;
import zw.co.zim.willplatform.exceptions.RecordExistsException;
import zw.co.zim.willplatform.exceptions.RecordNotFoundException;
import zw.co.zim.willplatform.model.VehicleAsset;
import zw.co.zim.willplatform.repository.VehicleAssetRepository;
import zw.co.zim.willplatform.service.VehicleAssetService;

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
        return vehicleAssetRepository.findAll();
    }

    @Override
    public Optional<VehicleAsset> findById(Long id) {
        return Optional.ofNullable(vehicleAssetRepository.findById(id).orElseThrow(() -> new RecordNotFoundException("Vehicle with ID of " + id + " not found")));
    }

    @Override
    public VehicleAsset save(VehicleAsset vehicleAsset) {
        Optional<VehicleAsset> optionalRegNumber = vehicleAssetRepository.findFirstByRegistrationNumberAndRecordStatusNot(vehicleAsset.getRegistrationNumber(), RecordStatus.DELETED);
        Optional<VehicleAsset> optionalEngineNumber = vehicleAssetRepository.findFirstByEngineNumberAndRecordStatusNot(vehicleAsset.getEngineNumber(), RecordStatus.DELETED);
        if (optionalRegNumber.isPresent())
            throw new RecordExistsException("A vehicle with the same registration number of " + vehicleAsset.getRegistrationNumber() + " already exist");

        if (optionalEngineNumber.isPresent())
            throw new RecordExistsException("A vehicle with the same engine number of " + vehicleAsset.getEngineNumber() + " already exist");

        return vehicleAssetRepository.save(vehicleAsset);
    }

    @Override
    public VehicleAsset update(Long id, VehicleAsset vehicleAsset) {
        Optional<VehicleAsset> currentVehicle = vehicleAssetRepository.findById(id);
        if (currentVehicle.isPresent()) {
            vehicleAsset.setId(currentVehicle.get().getId());
            return vehicleAssetRepository.save(vehicleAsset);
        }
        throw new RecordNotFoundException("Vehicle with id of " + id + " not found");
    }

    @Override
    public void deleteById(Long id) {
        Optional<VehicleAsset> vehicleOptional = vehicleAssetRepository.findById(id);
        if (vehicleOptional.isPresent()) {
            vehicleAssetRepository.deleteById(id);
        } else {
            throw new RecordNotFoundException("Vehicle with id of " + id + " not found");
        }
    }

    @Override
    public Optional<VehicleAsset> findVehicleByRegNumber(String regNumber) {
        return Optional.ofNullable(vehicleAssetRepository.findFirstByRegistrationNumberAndRecordStatusNot(regNumber,RecordStatus.DELETED).orElseThrow(() -> new RecordNotFoundException("Vehicle with registration number of " + regNumber + "not found")));
    }
}
