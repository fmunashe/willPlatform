package zw.co.zim.willplatform.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import zw.co.zim.willplatform.utils.enums.RecordStatus;
import zw.co.zim.willplatform.model.AssetPersonOwingMoney;
import zw.co.zim.willplatform.model.Client;

import java.util.List;
import java.util.Optional;

@Repository
public interface AssetPersonOwingMoneyRepository extends JpaRepository<AssetPersonOwingMoney, Long> {
    List<AssetPersonOwingMoney> findAllByRecordStatusNot(RecordStatus recordStatus);

    Page<AssetPersonOwingMoney> findAllByRecordStatusNot(Pageable pageable, RecordStatus recordStatus);

    Page<AssetPersonOwingMoney> findAllByUserIdAndRecordStatusNot(Pageable pageable, Client clientId, RecordStatus recordStatus);

    Optional<AssetPersonOwingMoney> findFirstByIdAndRecordStatusNot(Long id, RecordStatus recordStatus);
}
