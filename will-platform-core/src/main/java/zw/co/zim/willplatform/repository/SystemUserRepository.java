package zw.co.zim.willplatform.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import zw.co.zim.willplatform.utils.enums.RecordStatus;
import zw.co.zim.willplatform.utils.enums.RoleEnum;
import zw.co.zim.willplatform.model.SystemUser;

import java.util.List;
import java.util.Optional;

public interface SystemUserRepository extends JpaRepository<SystemUser, Long> {
    List<SystemUser> findAllByRecordStatusNot(RecordStatus recordStatus);

    Page<SystemUser> findAllByRecordStatusNot(Pageable pageable, RecordStatus recordStatus);

    Page<SystemUser> findAllByRoleAndRecordStatusNot(Pageable pageable, RoleEnum role, RecordStatus recordStatus);

    Optional<SystemUser> findFirstByIdAndRecordStatusNot(Long id, RecordStatus recordStatus);

    Optional<SystemUser> findFirstByEmailAndRecordStatusNot(String email, RecordStatus recordStatus);
}
