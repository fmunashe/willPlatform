package zw.co.zim.willplatform.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import zw.co.zim.willplatform.model.BankAsset;
import zw.co.zim.willplatform.model.Client;
import zw.co.zim.willplatform.repository.BankAssetRepository;
import zw.co.zim.willplatform.service.BankAssetService;
import zw.co.zim.willplatform.utils.PageableHelper;
import zw.co.zim.willplatform.utils.enums.RecordStatus;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class BankAssetServiceImpl implements BankAssetService {

    private final BankAssetRepository bankAssetRepository;

    public BankAssetServiceImpl(BankAssetRepository bankAssetRepository) {
        this.bankAssetRepository = bankAssetRepository;
    }

    @Override
    public List<BankAsset> findAll() {
        return bankAssetRepository.findAllByRecordStatusNot(RecordStatus.DELETED);
    }

    @Override
    public Page<BankAsset> findAll(Integer pageNo, Integer pageSize) {
        pageNo = PageableHelper.cleanPageNumber(pageNo);
        pageSize = PageableHelper.cleanPageSize(pageSize);
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.Direction.DESC, "id");
        return bankAssetRepository.findAllByRecordStatusNot(pageable, RecordStatus.DELETED);
    }

    @Override
    public Page<BankAsset> findAllByUserId(Client clientId, Integer pageNo, Integer pageSize) {
        pageNo = PageableHelper.cleanPageNumber(pageNo);
        pageSize = PageableHelper.cleanPageSize(pageSize);
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.Direction.DESC, "id");
        return bankAssetRepository.findAllByUserIdAndRecordStatusNot(pageable, clientId, RecordStatus.DELETED);
    }

    @Override
    public Optional<BankAsset> findById(Long id) {
        return bankAssetRepository.findFirstByIdAndRecordStatusNot(id, RecordStatus.DELETED);
    }

    @Override
    public BankAsset save(BankAsset bankAsset) {
        return bankAssetRepository.save(bankAsset);
    }

    @Override
    public BankAsset update(Long id, BankAsset bankAsset) {
        Optional<BankAsset> currentBank = this.findById(id);
        if (currentBank.isPresent()) {
            bankAsset.setId(currentBank.get().getId());
            return bankAssetRepository.save(bankAsset);
        }
        return bankAsset;
    }

    @Override
    public void deleteById(Long id) {
        Optional<BankAsset> bankOptional = this.findById(id);
        if (bankOptional.isPresent()) {
            BankAsset asset = bankOptional.get();
            asset.setRecordStatus(RecordStatus.DELETED);
            bankAssetRepository.save(asset);
        }

    }

    @Override
    public Optional<BankAsset> findBankByAccountNumber(String accountNumber) {
        return bankAssetRepository.findFirstByAccountNumberAndRecordStatusNot(accountNumber, RecordStatus.DELETED);
    }
}
