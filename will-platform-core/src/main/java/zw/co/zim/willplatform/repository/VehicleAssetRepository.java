package zw.co.zim.willplatform.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import zw.co.zim.willplatform.enums.RecordStatus;
import zw.co.zim.willplatform.model.VehicleAsset;

import java.util.List;
import java.util.Optional;

@Repository
public interface VehicleAssetRepository extends JpaRepository<VehicleAsset, Long> {
    List<VehicleAsset> findAllByRecordStatusNot(RecordStatus recordStatus);

    Page<VehicleAsset> findAllByRecordStatusNot(Pageable pageable, RecordStatus recordStatus);

    Optional<VehicleAsset> findFirstByRegistrationNumberAndRecordStatusNot(String regNumber, RecordStatus recordStatus);

    Optional<VehicleAsset> findFirstByEngineNumberAndRecordStatusNot(String engineNumber, RecordStatus recordStatus);
    Optional<VehicleAsset> findFirstByIdAndRecordStatusNot(Long id, RecordStatus recordStatus);
}
