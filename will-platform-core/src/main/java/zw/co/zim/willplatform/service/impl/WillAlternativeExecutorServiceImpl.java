package zw.co.zim.willplatform.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import zw.co.zim.willplatform.utils.enums.RecordStatus;
import zw.co.zim.willplatform.model.Client;
import zw.co.zim.willplatform.model.WillAlternativeExecutor;
import zw.co.zim.willplatform.repository.WillAlternativeExecutorRepository;
import zw.co.zim.willplatform.service.WillAlternativeExecutorService;
import zw.co.zim.willplatform.utils.PageableHelper;

import java.util.List;
import java.util.Optional;

@Service
public class WillAlternativeExecutorServiceImpl implements WillAlternativeExecutorService {
    private final WillAlternativeExecutorRepository repository;

    public WillAlternativeExecutorServiceImpl(WillAlternativeExecutorRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<WillAlternativeExecutor> findAll() {
        return repository.findAllByRecordStatusNot(RecordStatus.DELETED);
    }

    @Override
    public Optional<WillAlternativeExecutor> findById(Long id) {
        return repository.findFirstByIdAndRecordStatusNot(id, RecordStatus.DELETED);
    }

    @Override
    public WillAlternativeExecutor save(WillAlternativeExecutor willAlternativeExecutor) {
        return repository.save(willAlternativeExecutor);
    }

    @Override
    public WillAlternativeExecutor update(Long id, WillAlternativeExecutor willAlternativeExecutor) {
        Optional<WillAlternativeExecutor> optional = this.findById(id);
        if (optional.isPresent()) {
            WillAlternativeExecutor executor = optional.get();
            executor.setExecutorDetails(willAlternativeExecutor.getExecutorDetails());
            executor.setEmail(willAlternativeExecutor.getEmail());
            executor.setContactNumber(willAlternativeExecutor.getContactNumber());
            executor.setUserId(willAlternativeExecutor.getUserId());
            executor.setAddress(willAlternativeExecutor.getAddress());
            executor.setRecordStatus(willAlternativeExecutor.getRecordStatus());
            return repository.save(executor);
        }
        return willAlternativeExecutor;
    }

    @Override
    public void deleteById(Long id) {
        Optional<WillAlternativeExecutor> optional = this.findById(id);
        if (optional.isPresent()) {
            WillAlternativeExecutor executor = optional.get();
            executor.setRecordStatus(RecordStatus.DELETED);
            repository.save(executor);
        }
    }

    @Override
    public Page<WillAlternativeExecutor> findAll(Integer pageNo, Integer pageSize) {
        pageNo = PageableHelper.cleanPageNumber(pageNo);
        pageSize = PageableHelper.cleanPageSize(pageSize);
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.Direction.DESC, "id");
        return repository.findAllByRecordStatusNot(pageable, RecordStatus.DELETED);
    }

    @Override
    public Optional<WillAlternativeExecutor> findFirstByUserId(Client userId, Integer pageNo, Integer pageSize) {
        return repository.findFirstByUserIdAndRecordStatusNot(userId, RecordStatus.DELETED);
    }
}
