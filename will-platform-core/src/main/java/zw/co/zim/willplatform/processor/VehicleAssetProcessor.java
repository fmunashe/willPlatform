package zw.co.zim.willplatform.processor;

import zw.co.zim.willplatform.common.AppService;
import zw.co.zim.willplatform.dto.VehicleAssetRecordDto;

import java.util.Optional;

public interface VehicleAssetProcessor extends AppService<VehicleAssetRecordDto> {
    Optional<VehicleAssetRecordDto> findVehicleByRegNumber(String regNumber);
}
