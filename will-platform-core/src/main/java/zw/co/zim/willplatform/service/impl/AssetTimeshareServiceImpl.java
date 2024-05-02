package zw.co.zim.willplatform.service.impl;

import zw.co.zim.willplatform.exceptions.RecordNotFoundException;
import zw.co.zim.willplatform.model.AssetTimeShare;
import zw.co.zim.willplatform.repository.AssetTimeShareRepository;
import zw.co.zim.willplatform.service.AssetTimeshareService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AssetTimeshareServiceImpl implements AssetTimeshareService {

    private final AssetTimeShareRepository repository;

    public AssetTimeshareServiceImpl(AssetTimeShareRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<AssetTimeShare> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<AssetTimeShare> findById(Long id) {
        return Optional.ofNullable(repository.findById(id).orElseThrow(() -> new RecordNotFoundException("Record with id of " + id + " could not be found")));
    }

    @Override
    public AssetTimeShare save(AssetTimeShare assetTimeShare) {
        return repository.save(assetTimeShare);
    }

    @Override
    public AssetTimeShare update(Long id, AssetTimeShare assetTimeShare) {
        Optional<AssetTimeShare> optionalAssetTimeShare = this.findById(id);

        if (optionalAssetTimeShare.isEmpty())
            throw new RecordNotFoundException("Record with id of " + id + " could not be found");

        assetTimeShare.setId(optionalAssetTimeShare.get().getId());
        return repository.save(assetTimeShare);
    }

    @Override
    public void deleteById(Long id) {
        Optional<AssetTimeShare> optionalAssetTimeShare = this.findById(id);

        if (optionalAssetTimeShare.isEmpty())
            throw new RecordNotFoundException("Record with id of " + id + " could not be found");

        repository.deleteById(id);
    }
}
