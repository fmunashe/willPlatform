package zw.co.zim.willplatform.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import zw.co.zim.willplatform.utils.enums.RecordStatus;
import zw.co.zim.willplatform.model.Client;
import zw.co.zim.willplatform.model.LiabilitiesUserOwesMoney;

import java.util.Optional;

public interface LiabilitiesUserOwesMoneyRepository extends JpaRepository<LiabilitiesUserOwesMoney,Long> {
    Page<LiabilitiesUserOwesMoney> findAllByRecordStatusNot(Pageable pageable, RecordStatus recordStatus);

    Page<LiabilitiesUserOwesMoney> findAllByUserIdAndRecordStatusNot(Pageable pageable, Client clientId, RecordStatus recordStatus);

    Optional<LiabilitiesUserOwesMoney> findFirstByIdAndRecordStatusNot(Long id, RecordStatus recordStatus);
}
