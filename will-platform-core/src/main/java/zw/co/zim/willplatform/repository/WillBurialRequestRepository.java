package zw.co.zim.willplatform.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import zw.co.zim.willplatform.utils.enums.RecordStatus;
import zw.co.zim.willplatform.model.Client;
import zw.co.zim.willplatform.model.WillBurialRequest;

import java.util.List;
import java.util.Optional;

public interface WillBurialRequestRepository extends JpaRepository<WillBurialRequest, Long> {
    List<WillBurialRequest> findAllByRecordStatusNot(RecordStatus recordStatus);

    Page<WillBurialRequest> findAllByRecordStatusNot(Pageable pageable, RecordStatus recordStatus);

    Optional<WillBurialRequest> findFirstByUserIdAndRecordStatusNot(Client userId, RecordStatus recordStatus);

    Optional<WillBurialRequest> findFirstByIdAndRecordStatusNot(Long id, RecordStatus recordStatus);
}
