package zw.co.zim.willplatform.repository;

import zw.co.zim.willplatform.model.BankAsset;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BankAssetRepository extends JpaRepository<BankAsset, Long> {
    Optional<BankAsset> findFirstByAccountNumber(String accountNumber);
}
