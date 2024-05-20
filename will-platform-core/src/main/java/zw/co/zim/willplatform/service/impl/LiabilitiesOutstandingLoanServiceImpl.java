package zw.co.zim.willplatform.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import zw.co.zim.willplatform.enums.RecordStatus;
import zw.co.zim.willplatform.model.Client;
import zw.co.zim.willplatform.model.LiabilitiesOutstandingLoan;
import zw.co.zim.willplatform.repository.LiabilitiesOutstandingLoanRepository;
import zw.co.zim.willplatform.service.LiabilitiesOutstandingLoanService;
import zw.co.zim.willplatform.utils.PageableHelper;

import java.util.List;
import java.util.Optional;

@Service
public class LiabilitiesOutstandingLoanServiceImpl implements LiabilitiesOutstandingLoanService {
    private final LiabilitiesOutstandingLoanRepository repository;

    public LiabilitiesOutstandingLoanServiceImpl(LiabilitiesOutstandingLoanRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<LiabilitiesOutstandingLoan> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<LiabilitiesOutstandingLoan> findById(Long id) {
        return repository.findFirstByIdAndRecordStatusNot(id, RecordStatus.DELETED);
    }

    @Override
    public LiabilitiesOutstandingLoan save(LiabilitiesOutstandingLoan liabilitiesOutstandingLoan) {
        return repository.save(liabilitiesOutstandingLoan);
    }

    @Override
    public LiabilitiesOutstandingLoan update(Long id, LiabilitiesOutstandingLoan liabilitiesOutstandingLoan) {
        Optional<LiabilitiesOutstandingLoan> optional = this.findById(id);
        if (optional.isPresent()) {
            LiabilitiesOutstandingLoan outstandingLoan = optional.get();
            outstandingLoan.setAmountOwed(liabilitiesOutstandingLoan.getAmountOwed());
            outstandingLoan.setNameOfCreditor(liabilitiesOutstandingLoan.getNameOfCreditor());
            outstandingLoan.setUserId(liabilitiesOutstandingLoan.getUserId());
            outstandingLoan.setRecordStatus(liabilitiesOutstandingLoan.getRecordStatus());
            return repository.save(outstandingLoan);
        }
        return liabilitiesOutstandingLoan;
    }

    @Override
    public void deleteById(Long id) {
        Optional<LiabilitiesOutstandingLoan> optional = this.findById(id);
        if (optional.isPresent()) {
            LiabilitiesOutstandingLoan outstandingLoan = optional.get();
            outstandingLoan.setRecordStatus(RecordStatus.DELETED);
            repository.save(outstandingLoan);
        }
    }

    @Override
    public Page<LiabilitiesOutstandingLoan> findAll(Integer pageNo, Integer pageSize) {
        pageNo = PageableHelper.cleanPageNumber(pageNo);
        pageSize = PageableHelper.cleanPageSize(pageSize);
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.Direction.DESC, "id");
        return repository.findAllByRecordStatusNot(pageable, RecordStatus.DELETED);
    }

    @Override
    public Page<LiabilitiesOutstandingLoan> findAllByUserId(Client clientId, Integer pageNo, Integer pageSize) {
        pageNo = PageableHelper.cleanPageNumber(pageNo);
        pageSize = PageableHelper.cleanPageSize(pageSize);
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.Direction.DESC, "id");
        return repository.findAllByUserIdAndRecordStatusNot(pageable, clientId, RecordStatus.DELETED);
    }
}
