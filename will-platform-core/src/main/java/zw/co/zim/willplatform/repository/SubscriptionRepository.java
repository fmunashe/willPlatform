package zw.co.zim.willplatform.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import zw.co.zim.willplatform.enums.RecordStatus;
import zw.co.zim.willplatform.model.Client;
import zw.co.zim.willplatform.model.Subscription;

import java.util.List;
import java.util.Optional;

public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {
    List<Subscription> findAllByRecordStatusNot(RecordStatus recordStatus);

    Page<Subscription> findAllByRecordStatusNot(Pageable pageable, RecordStatus recordStatus);

    Optional<Subscription> findFirstByUserIdAndRecordStatusNot(Client userId, RecordStatus recordStatus);

    Optional<Subscription> findFirstByIdAndRecordStatusNot(Long id, RecordStatus recordStatus);
}
