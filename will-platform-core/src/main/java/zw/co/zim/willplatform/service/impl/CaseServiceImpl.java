package zw.co.zim.willplatform.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import zw.co.zim.willplatform.model.Cases;
import zw.co.zim.willplatform.model.Client;
import zw.co.zim.willplatform.model.SystemUser;
import zw.co.zim.willplatform.repository.CasesRepository;
import zw.co.zim.willplatform.service.CaseService;
import zw.co.zim.willplatform.utils.PageableHelper;
import zw.co.zim.willplatform.utils.enums.CaseType;
import zw.co.zim.willplatform.utils.enums.RecordStatus;

import java.util.List;
import java.util.Optional;

@Service
public class CaseServiceImpl implements CaseService {
    private final CasesRepository repository;

    public CaseServiceImpl(CasesRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Cases> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Cases> findById(Long id) {
        return repository.findFirstByIdAndStatusNot(id, RecordStatus.DELETED);
    }

    @Override
    public Cases save(Cases cases) {
        return repository.save(cases);
    }

    @Override
    public Cases update(Long id, Cases cases) {
        Optional<Cases> optional = this.findById(id);
        if (optional.isPresent()) {
            Cases currentCase = optional.get();
            currentCase.setCaseType(cases.getCaseType());
            currentCase.setCaseNumber(cases.getCaseNumber());
            currentCase.setStatus(cases.getStatus());
            currentCase.setAssignedAgent(cases.getAssignedAgent());
            currentCase.setDescription(cases.getDescription());
            currentCase.setPriority(cases.getPriority());
            currentCase.setUserId(cases.getUserId());
            currentCase.setSubject(cases.getSubject());
            return repository.save(currentCase);
        }
        return cases;
    }

    @Override
    public void deleteById(Long id) {
        Optional<Cases> optional = this.findById(id);
        if (optional.isPresent()) {
            Cases currentCase = optional.get();
            currentCase.setStatus(RecordStatus.DELETED);
            repository.save(currentCase);
        }
    }

    @Override
    public Page<Cases> findAll(Integer pageNo, Integer pageSize) {
        pageNo = PageableHelper.cleanPageNumber(pageNo);
        pageSize = PageableHelper.cleanPageSize(pageSize);
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.Direction.DESC, "id");
        return repository.findAllByStatusNot(RecordStatus.DELETED, pageable);
    }

    @Override
    public Page<Cases> findAllByCaseType(CaseType caseType, Integer pageNo, Integer pageSize) {
        pageNo = PageableHelper.cleanPageNumber(pageNo);
        pageSize = PageableHelper.cleanPageSize(pageSize);
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.Direction.DESC, "id");
        return repository.findAllByCaseTypeAndStatusNot(caseType, RecordStatus.DELETED, pageable);
    }

    @Override
    public Page<Cases> findAllByAssignedAgent(SystemUser assignedAgent, Integer pageNo, Integer pageSize) {
        pageNo = PageableHelper.cleanPageNumber(pageNo);
        pageSize = PageableHelper.cleanPageSize(pageSize);
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.Direction.DESC, "id");
        return repository.findAllByAssignedAgentAndStatusNot(pageable, assignedAgent, RecordStatus.DELETED);
    }

    @Override
    public Page<Cases> findAllByClient(Client client, Integer pageNo, Integer pageSize) {
        pageNo = PageableHelper.cleanPageNumber(pageNo);
        pageSize = PageableHelper.cleanPageSize(pageSize);
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.Direction.DESC, "id");
        return repository.findAllByUserIdAndStatusNot(pageable, client, RecordStatus.DELETED);
    }

    @Override
    public Optional<Cases> findFirstByCaseNumber(String caseNumber) {
        return repository.findFirstByCaseNumberAndStatusNot(caseNumber, RecordStatus.DELETED);
    }

    @Override
    public Optional<Cases> findLatestCase() {
        return repository.findTopByOrderByIdDesc();
    }
}
