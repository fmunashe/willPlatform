package zw.co.zim.willplatform.service;

import org.springframework.data.domain.Page;
import zw.co.zim.willplatform.common.AppService;
import zw.co.zim.willplatform.model.VehicleAsset;

import java.util.Optional;

public interface VehicleAssetService extends AppService<VehicleAsset> {
    Optional<VehicleAsset> findVehicleByRegNumber(String regNumber);
    Page<VehicleAsset> findAll(Integer pageNo, Integer pageSize);

    Optional<VehicleAsset> findVehicleByEngineNumber(String engineNumber);
}
