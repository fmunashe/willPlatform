package zw.co.zim.willplatform.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import zw.co.zim.willplatform.enums.RecordStatus;
import zw.co.zim.willplatform.model.AssetTrust;
import zw.co.zim.willplatform.model.Client;

import java.util.List;
import java.util.Optional;

@Repository
public interface AssetTrustRepository extends JpaRepository<AssetTrust, Long> {
    List<AssetTrust> findAllByRecordStatusNot(RecordStatus recordStatus);

    Page<AssetTrust> findAllByRecordStatusNot(Pageable pageable, RecordStatus recordStatus);

    Page<AssetTrust> findAllByUserIdAndRecordStatusNot(Pageable pageable, Client clientId, RecordStatus recordStatus);

    Optional<AssetTrust> findFirstByIdAndRecordStatusNot(Long id, RecordStatus recordStatus);
}
