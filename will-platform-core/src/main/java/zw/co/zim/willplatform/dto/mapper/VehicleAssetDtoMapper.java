package zw.co.zim.willplatform.dto.mapper;

import org.springframework.stereotype.Service;
import zw.co.zim.willplatform.dto.VehicleAssetRecordDto;
import zw.co.zim.willplatform.model.VehicleAsset;

import java.util.function.Function;

@Service
public class VehicleAssetDtoMapper implements Function<VehicleAsset, VehicleAssetRecordDto> {
    @Override
    public VehicleAssetRecordDto apply(VehicleAsset vehicleAsset) {
        return VehicleAssetRecordDto.builder()
            .id(vehicleAsset.getId())
            .user(vehicleAsset.getUserId())
            .color(vehicleAsset.getColor())
            .model(vehicleAsset.getModel())
            .make(vehicleAsset.getMake())
            .engineNumber(vehicleAsset.getEngineNumber())
            .registrationNumber(vehicleAsset.getRegistrationNumber())
            .registrationPaperWith(vehicleAsset.getRegistrationPaperWith())
            .manufactureYear(vehicleAsset.getManufactureYear())
            .value(vehicleAsset.getVehicleValue())
            .fullyPaid(vehicleAsset.getFullyPaid())
            .recordStatus(vehicleAsset.getRecordStatus())
            .createdAt(vehicleAsset.getCreatedAt())
            .updatedAt(vehicleAsset.getUpdatedAt())
            .build();
    }
}
