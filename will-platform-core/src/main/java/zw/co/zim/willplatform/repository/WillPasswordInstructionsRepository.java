package zw.co.zim.willplatform.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import zw.co.zim.willplatform.enums.RecordStatus;
import zw.co.zim.willplatform.model.Client;
import zw.co.zim.willplatform.model.WillPasswordsInstructions;

import java.util.List;
import java.util.Optional;

public interface WillPasswordInstructionsRepository extends JpaRepository<WillPasswordsInstructions, Long> {
    List<WillPasswordsInstructions> findAllByRecordStatusNot(RecordStatus recordStatus);

    Page<WillPasswordsInstructions> findAllByRecordStatusNot(Pageable pageable, RecordStatus recordStatus);

    Page<WillPasswordsInstructions> findAllByUserIdAndRecordStatusNot(Pageable pageable, Client userId, RecordStatus recordStatus);

    Optional<WillPasswordsInstructions> findFirstByIdAndRecordStatusNot(Long id, RecordStatus recordStatus);
}
