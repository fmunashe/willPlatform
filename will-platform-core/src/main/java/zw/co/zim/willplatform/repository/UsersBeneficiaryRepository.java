package zw.co.zim.willplatform.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import zw.co.zim.willplatform.utils.enums.RecordStatus;
import zw.co.zim.willplatform.model.Client;
import zw.co.zim.willplatform.model.UsersBeneficiary;

import java.util.List;
import java.util.Optional;

public interface UsersBeneficiaryRepository extends JpaRepository<UsersBeneficiary, Long> {
    List<UsersBeneficiary> findAllByRecordStatusNot(RecordStatus recordStatus);

    Page<UsersBeneficiary> findAllByRecordStatusNot(Pageable pageable, RecordStatus recordStatus);

    Page<UsersBeneficiary> findAllByUserIdAndRecordStatusNot(Pageable pageable, Client userId, RecordStatus recordStatus);

    Optional<UsersBeneficiary> findFirstByIdAndRecordStatusNot(Long id, RecordStatus recordStatus);
    Optional<UsersBeneficiary> findFirstByUserIdAndEmailAndRecordStatusNot(Client client,String email, RecordStatus recordStatus);
    Optional<UsersBeneficiary> findFirstByUserIdAndIdNumberAndRecordStatusNot(Client client,String nationalId, RecordStatus recordStatus);
}
