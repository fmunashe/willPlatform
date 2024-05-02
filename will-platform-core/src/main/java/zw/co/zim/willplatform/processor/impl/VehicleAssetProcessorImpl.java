package zw.co.zim.willplatform.processor.impl;

import zw.co.zim.willplatform.dto.VehicleAssetRecordDto;
import zw.co.zim.willplatform.dto.mapper.VehicleAssetDtoMapper;
import zw.co.zim.willplatform.model.VehicleAsset;
import zw.co.zim.willplatform.processor.VehicleAssetProcessor;
import zw.co.zim.willplatform.service.VehicleAssetService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VehicleAssetProcessorImpl implements VehicleAssetProcessor {
    private final VehicleAssetService vehicleAssetService;
    private final VehicleAssetDtoMapper vehicleAssetMapper;

    public VehicleAssetProcessorImpl(VehicleAssetService vehicleAssetService, VehicleAssetDtoMapper vehicleAssetMapper) {
        this.vehicleAssetService = vehicleAssetService;
        this.vehicleAssetMapper = vehicleAssetMapper;
    }

    @Override
    public List<VehicleAssetRecordDto> findAll() {
        return vehicleAssetService.findAll()
            .stream()
            .map(vehicleAssetMapper)
            .collect(Collectors.toList());
    }

    @Override
    public Optional<VehicleAssetRecordDto> findById(Long id) {
        Optional<VehicleAsset> vehicleAsset = vehicleAssetService.findById(id);
        return vehicleAsset.map(vehicleAssetMapper);
    }

    @Override
    public VehicleAssetRecordDto save(VehicleAssetRecordDto vehicleAssetRecordDto) {
        VehicleAsset vehicle = vehicleAssetService.save(new VehicleAsset(vehicleAssetRecordDto));
        return vehicleAssetMapper.apply(vehicle);
    }

    @Override
    public VehicleAssetRecordDto update(Long id, VehicleAssetRecordDto vehicleAssetRecordDto) {
        VehicleAsset vehicle = vehicleAssetService.update(id, new VehicleAsset(vehicleAssetRecordDto));
        return vehicleAssetMapper.apply(vehicle);
    }

    @Override
    public void deleteById(Long id) {
        vehicleAssetService.deleteById(id);
    }

    @Override
    public Optional<VehicleAssetRecordDto> findVehicleByRegNumber(String regNumber) {
        Optional<VehicleAsset> vehicleAsset = vehicleAssetService.findVehicleByRegNumber(regNumber);
        return vehicleAsset.map(vehicleAssetMapper);
    }
}
