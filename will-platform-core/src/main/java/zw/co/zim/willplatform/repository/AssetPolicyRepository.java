package zw.co.zim.willplatform.repository;

import org.springframework.data.domain.Pageable;
import zw.co.zim.willplatform.enums.RecordStatus;
import zw.co.zim.willplatform.model.AssetPolicy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import zw.co.zim.willplatform.model.Client;
import zw.co.zim.willplatform.model.LiabilitiesUserOwesMoney;

import java.util.List;
import java.util.Optional;

@Repository
public interface AssetPolicyRepository extends JpaRepository<AssetPolicy, Long> {
    Optional<AssetPolicy> findFirstByPolicyNumberAndRecordStatusNot(String policyNumber,RecordStatus recordStatus);
    List<AssetPolicy> findAllByRecordStatusNot(Pageable pageable, RecordStatus recordStatus);

    List<AssetPolicy> findAllByUserIdAndRecordStatusNot(Pageable pageable, Client clientId, RecordStatus recordStatus);

    Optional<AssetPolicy> findFirstByIdAndRecordStatusNot(Long id, RecordStatus recordStatus);
}
