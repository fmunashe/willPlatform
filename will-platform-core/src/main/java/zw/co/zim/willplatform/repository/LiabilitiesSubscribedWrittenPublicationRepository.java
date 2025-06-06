package zw.co.zim.willplatform.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import zw.co.zim.willplatform.utils.enums.RecordStatus;
import zw.co.zim.willplatform.model.Client;
import zw.co.zim.willplatform.model.LiabilitiesSubscribedWrittenPublication;

import java.util.Optional;

public interface LiabilitiesSubscribedWrittenPublicationRepository extends JpaRepository<LiabilitiesSubscribedWrittenPublication, Long> {
    Page<LiabilitiesSubscribedWrittenPublication> findAllByRecordStatusNot(Pageable pageable, RecordStatus recordStatus);

    Page<LiabilitiesSubscribedWrittenPublication> findAllByUserIdAndRecordStatusNot(Pageable pageable, Client clientId, RecordStatus recordStatus);

    Optional<LiabilitiesSubscribedWrittenPublication> findFirstByIdAndRecordStatusNot(Long id, RecordStatus recordStatus);
}
