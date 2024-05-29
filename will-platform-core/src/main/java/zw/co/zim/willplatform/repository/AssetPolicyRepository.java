package zw.co.zim.willplatform.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import zw.co.zim.willplatform.utils.enums.RecordStatus;
import zw.co.zim.willplatform.model.AssetPolicy;
import zw.co.zim.willplatform.model.Client;

import java.util.List;
import java.util.Optional;

@Repository
public interface AssetPolicyRepository extends JpaRepository<AssetPolicy, Long> {
    Optional<AssetPolicy> findFirstByPolicyNumberAndRecordStatusNot(String policyNumber, RecordStatus recordStatus);

    List<AssetPolicy> findAllByRecordStatusNot(RecordStatus recordStatus);

    Page<AssetPolicy> findAllByRecordStatusNot(Pageable pageable, RecordStatus recordStatus);

    Page<AssetPolicy> findAllByUserIdAndRecordStatusNot(Pageable pageable, Client clientId, RecordStatus recordStatus);

    Optional<AssetPolicy> findFirstByIdAndRecordStatusNot(Long id, RecordStatus recordStatus);
}
