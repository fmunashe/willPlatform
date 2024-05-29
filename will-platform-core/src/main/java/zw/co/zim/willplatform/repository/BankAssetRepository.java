package zw.co.zim.willplatform.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import zw.co.zim.willplatform.utils.enums.RecordStatus;
import zw.co.zim.willplatform.model.BankAsset;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import zw.co.zim.willplatform.model.Client;

import java.util.List;
import java.util.Optional;

@Repository
public interface BankAssetRepository extends JpaRepository<BankAsset, Long> {
    Optional<BankAsset> findFirstByAccountNumberAndRecordStatusNot(String accountNumber, RecordStatus recordStatus);
    List<BankAsset> findAllByRecordStatusNot(RecordStatus recordStatus);
    Page<BankAsset> findAllByRecordStatusNot(Pageable pageable, RecordStatus recordStatus);

    Page<BankAsset> findAllByUserIdAndRecordStatusNot(Pageable pageable, Client clientId, RecordStatus recordStatus);

    Optional<BankAsset> findFirstByIdAndRecordStatusNot(Long id, RecordStatus recordStatus);
}
