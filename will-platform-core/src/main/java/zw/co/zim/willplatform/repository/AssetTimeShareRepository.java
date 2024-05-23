package zw.co.zim.willplatform.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import zw.co.zim.willplatform.enums.RecordStatus;
import zw.co.zim.willplatform.model.AssetTimeShare;
import zw.co.zim.willplatform.model.Client;

import java.util.List;
import java.util.Optional;

@Repository
public interface AssetTimeShareRepository extends JpaRepository<AssetTimeShare, Long> {
    List<AssetTimeShare> findAllByRecordStatusNot(RecordStatus recordStatus);

    Page<AssetTimeShare> findAllByRecordStatusNot(Pageable pageable, RecordStatus recordStatus);

    Page<AssetTimeShare> findAllByUserIdAndRecordStatusNot(Pageable pageable, Client clientId, RecordStatus recordStatus);

    Optional<AssetTimeShare> findFirstByIdAndRecordStatusNot(Long id, RecordStatus recordStatus);
}
