package zw.co.zim.willplatform.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import zw.co.zim.willplatform.enums.RecordStatus;
import zw.co.zim.willplatform.model.Currency;

import java.util.List;
import java.util.Optional;

public interface CurrencyRepository extends JpaRepository<Currency, Long> {
    Page<Currency> findAllByRecordStatusNot(Pageable pageable, RecordStatus recordStatus);

    Optional<Currency> findFirstByNameAndRecordStatusNot(String name, RecordStatus recordStatus);
    Optional<Currency> findFirstByIdAndRecordStatusNot(Long id, RecordStatus recordStatus);
}
