package zw.co.zim.willplatform.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import zw.co.zim.willplatform.utils.enums.RecordStatus;
import zw.co.zim.willplatform.model.Client;
import zw.co.zim.willplatform.model.PropertyAsset;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PropertyAssetRepository extends JpaRepository<PropertyAsset,Long> {
    List<PropertyAsset> findAllByRecordStatusNot( RecordStatus recordStatus);
    Page<PropertyAsset> findAllByRecordStatusNot(Pageable pageable, RecordStatus recordStatus);

    Page<PropertyAsset> findAllByUserIdAndRecordStatusNot(Pageable pageable, Client clientId, RecordStatus recordStatus);

    Optional<PropertyAsset> findFirstByIdAndRecordStatusNot(Long id, RecordStatus recordStatus);
}
