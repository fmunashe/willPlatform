package zw.co.zim.willplatform.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import zw.co.zim.willplatform.utils.enums.RecordStatus;
import zw.co.zim.willplatform.model.TransferFees;
import zw.co.zim.willplatform.repository.TransferFeesRepository;
import zw.co.zim.willplatform.service.TransferFeesService;
import zw.co.zim.willplatform.utils.PageableHelper;

import java.util.List;
import java.util.Optional;

@Service
public class TransferFeesServiceImpl implements TransferFeesService {
    private final TransferFeesRepository repository;

    public TransferFeesServiceImpl(TransferFeesRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<TransferFees> findAll() {
        return repository.findAllByRecordStatusNot(RecordStatus.DELETED);
    }

    @Override
    public Optional<TransferFees> findById(Long id) {
        return repository.findFirstByIdAndRecordStatusNot(id, RecordStatus.DELETED);
    }

    @Override
    public TransferFees save(TransferFees transferFees) {
        return repository.save(transferFees);
    }

    @Override
    public TransferFees update(Long id, TransferFees transferFees) {
        Optional<TransferFees> optional = this.findById(id);
        if (optional.isPresent()) {
            TransferFees currentFee = optional.get();
            currentFee.setTransferFee(transferFees.getTransferFee());
            currentFee.setLevy(transferFees.getLevy());
            currentFee.setTransferValue(transferFees.getTransferValue());
            currentFee.setVat(transferFees.getVat());
            currentFee.setRecordStatus(transferFees.getRecordStatus());
            return repository.save(currentFee);
        }
        return transferFees;
    }

    @Override
    public void deleteById(Long id) {
        Optional<TransferFees> optional = this.findById(id);
        if (optional.isPresent()) {
            TransferFees currentFee = optional.get();
            currentFee.setRecordStatus(RecordStatus.DELETED);
            repository.save(currentFee);
        }
    }

    @Override
    public Page<TransferFees> findAll(Integer pageNo, Integer pageSize) {
        pageNo = PageableHelper.cleanPageNumber(pageNo);
        pageSize = PageableHelper.cleanPageSize(pageSize);
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.Direction.DESC, "id");
        return repository.findAllByRecordStatusNot(pageable, RecordStatus.DELETED);
    }
}
