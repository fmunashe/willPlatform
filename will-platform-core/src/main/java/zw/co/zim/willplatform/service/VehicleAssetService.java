package zw.co.zim.willplatform.service;

import org.springframework.data.domain.Page;
import zw.co.zim.willplatform.common.BaseService;
import zw.co.zim.willplatform.model.Client;
import zw.co.zim.willplatform.model.VehicleAsset;

import java.util.Optional;

public interface VehicleAssetService extends BaseService<VehicleAsset> {
    Optional<VehicleAsset> findVehicleByRegNumber(String regNumber);
    Page<VehicleAsset> findAll(Integer pageNo, Integer pageSize);
    Page<VehicleAsset> findAllByUserId(Client clientId, Integer pageNo, Integer pageSize);

    Optional<VehicleAsset> findVehicleByEngineNumber(String engineNumber);
}
