package zw.co.zim.willplatform.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import zw.co.zim.willplatform.enums.RecordStatus;
import zw.co.zim.willplatform.model.Client;
import zw.co.zim.willplatform.model.SpecialBequest;

import java.util.List;
import java.util.Optional;

public interface SpecialBequestRepository extends JpaRepository<SpecialBequest, Long> {
    List<SpecialBequest> findAllByRecordStatusNot(RecordStatus recordStatus);

    Page<SpecialBequest> findAllByRecordStatusNot(Pageable pageable, RecordStatus recordStatus);

    Optional<SpecialBequest> findFirstByUserIdAndRecordStatusNot(Client userId, RecordStatus recordStatus);

    Optional<SpecialBequest> findFirstByIdAndRecordStatusNot(Long id, RecordStatus recordStatus);
}
