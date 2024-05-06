package zw.co.zim.willplatform.service.impl;

import zw.co.zim.willplatform.enums.RecordStatus;
import zw.co.zim.willplatform.exceptions.RecordExistsException;
import zw.co.zim.willplatform.exceptions.RecordNotFoundException;
import zw.co.zim.willplatform.model.BankAsset;
import zw.co.zim.willplatform.repository.BankAssetRepository;
import zw.co.zim.willplatform.service.BankAssetService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BankAssetServiceImpl implements BankAssetService {

    private final BankAssetRepository bankAssetRepository;

    public BankAssetServiceImpl(BankAssetRepository bankAssetRepository) {
        this.bankAssetRepository = bankAssetRepository;
    }

    @Override
    public List<BankAsset> findAll() {
        return bankAssetRepository.findAll();
    }

    @Override
    public Optional<BankAsset> findById(Long id) {
        return Optional.ofNullable(bankAssetRepository.findById(id).orElseThrow(() -> new RecordNotFoundException("Bank details with Id of " + id + " not found")));
    }

    @Override
    public BankAsset save(BankAsset bankAsset) {
        Optional<BankAsset> currentBank = bankAssetRepository.findFirstByAccountNumber(bankAsset.getAccountNumber(), RecordStatus.DELETED);
        if (currentBank.isPresent())
            throw new RecordExistsException("Bank record with account number " + bankAsset.getAccountNumber() + " already exists");
        return bankAssetRepository.save(bankAsset);
    }

    @Override
    public BankAsset update(Long id, BankAsset bankAsset) {
        Optional<BankAsset> currentBank = bankAssetRepository.findById(id);
        if (currentBank.isEmpty())
            throw new RecordNotFoundException("Bank record with id of " + id + " not found");
        bankAsset.setId(currentBank.get().getId());
        return bankAssetRepository.save(bankAsset);

    }

    @Override
    public void deleteById(Long id) {
        Optional<BankAsset> bankOptional = bankAssetRepository.findById(id);
        if (bankOptional.isEmpty())
            throw new RecordNotFoundException("Bank record with id of " + id + " not found");
        bankAssetRepository.deleteById(id);
    }

    @Override
    public Optional<BankAsset> findBankByAccountNumber(String accountNumber) {
        return Optional.empty();
    }
}
