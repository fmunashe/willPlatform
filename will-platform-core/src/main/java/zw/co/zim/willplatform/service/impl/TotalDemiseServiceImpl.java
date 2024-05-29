package zw.co.zim.willplatform.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import zw.co.zim.willplatform.utils.enums.RecordStatus;
import zw.co.zim.willplatform.model.Client;
import zw.co.zim.willplatform.model.TotalDemise;
import zw.co.zim.willplatform.repository.TotalDemiseRepository;
import zw.co.zim.willplatform.service.TotalDemiseService;
import zw.co.zim.willplatform.utils.PageableHelper;

import java.util.List;
import java.util.Optional;

@Service
public class TotalDemiseServiceImpl implements TotalDemiseService {
    private final TotalDemiseRepository repository;

    public TotalDemiseServiceImpl(TotalDemiseRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<TotalDemise> findAll() {
        return repository.findAllByRecordStatusNot(RecordStatus.DELETED);
    }

    @Override
    public Optional<TotalDemise> findById(Long id) {
        return repository.findFirstByIdAndRecordStatusNot(id, RecordStatus.DELETED);
    }

    @Override
    public TotalDemise save(TotalDemise totalDemise) {
        return repository.save(totalDemise);
    }

    @Override
    public TotalDemise update(Long id, TotalDemise totalDemise) {
        Optional<TotalDemise> optional = this.findById(id);
        if (optional.isPresent()) {
            TotalDemise demise = optional.get();
            demise.setBeneficiaryDetails(totalDemise.getBeneficiaryDetails());
            demise.setUserId(totalDemise.getUserId());
            demise.setRecordStatus(totalDemise.getRecordStatus());
            return repository.save(demise);
        }
        return totalDemise;
    }

    @Override
    public void deleteById(Long id) {
        Optional<TotalDemise> optional = this.findById(id);
        if (optional.isPresent()) {
            TotalDemise demise = optional.get();
            demise.setRecordStatus(RecordStatus.DELETED);
            repository.save(demise);
        }
    }

    @Override
    public Page<TotalDemise> findAll(Integer pageNo, Integer pageSize) {
        pageNo = PageableHelper.cleanPageNumber(pageNo);
        pageSize = PageableHelper.cleanPageSize(pageSize);
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.Direction.DESC, "id");
        return repository.findAllByRecordStatusNot(pageable, RecordStatus.DELETED);
    }

    @Override
    public Page<TotalDemise> findAllByUserId(Client userId, Integer pageNo, Integer pageSize) {
        pageNo = PageableHelper.cleanPageNumber(pageNo);
        pageSize = PageableHelper.cleanPageSize(pageSize);
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.Direction.DESC, "id");
        return repository.findAllByUserIdAndRecordStatusNot(pageable, userId, RecordStatus.DELETED);
    }
}
