package zw.co.zim.willplatform.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import zw.co.zim.willplatform.enums.ProductNames;
import zw.co.zim.willplatform.enums.RecordStatus;
import zw.co.zim.willplatform.model.Currency;
import zw.co.zim.willplatform.model.Products;

import java.util.List;
import java.util.Optional;

public interface ProductsRepository extends JpaRepository<Products, Long> {
    List<Products> findAllByRecordStatusNot(Pageable pageable, RecordStatus recordStatus);

    List<Products> findAllByCurrencyAndRecordStatusNot(Pageable pageable, Currency currency, RecordStatus recordStatus);

    Optional<Products> findFirstByIdAndRecordStatusNot(Long id, RecordStatus recordStatus);

    Optional<Products> findFirstByNameAndRecordStatusNot(ProductNames names, RecordStatus recordStatus);
}
