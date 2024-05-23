package zw.co.zim.willplatform.dto.mapper;

import zw.co.zim.willplatform.dto.VehicleAssetRecordDto;
import zw.co.zim.willplatform.model.VehicleAsset;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class VehicleAssetDtoMapper implements Function<VehicleAsset, VehicleAssetRecordDto> {
    @Override
    public VehicleAssetRecordDto apply(VehicleAsset vehicleAsset) {
        return new VehicleAssetRecordDto(vehicleAsset.getId(), vehicleAsset.getMake(), vehicleAsset.getModel(), vehicleAsset.getColor(), vehicleAsset.getRegistrationNumber(), vehicleAsset.getEngineNumber(), vehicleAsset.getManufactureYear(), vehicleAsset.getVehicleValue(), vehicleAsset.getFullyPaid(), vehicleAsset.getRegistrationPaperWith(), vehicleAsset.getUserId(),vehicleAsset.getRecordStatus());
    }
}
