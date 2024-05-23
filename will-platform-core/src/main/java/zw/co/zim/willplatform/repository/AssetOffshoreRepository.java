package zw.co.zim.willplatform.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import zw.co.zim.willplatform.enums.RecordStatus;
import zw.co.zim.willplatform.model.AssetOffshore;
import zw.co.zim.willplatform.model.Client;

import java.util.List;
import java.util.Optional;

@Repository
public interface AssetOffshoreRepository extends JpaRepository<AssetOffshore, Long> {
    List<AssetOffshore> findAllByRecordStatusNot(RecordStatus recordStatus);

    Page<AssetOffshore> findAllByRecordStatusNot(Pageable pageable, RecordStatus recordStatus);

    Page<AssetOffshore> findAllByUserIdAndRecordStatusNot(Pageable pageable, Client clientId, RecordStatus recordStatus);

    Optional<AssetOffshore> findFirstByIdAndRecordStatusNot(Long id, RecordStatus recordStatus);
}
