package zw.co.zim.willplatform.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import zw.co.zim.willplatform.utils.enums.RecordStatus;
import zw.co.zim.willplatform.model.Client;
import zw.co.zim.willplatform.model.LiabilitiesCreditCard;

import java.util.List;
import java.util.Optional;

public interface LiabilitiesCreditCardRepository extends JpaRepository<LiabilitiesCreditCard, Long> {
    Page<LiabilitiesCreditCard> findAllByRecordStatusNot(Pageable pageable, RecordStatus recordStatus);

    Page<LiabilitiesCreditCard> findAllByUserIdAndRecordStatusNot(Pageable pageable,Client clientId, RecordStatus recordStatus);

    Optional<LiabilitiesCreditCard> findFirstByIdAndRecordStatusNot(Long id, RecordStatus recordStatus);

}
