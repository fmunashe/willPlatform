package zw.co.zim.willplatform.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import zw.co.zim.willplatform.utils.enums.RecordStatus;
import zw.co.zim.willplatform.model.Client;
import zw.co.zim.willplatform.model.TotalDemise;

import java.util.List;
import java.util.Optional;

public interface TotalDemiseRepository extends JpaRepository<TotalDemise, Long> {
    List<TotalDemise> findAllByRecordStatusNot(RecordStatus recordStatus);

    Page<TotalDemise> findAllByRecordStatusNot(Pageable pageable, RecordStatus recordStatus);

    Page<TotalDemise> findAllByUserIdAndRecordStatusNot(Pageable pageable, Client userId, RecordStatus recordStatus);

    Optional<TotalDemise> findFirstByIdAndRecordStatusNot(Long id, RecordStatus recordStatus);
}
