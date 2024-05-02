package zw.co.zim.willplatform.repository;

import zw.co.zim.willplatform.model.AssetTrust;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssetTrustRepository extends JpaRepository<AssetTrust, Long> {
}
