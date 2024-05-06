package zw.co.zim.willplatform.repository;

import org.springframework.data.domain.Pageable;
import zw.co.zim.willplatform.enums.RecordStatus;
import zw.co.zim.willplatform.model.AssetInvestment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import zw.co.zim.willplatform.model.Client;
import zw.co.zim.willplatform.model.LiabilitiesUserOwesMoney;

import java.util.List;
import java.util.Optional;

@Repository
public interface AssetInvestmentRepository extends JpaRepository<AssetInvestment, Long> {
    List<AssetInvestment> findAllByRecordStatusNot(Pageable pageable, RecordStatus recordStatus);

    List<AssetInvestment> findAllByUserIdAndRecordStatusNot(Pageable pageable, Client clientId, RecordStatus recordStatus);

    Optional<AssetInvestment> findFirstByIdAndRecordStatusNot(Long id, RecordStatus recordStatus);
}
