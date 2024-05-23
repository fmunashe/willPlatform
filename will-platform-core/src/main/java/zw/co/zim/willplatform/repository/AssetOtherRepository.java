package zw.co.zim.willplatform.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import zw.co.zim.willplatform.enums.RecordStatus;
import zw.co.zim.willplatform.model.AssetOther;
import zw.co.zim.willplatform.model.Client;

import java.util.List;
import java.util.Optional;

@Repository
public interface AssetOtherRepository extends JpaRepository<AssetOther, Long> {
    List<AssetOther> findAllByRecordStatusNot(RecordStatus recordStatus);

    Page<AssetOther> findAllByRecordStatusNot(Pageable pageable, RecordStatus recordStatus);

    Page<AssetOther> findAllByUserIdAndRecordStatusNot(Pageable pageable, Client clientId, RecordStatus recordStatus);

    Optional<AssetOther> findFirstByIdAndRecordStatusNot(Long id, RecordStatus recordStatus);
}
