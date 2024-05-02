package zw.co.zim.willplatform.repository;

import zw.co.zim.willplatform.model.AssetInvestment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssetInvestmentRepository extends JpaRepository<AssetInvestment, Long> {
}
