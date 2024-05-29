package zw.co.zim.willplatform.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import zw.co.zim.willplatform.utils.enums.RecordStatus;
import zw.co.zim.willplatform.model.Client;

import java.util.List;
import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client, Long> {
    List<Client> findAllByRecordStatusNot(RecordStatus recordStatus);
    Page<Client> findAllByRecordStatusNot(Pageable pageable, RecordStatus recordStatus);

    Optional<Client> findFirstByEmailOrNationalIdNumberAndRecordStatusNot(String email, String nationalId, RecordStatus recordStatus);

    Optional<Client> findFirstByIdAndRecordStatusNot(Long id, RecordStatus recordStatus);

    public Optional<Client> findFirstByNationalIdNumberAndRecordStatusNot(String nationalId, RecordStatus recordStatus);

    public Optional<Client> findFirstByPassportNumberAndRecordStatusNot(String passportNumber, RecordStatus recordStatus);
}
