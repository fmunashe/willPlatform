package zw.co.zim.willplatform.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import zw.co.zim.willplatform.enums.ProductNames;
import zw.co.zim.willplatform.enums.RecordStatus;
import zw.co.zim.willplatform.model.Currency;
import zw.co.zim.willplatform.model.Products;
import zw.co.zim.willplatform.repository.ProductsRepository;
import zw.co.zim.willplatform.service.ProductsService;
import zw.co.zim.willplatform.utils.PageableHelper;

import java.util.List;
import java.util.Optional;

@Service
public class ProductsServiceImpl implements ProductsService {
    private final ProductsRepository repository;

    public ProductsServiceImpl(ProductsRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Products> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Products> findById(Long id) {
        return repository.findFirstByIdAndRecordStatusNot(id, RecordStatus.DELETED);
    }

    @Override
    public Products save(Products products) {
        return repository.save(products);
    }

    @Override
    public Products update(Long id, Products products) {
        Optional<Products> optional = this.findById(id);
        if (optional.isPresent()) {
            Products product = optional.get();
            product.setCurrency(products.getCurrency());
            product.setName(products.getName());
            product.setDescription(products.getDescription());
            product.setSubscription(products.getSubscription());
            product.setPrice(products.getPrice());
            product.setRecordStatus(products.getRecordStatus());
            return repository.save(product);
        }
        return products;
    }

    @Override
    public void deleteById(Long id) {
        Optional<Products> optional = this.findById(id);
        if (optional.isPresent()) {
            Products product = optional.get();
            product.setRecordStatus(RecordStatus.DELETED);
            repository.save(product);
        }
    }

    @Override
    public Page<Products> findAll(Integer pageNo, Integer pageSize) {
        pageNo = PageableHelper.cleanPageNumber(pageNo);
        pageSize = PageableHelper.cleanPageSize(pageSize);
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.Direction.DESC, "id");
        return repository.findAllByRecordStatusNot(pageable, RecordStatus.DELETED);
    }

    @Override
    public Page<Products> findAllByCurrency(Currency currency, Integer pageNo, Integer pageSize) {
        pageNo = PageableHelper.cleanPageNumber(pageNo);
        pageSize = PageableHelper.cleanPageSize(pageSize);
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.Direction.DESC, "id");
        return repository.findAllByCurrencyAndRecordStatusNot(pageable, currency, RecordStatus.DELETED);
    }

    @Override
    public Optional<Products> findProductByName(ProductNames name) {
        return repository.findFirstByNameAndRecordStatusNot(name, RecordStatus.DELETED);
    }
}
