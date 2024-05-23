package zw.co.zim.willplatform.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import zw.co.zim.willplatform.enums.RecordStatus;
import zw.co.zim.willplatform.model.Client;
import zw.co.zim.willplatform.model.LiabilitiesOutstandingAccount;
import zw.co.zim.willplatform.repository.LiabilitiesOutstandingAccountRepository;
import zw.co.zim.willplatform.service.LiabilitiesOutstandingAccountService;
import zw.co.zim.willplatform.utils.PageableHelper;

import java.util.List;
import java.util.Optional;

@Service
public class LiabilitiesOutstandingAccountServiceImpl implements LiabilitiesOutstandingAccountService {
    private final LiabilitiesOutstandingAccountRepository repository;

    public LiabilitiesOutstandingAccountServiceImpl(LiabilitiesOutstandingAccountRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<LiabilitiesOutstandingAccount> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<LiabilitiesOutstandingAccount> findById(Long id) {
        return repository.findFirstByIdAndRecordStatusNot(id, RecordStatus.DELETED);
    }

    @Override
    public LiabilitiesOutstandingAccount save(LiabilitiesOutstandingAccount liabilitiesOutstandingAccount) {
        return repository.save(liabilitiesOutstandingAccount);
    }

    @Override
    public LiabilitiesOutstandingAccount update(Long id, LiabilitiesOutstandingAccount liabilitiesOutstandingAccount) {
        Optional<LiabilitiesOutstandingAccount> optional = this.findById(id);
        if (optional.isPresent()) {
            LiabilitiesOutstandingAccount account = optional.get();
            account.setNameOfAccount(liabilitiesOutstandingAccount.getNameOfAccount());
            account.setAccountValue(liabilitiesOutstandingAccount.getAccountValue());
            account.setUserId(liabilitiesOutstandingAccount.getUserId());
            account.setRecordStatus(liabilitiesOutstandingAccount.getRecordStatus());
            repository.save(account);
        }
        return liabilitiesOutstandingAccount;
    }

    @Override
    public void deleteById(Long id) {
        Optional<LiabilitiesOutstandingAccount> optional = this.findById(id);
        if (optional.isPresent()) {
            LiabilitiesOutstandingAccount account = optional.get();
            account.setRecordStatus(RecordStatus.DELETED);
            repository.save(account);
        }
    }

    @Override
    public Page<LiabilitiesOutstandingAccount> findAll(Integer pageNo, Integer pageSize) {
        pageNo = PageableHelper.cleanPageNumber(pageNo);
        pageSize = PageableHelper.cleanPageSize(pageSize);
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.Direction.DESC, "id");
        return repository.findAllByRecordStatusNot(pageable, RecordStatus.DELETED);
    }

    @Override
    public Page<LiabilitiesOutstandingAccount> findAllByUserId(Client clientId, Integer pageNo, Integer pageSize) {
        pageNo = PageableHelper.cleanPageNumber(pageNo);
        pageSize = PageableHelper.cleanPageSize(pageSize);
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.Direction.DESC, "id");
        return repository.findAllByUserIdAndRecordStatusNot(pageable, clientId, RecordStatus.DELETED);
    }
}
