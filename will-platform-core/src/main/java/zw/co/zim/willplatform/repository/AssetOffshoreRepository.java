package zw.co.zim.willplatform.repository;

import org.springframework.data.domain.Pageable;
import zw.co.zim.willplatform.enums.RecordStatus;
import zw.co.zim.willplatform.model.AssetOffshore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import zw.co.zim.willplatform.model.Client;
import zw.co.zim.willplatform.model.LiabilitiesUserOwesMoney;

import java.util.List;
import java.util.Optional;

@Repository
public interface AssetOffshoreRepository extends JpaRepository<AssetOffshore, Long> {
    List<AssetOffshore> findAllByRecordStatusNot(Pageable pageable, RecordStatus recordStatus);

    List<AssetOffshore> findAllByUserIdAndRecordStatusNot(Pageable pageable, Client clientId, RecordStatus recordStatus);

    Optional<AssetOffshore> findFirstByIdAndRecordStatusNot(Long id, RecordStatus recordStatus);
}
