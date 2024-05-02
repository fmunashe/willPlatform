package zw.co.zim.willplatform.repository;

import zw.co.zim.willplatform.model.AssetPolicy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AssetPolicyRepository extends JpaRepository<AssetPolicy, Long> {
    Optional<AssetPolicy> findFirstByPolicyNumber(String policyNumber);
}
