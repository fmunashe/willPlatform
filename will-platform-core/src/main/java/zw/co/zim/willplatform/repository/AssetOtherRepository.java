package zw.co.zim.willplatform.repository;

import zw.co.zim.willplatform.model.AssetOther;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssetOtherRepository extends JpaRepository<AssetOther, Long> {
}
