package zw.co.zim.willplatform.repository;

import zw.co.zim.willplatform.model.VehicleAsset;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VehicleAssetRepository extends JpaRepository<VehicleAsset,Long> {
    Optional<VehicleAsset> findFirstByRegistrationNumber(String regNumber);

    Optional<VehicleAsset> findFirstByEngineNumber(String engineNumber);
}
