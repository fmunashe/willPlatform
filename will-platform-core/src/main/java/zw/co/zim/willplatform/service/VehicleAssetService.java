package zw.co.zim.willplatform.service;

import zw.co.zim.willplatform.common.AppService;
import zw.co.zim.willplatform.model.VehicleAsset;

import java.util.Optional;

public interface VehicleAssetService extends AppService<VehicleAsset> {
    Optional<VehicleAsset> findVehicleByRegNumber(String regNumber);
}
