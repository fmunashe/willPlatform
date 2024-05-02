package zw.co.zim.willplatform.service.impl;

import zw.co.zim.willplatform.exceptions.RecordNotFoundException;
import zw.co.zim.willplatform.model.AssetInvestment;
import zw.co.zim.willplatform.repository.AssetInvestmentRepository;
import zw.co.zim.willplatform.service.AssetInvestmentService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AssetInvestmentServiceImpl implements AssetInvestmentService {

    private final AssetInvestmentRepository repository;

    public AssetInvestmentServiceImpl(AssetInvestmentRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<AssetInvestment> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<AssetInvestment> findById(Long id) {
        return Optional.ofNullable(repository.findById(id).orElseThrow(() -> new RecordNotFoundException("Record with id of " + id + " could not be found")));
    }

    @Override
    public AssetInvestment save(AssetInvestment assetInvestment) {
        return repository.save(assetInvestment);
    }

    @Override
    public AssetInvestment update(Long id, AssetInvestment assetInvestment) {
        Optional<AssetInvestment> optionalAssetInvestment = this.findById(id);

        if (optionalAssetInvestment.isEmpty())
            throw new RecordNotFoundException("Record with id of " + id + " could not be found");

        assetInvestment.setId(optionalAssetInvestment.get().getId());
        return repository.save(assetInvestment);
    }

    @Override
    public void deleteById(Long id) {
        Optional<AssetInvestment> optionalAssetInvestment = this.findById(id);

        if (optionalAssetInvestment.isEmpty())
            throw new RecordNotFoundException("Record with id of " + id + " could not be found");


        repository.deleteById(id);
    }
}
