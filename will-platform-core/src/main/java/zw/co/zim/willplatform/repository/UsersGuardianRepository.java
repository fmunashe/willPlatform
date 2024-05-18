package zw.co.zim.willplatform.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import zw.co.zim.willplatform.enums.RecordStatus;
import zw.co.zim.willplatform.model.Client;
import zw.co.zim.willplatform.model.UsersGuardian;

import java.util.List;
import java.util.Optional;

public interface UsersGuardianRepository extends JpaRepository<UsersGuardian, Long> {
    List<UsersGuardian> findAllByRecordStatusNot(RecordStatus recordStatus);

    Page<UsersGuardian> findAllByRecordStatusNot(Pageable pageable, RecordStatus recordStatus);

    Optional<UsersGuardian> findFirstByUserIdAndRecordStatusNot(Client userId, RecordStatus recordStatus);

    Optional<UsersGuardian> findFirstByIdAndRecordStatusNot(Long id, RecordStatus recordStatus);
}
