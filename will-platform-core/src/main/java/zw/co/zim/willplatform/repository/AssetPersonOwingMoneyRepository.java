package zw.co.zim.willplatform.repository;

import zw.co.zim.willplatform.model.AssetPersonOwingMoney;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssetPersonOwingMoneyRepository extends JpaRepository<AssetPersonOwingMoney, Long> {
}
