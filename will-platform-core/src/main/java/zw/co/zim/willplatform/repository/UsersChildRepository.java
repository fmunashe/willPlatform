package zw.co.zim.willplatform.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import zw.co.zim.willplatform.utils.enums.RecordStatus;
import zw.co.zim.willplatform.model.Client;
import zw.co.zim.willplatform.model.UsersChild;

import java.util.List;
import java.util.Optional;

public interface UsersChildRepository extends JpaRepository<UsersChild, Long> {
    List<UsersChild> findAllByRecordStatusNot(RecordStatus recordStatus);

    Page<UsersChild> findAllByRecordStatusNot(Pageable pageable, RecordStatus recordStatus);

    Page<UsersChild> findAllByUserIdAndRecordStatusNot(Pageable pageable, Client userId, RecordStatus recordStatus);

    Optional<UsersChild> findFirstByIdAndRecordStatusNot(Long id, RecordStatus recordStatus);
}
