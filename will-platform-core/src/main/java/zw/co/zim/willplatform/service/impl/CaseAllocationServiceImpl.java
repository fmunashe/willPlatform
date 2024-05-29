package zw.co.zim.willplatform.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import zw.co.zim.willplatform.utils.enums.CaseType;
import zw.co.zim.willplatform.utils.enums.RecordStatus;
import zw.co.zim.willplatform.model.CaseAllocation;
import zw.co.zim.willplatform.model.Cases;
import zw.co.zim.willplatform.repository.CaseAllocationRepository;
import zw.co.zim.willplatform.service.CaseAllocationService;
import zw.co.zim.willplatform.utils.PageableHelper;

import java.util.List;
import java.util.Optional;

@Service
public class CaseAllocationServiceImpl implements CaseAllocationService {
    private final CaseAllocationRepository repository;

    public CaseAllocationServiceImpl(CaseAllocationRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<CaseAllocation> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<CaseAllocation> findById(Long id) {
        return repository.findFirstByIdAndRecordStatusNot(id, RecordStatus.DELETED);
    }

    @Override
    public CaseAllocation save(CaseAllocation caseAllocation) {
        return repository.save(caseAllocation);
    }

    @Override
    public CaseAllocation update(Long id, CaseAllocation caseAllocation) {
        Optional<CaseAllocation> optional = this.findById(id);
        if (optional.isPresent()) {
            CaseAllocation allocation = optional.get();
            allocation.setRecordStatus(caseAllocation.getRecordStatus());
            allocation.setAllocationTime(caseAllocation.getAllocationTime());
            allocation.setCaseId(caseAllocation.getCaseId());
            allocation.setCaseType(caseAllocation.getCaseType());
            return repository.save(allocation);
        }
        return caseAllocation;
    }

    @Override
    public void deleteById(Long id) {
        Optional<CaseAllocation> optional = this.findById(id);
        if (optional.isPresent()) {
            CaseAllocation allocation = optional.get();
            allocation.setRecordStatus(RecordStatus.DELETED);
            repository.save(allocation);
        }
    }

    @Override
    public Page<CaseAllocation> findAll(Integer pageNo, Integer pageSize) {
        pageNo = PageableHelper.cleanPageNumber(pageNo);
        pageSize = PageableHelper.cleanPageSize(pageSize);
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.Direction.DESC, "id");
        return repository.findAllByRecordStatusNot(pageable, RecordStatus.DELETED);
    }

    @Override
    public Page<CaseAllocation> findAllByCaseType(CaseType caseType, Integer pageNo, Integer pageSize) {
        pageNo = PageableHelper.cleanPageNumber(pageNo);
        pageSize = PageableHelper.cleanPageSize(pageSize);
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.Direction.DESC, "id");
        return repository.findAllByCaseTypeAndRecordStatusNot(caseType, pageable, RecordStatus.DELETED);
    }

    @Override
    public Optional<CaseAllocation> findFirstByCaseId(Cases caseId) {
        return repository.findFirstByCaseIdAndRecordStatusNot(caseId, RecordStatus.DELETED);
    }
}
