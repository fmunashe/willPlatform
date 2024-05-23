package zw.co.zim.willplatform.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import zw.co.zim.willplatform.enums.RecordStatus;
import zw.co.zim.willplatform.model.Client;
import zw.co.zim.willplatform.model.WillBurialRequest;
import zw.co.zim.willplatform.repository.WillBurialRequestRepository;
import zw.co.zim.willplatform.service.WillBurialRequestService;
import zw.co.zim.willplatform.utils.PageableHelper;

import java.util.List;
import java.util.Optional;

@Service
public class WillBurialRequestServiceImpl implements WillBurialRequestService {
    private final WillBurialRequestRepository repository;

    public WillBurialRequestServiceImpl(WillBurialRequestRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<WillBurialRequest> findAll() {
        return repository.findAllByRecordStatusNot(RecordStatus.DELETED);
    }

    @Override
    public Optional<WillBurialRequest> findById(Long id) {
        return repository.findFirstByIdAndRecordStatusNot(id, RecordStatus.DELETED);
    }

    @Override
    public WillBurialRequest save(WillBurialRequest willBurialRequest) {
        return repository.save(willBurialRequest);
    }

    @Override
    public WillBurialRequest update(Long id, WillBurialRequest willBurialRequest) {
        Optional<WillBurialRequest> optional = this.findById(id);
        if (optional.isPresent()) {
            WillBurialRequest burialRequest = optional.get();
            burialRequest.setUserId(willBurialRequest.getUserId());
            burialRequest.setBurialType(willBurialRequest.getBurialType());
            burialRequest.setLivingWill(willBurialRequest.getLivingWill());
            burialRequest.setBurialInformation(willBurialRequest.getBurialInformation());
            burialRequest.setRecordStatus(willBurialRequest.getRecordStatus());
            return repository.save(burialRequest);
        }
        return willBurialRequest;
    }

    @Override
    public void deleteById(Long id) {
        Optional<WillBurialRequest> optional = this.findById(id);
        if (optional.isPresent()) {
            WillBurialRequest burialRequest = optional.get();
            burialRequest.setRecordStatus(RecordStatus.DELETED);
            repository.save(burialRequest);
        }
    }

    @Override
    public Page<WillBurialRequest> findAll(Integer pageNo, Integer pageSize) {
        pageNo = PageableHelper.cleanPageNumber(pageNo);
        pageSize = PageableHelper.cleanPageSize(pageSize);
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.Direction.DESC, "id");
        return repository.findAllByRecordStatusNot(pageable, RecordStatus.DELETED);
    }

    @Override
    public Optional<WillBurialRequest> findFirstByUserId(Client userId) {
        return repository.findFirstByUserIdAndRecordStatusNot(userId, RecordStatus.DELETED);
    }
}
