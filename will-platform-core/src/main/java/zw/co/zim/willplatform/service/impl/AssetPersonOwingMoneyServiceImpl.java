package zw.co.zim.willplatform.service.impl;

import zw.co.zim.willplatform.exceptions.RecordNotFoundException;
import zw.co.zim.willplatform.model.AssetPersonOwingMoney;
import zw.co.zim.willplatform.repository.AssetPersonOwingMoneyRepository;
import zw.co.zim.willplatform.service.AssetPersonOwingMoneyService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AssetPersonOwingMoneyServiceImpl implements AssetPersonOwingMoneyService {
    private final AssetPersonOwingMoneyRepository repository;

    public AssetPersonOwingMoneyServiceImpl(AssetPersonOwingMoneyRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<AssetPersonOwingMoney> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<AssetPersonOwingMoney> findById(Long id) {
        return Optional.ofNullable(repository.findById(id).orElseThrow(() -> new RecordNotFoundException("Record with id of " + id + " could not be found")));
    }

    @Override
    public AssetPersonOwingMoney save(AssetPersonOwingMoney assetPersonOwingMoney) {
        return repository.save(assetPersonOwingMoney);
    }

    @Override
    public AssetPersonOwingMoney update(Long id, AssetPersonOwingMoney assetPersonOwingMoney) {
        Optional<AssetPersonOwingMoney> optionalAssetPersonOwingMoney = this.findById(id);

        if (optionalAssetPersonOwingMoney.isEmpty())
            throw new RecordNotFoundException("Record with id of " + id + " could not be found");

        assetPersonOwingMoney.setId(optionalAssetPersonOwingMoney.get().getId());
        return repository.save(assetPersonOwingMoney);
    }

    @Override
    public void deleteById(Long id) {
        Optional<AssetPersonOwingMoney> optionalAssetPersonOwingMoney = this.findById(id);

        if (optionalAssetPersonOwingMoney.isEmpty())
            throw new RecordNotFoundException("Record with id of " + id + " could not be found");

        repository.deleteById(id);
    }
}
