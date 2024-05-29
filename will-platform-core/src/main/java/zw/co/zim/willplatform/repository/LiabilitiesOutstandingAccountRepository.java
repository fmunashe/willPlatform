package zw.co.zim.willplatform.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import zw.co.zim.willplatform.utils.enums.RecordStatus;
import zw.co.zim.willplatform.model.Client;
import zw.co.zim.willplatform.model.LiabilitiesOutstandingAccount;

import java.util.Optional;

public interface LiabilitiesOutstandingAccountRepository extends JpaRepository<LiabilitiesOutstandingAccount, Long> {
    Page<LiabilitiesOutstandingAccount> findAllByRecordStatusNot(Pageable pageable, RecordStatus recordStatus);

    Page<LiabilitiesOutstandingAccount> findAllByUserIdAndRecordStatusNot(Pageable pageable, Client clientId, RecordStatus recordStatus);

    Optional<LiabilitiesOutstandingAccount> findFirstByIdAndRecordStatusNot(Long id, RecordStatus recordStatus);
}
