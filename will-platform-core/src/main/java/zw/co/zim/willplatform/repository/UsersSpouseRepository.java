package zw.co.zim.willplatform.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import zw.co.zim.willplatform.utils.enums.RecordStatus;
import zw.co.zim.willplatform.model.Client;
import zw.co.zim.willplatform.model.UsersSpouse;

import java.util.List;
import java.util.Optional;

public interface UsersSpouseRepository extends JpaRepository<UsersSpouse, Long> {
    List<UsersSpouse> findAllByRecordStatusNot(RecordStatus recordStatus);

    Page<UsersSpouse> findAllByRecordStatusNot(Pageable pageable, RecordStatus recordStatus);

    Page<UsersSpouse> findAllByUserIdAndRecordStatusNot(Pageable pageable, Client userId, RecordStatus recordStatus);

    Optional<UsersSpouse> findFirstByIdAndRecordStatusNot(Long id, RecordStatus recordStatus);
}
