package zw.co.zim.willplatform.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import zw.co.zim.willplatform.utils.enums.RecordStatus;
import zw.co.zim.willplatform.model.AssetInvestment;
import zw.co.zim.willplatform.model.Client;

import java.util.List;
import java.util.Optional;

@Repository
public interface AssetInvestmentRepository extends JpaRepository<AssetInvestment, Long>, JpaSpecificationExecutor<AssetInvestment> {
    List<AssetInvestment> findAllByRecordStatusNot(RecordStatus recordStatus);
    Page<AssetInvestment> findAllByRecordStatusNot(Pageable pageable, RecordStatus recordStatus);

    Page<AssetInvestment> findAllByUserIdAndRecordStatusNot(Pageable pageable, Client clientId, RecordStatus recordStatus);

    Optional<AssetInvestment> findFirstByIdAndRecordStatusNot(Long id, RecordStatus recordStatus);
}
