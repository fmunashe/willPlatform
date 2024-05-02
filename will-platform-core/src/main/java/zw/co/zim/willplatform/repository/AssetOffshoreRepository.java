package zw.co.zim.willplatform.repository;

import zw.co.zim.willplatform.model.AssetOffshore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssetOffshoreRepository extends JpaRepository<AssetOffshore, Long> {
}
