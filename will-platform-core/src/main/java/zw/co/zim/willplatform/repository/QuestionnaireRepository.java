package zw.co.zim.willplatform.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import zw.co.zim.willplatform.utils.enums.RecordStatus;
import zw.co.zim.willplatform.model.Client;
import zw.co.zim.willplatform.model.Questionnaire;

import java.util.List;
import java.util.Optional;

public interface QuestionnaireRepository extends JpaRepository<Questionnaire, Long> {
    List<Questionnaire> findAllByRecordStatusNot(RecordStatus recordStatus);

    Page<Questionnaire> findAllByRecordStatusNot(Pageable pageable, RecordStatus recordStatus);

    Optional<Questionnaire> findFirstByUserIdAndRecordStatusNot(Client userId, RecordStatus recordStatus);

    Optional<Questionnaire> findFirstByIdAndRecordStatusNot(Long id, RecordStatus recordStatus);
}
