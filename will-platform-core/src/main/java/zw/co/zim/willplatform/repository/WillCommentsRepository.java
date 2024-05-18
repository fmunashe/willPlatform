package zw.co.zim.willplatform.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import zw.co.zim.willplatform.enums.RecordStatus;
import zw.co.zim.willplatform.model.Client;
import zw.co.zim.willplatform.model.WillComments;

import java.util.List;
import java.util.Optional;

public interface WillCommentsRepository extends JpaRepository<WillComments, Long> {
    List<WillComments> findAllByRecordStatusNot(RecordStatus recordStatus);

    Page<WillComments> findAllByRecordStatusNot(Pageable pageable, RecordStatus recordStatus);

    List<WillComments> findAllByUserIdAndRecordStatusNot(Client userId, RecordStatus recordStatus);

    Optional<WillComments> findFirstByIdAndRecordStatusNot(Long id, RecordStatus recordStatus);
}
