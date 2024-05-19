package zw.co.zim.willplatform.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import zw.co.zim.willplatform.enums.RecordStatus;
import zw.co.zim.willplatform.model.CaseUpdate;
import zw.co.zim.willplatform.model.Cases;
import zw.co.zim.willplatform.repository.CaseUpdateRepository;
import zw.co.zim.willplatform.service.CaseUpdatesService;
import zw.co.zim.willplatform.utils.PageableHelper;

import java.util.List;
import java.util.Optional;

@Service
public class CaseUpdateServiceImpl implements CaseUpdatesService {
    private final CaseUpdateRepository repository;

    public CaseUpdateServiceImpl(CaseUpdateRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<CaseUpdate> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<CaseUpdate> findById(Long id) {
        return repository.findFirstByIdAndStatusNot(id, RecordStatus.DELETED);
    }

    @Override
    public CaseUpdate save(CaseUpdate caseUpdate) {
        return repository.save(caseUpdate);
    }

    @Override
    public CaseUpdate update(Long id, CaseUpdate caseUpdate) {
        Optional<CaseUpdate> optional = this.findById(id);
        if (optional.isPresent()) {
            CaseUpdate currentUpdate = optional.get();
            currentUpdate.setCaseId(caseUpdate.getCaseId());
            currentUpdate.setName(caseUpdate.getName());
            currentUpdate.setDescription(caseUpdate.getDescription());
            currentUpdate.setCreatedBy(caseUpdate.getCreatedBy());
            currentUpdate.setStatus(caseUpdate.getStatus());
            return repository.save(caseUpdate);
        }
        return caseUpdate;
    }

    @Override
    public void deleteById(Long id) {
        Optional<CaseUpdate> optional = this.findById(id);
        if (optional.isPresent()) {
            CaseUpdate currentUpdate = optional.get();
            currentUpdate.setStatus(RecordStatus.DELETED);
            repository.save(currentUpdate);
        }
    }

    @Override
    public Page<CaseUpdate> findAll(Integer pageNo, Integer pageSize) {
        pageNo = PageableHelper.cleanPageNumber(pageNo);
        pageSize = PageableHelper.cleanPageSize(pageSize);
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.Direction.DESC, "id");
        return repository.findAllByStatusNot(pageable, RecordStatus.DELETED);
    }

    @Override
    public Page<CaseUpdate> findAllByCaseId(Cases caseId, Integer pageNo, Integer pageSize) {
        pageNo = PageableHelper.cleanPageNumber(pageNo);
        pageSize = PageableHelper.cleanPageSize(pageSize);
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.Direction.DESC, "id");
        return repository.findAllByCaseIdAndStatusNot(pageable, caseId, RecordStatus.DELETED);
    }

    @Override
    public Optional<CaseUpdate> findFirstById(Long caseId) {
        return repository.findFirstByIdAndStatusNot(caseId, RecordStatus.DELETED);
    }
}
