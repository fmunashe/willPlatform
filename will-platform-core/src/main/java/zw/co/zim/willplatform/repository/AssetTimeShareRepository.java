package zw.co.zim.willplatform.repository;

import zw.co.zim.willplatform.model.AssetTimeShare;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssetTimeShareRepository extends JpaRepository<AssetTimeShare, Long> {
}
