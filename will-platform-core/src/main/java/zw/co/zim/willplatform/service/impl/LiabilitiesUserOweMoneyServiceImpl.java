package zw.co.zim.willplatform.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import zw.co.zim.willplatform.utils.enums.RecordStatus;
import zw.co.zim.willplatform.model.Client;
import zw.co.zim.willplatform.model.LiabilitiesUserOwesMoney;
import zw.co.zim.willplatform.repository.LiabilitiesUserOwesMoneyRepository;
import zw.co.zim.willplatform.service.LiabilitiesUserOweMoneyService;
import zw.co.zim.willplatform.utils.PageableHelper;

import java.util.List;
import java.util.Optional;

@Service
public class LiabilitiesUserOweMoneyServiceImpl implements LiabilitiesUserOweMoneyService {
    private final LiabilitiesUserOwesMoneyRepository repository;

    public LiabilitiesUserOweMoneyServiceImpl(LiabilitiesUserOwesMoneyRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<LiabilitiesUserOwesMoney> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<LiabilitiesUserOwesMoney> findById(Long id) {
        return repository.findFirstByIdAndRecordStatusNot(id, RecordStatus.DELETED);
    }

    @Override
    public LiabilitiesUserOwesMoney save(LiabilitiesUserOwesMoney liabilitiesUserOwesMoney) {
        return repository.save(liabilitiesUserOwesMoney);
    }

    @Override
    public LiabilitiesUserOwesMoney update(Long id, LiabilitiesUserOwesMoney liabilitiesUserOwesMoney) {
        Optional<LiabilitiesUserOwesMoney> optional = this.findById(id);
        if (optional.isPresent()) {
            LiabilitiesUserOwesMoney owesMoney = optional.get();
            owesMoney.setAmountOwed(liabilitiesUserOwesMoney.getAmountOwed());
            owesMoney.setUserId(liabilitiesUserOwesMoney.getUserId());
            owesMoney.setPersonDetails(liabilitiesUserOwesMoney.getPersonDetails());
            owesMoney.setPersonContactDetails(liabilitiesUserOwesMoney.getPersonContactDetails());
            owesMoney.setRecordStatus(RecordStatus.DELETED);
            repository.save(owesMoney);
        }
        return liabilitiesUserOwesMoney;
    }

    @Override
    public void deleteById(Long id) {
        Optional<LiabilitiesUserOwesMoney> optional = this.findById(id);
        if (optional.isPresent()) {
            LiabilitiesUserOwesMoney owesMoney = optional.get();
            owesMoney.setRecordStatus(RecordStatus.DELETED);
            repository.save(owesMoney);
        }
    }

    @Override
    public Page<LiabilitiesUserOwesMoney> findAll(Integer pageNo, Integer pageSize) {
        pageNo = PageableHelper.cleanPageNumber(pageNo);
        pageSize = PageableHelper.cleanPageSize(pageSize);
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.Direction.DESC, "id");
        return repository.findAllByRecordStatusNot(pageable, RecordStatus.DELETED);
    }

    @Override
    public Page<LiabilitiesUserOwesMoney> findAllByUserId(Client clientId, Integer pageNo, Integer pageSize) {
        pageNo = PageableHelper.cleanPageNumber(pageNo);
        pageSize = PageableHelper.cleanPageSize(pageSize);
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.Direction.DESC, "id");
        return repository.findAllByUserIdAndRecordStatusNot(pageable, clientId, RecordStatus.DELETED);
    }
}
