package zw.co.zim.willplatform.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import zw.co.zim.willplatform.enums.RecordStatus;
import zw.co.zim.willplatform.model.Client;
import zw.co.zim.willplatform.model.LiabilitiesUserOwesMoney;

import java.util.List;
import java.util.Optional;

public interface LiabilitiesUserOwesMoneyRepository extends JpaRepository<LiabilitiesUserOwesMoney,Long> {
    List<LiabilitiesUserOwesMoney> findAllByRecordStatusNot(Pageable pageable, RecordStatus recordStatus);

    List<LiabilitiesUserOwesMoney> findAllByUserIdAndRecordStatusNot(Pageable pageable, Client clientId, RecordStatus recordStatus);

    Optional<LiabilitiesUserOwesMoney> findFirstByIdAndRecordStatusNot(Long id, RecordStatus recordStatus);
}
