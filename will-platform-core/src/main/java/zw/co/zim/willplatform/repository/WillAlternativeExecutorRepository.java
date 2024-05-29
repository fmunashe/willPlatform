package zw.co.zim.willplatform.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import zw.co.zim.willplatform.utils.enums.RecordStatus;
import zw.co.zim.willplatform.model.Client;
import zw.co.zim.willplatform.model.WillAlternativeExecutor;

import java.util.List;
import java.util.Optional;

public interface WillAlternativeExecutorRepository extends JpaRepository<WillAlternativeExecutor, Long> {
    List<WillAlternativeExecutor> findAllByRecordStatusNot(RecordStatus recordStatus);

    Page<WillAlternativeExecutor> findAllByRecordStatusNot(Pageable pageable, RecordStatus recordStatus);

    Optional<WillAlternativeExecutor> findFirstByUserIdAndRecordStatusNot(Client userId, RecordStatus recordStatus);

    Optional<WillAlternativeExecutor> findFirstByIdAndRecordStatusNot(Long id, RecordStatus recordStatus);
}
