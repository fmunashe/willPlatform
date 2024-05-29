package zw.co.zim.willplatform.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import zw.co.zim.willplatform.utils.enums.RecordStatus;
import zw.co.zim.willplatform.model.Client;
import zw.co.zim.willplatform.model.SignupProgressJourney;

import java.util.List;
import java.util.Optional;

public interface SignupProgressJourneyRepository extends JpaRepository<SignupProgressJourney, Long> {
    List<SignupProgressJourney> findAllByRecordStatusNot(RecordStatus recordStatus);

    Page<SignupProgressJourney> findAllByRecordStatusNot(Pageable pageable, RecordStatus recordStatus);

    Optional<SignupProgressJourney> findFirstByUserIdAndRecordStatusNot(Client userId, RecordStatus recordStatus);

    Optional<SignupProgressJourney> findFirstByIdAndRecordStatusNot(Long id, RecordStatus recordStatus);
}
