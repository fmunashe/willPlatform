package zw.co.zim.willplatform.service.impl;

import zw.co.zim.willplatform.exceptions.RecordNotFoundException;
import zw.co.zim.willplatform.model.AssetOffshore;
import zw.co.zim.willplatform.repository.AssetOffshoreRepository;
import zw.co.zim.willplatform.service.AssetOffshoreService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AssetOffshoreServiceImpl implements AssetOffshoreService {
    private final AssetOffshoreRepository repository;

    public AssetOffshoreServiceImpl(AssetOffshoreRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<AssetOffshore> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<AssetOffshore> findById(Long id) {
        return Optional.ofNullable(repository.findById(id).orElseThrow(() -> new RecordNotFoundException("Record with id of " + id + " could not be found")));
    }

    @Override
    public AssetOffshore save(AssetOffshore assetOffshore) {
        return repository.save(assetOffshore);
    }

    @Override
    public AssetOffshore update(Long id, AssetOffshore assetOffshore) {
        Optional<AssetOffshore> optionalAssetOffshore = this.findById(id);

        if (optionalAssetOffshore.isEmpty())
            throw new RecordNotFoundException("Record with id of " + id + " could not be found");

        assetOffshore.setId(optionalAssetOffshore.get().getId());
        return repository.save(assetOffshore);
    }

    @Override
    public void deleteById(Long id) {
        Optional<AssetOffshore> optionalAssetOffshore = this.findById(id);

        if (optionalAssetOffshore.isEmpty())
            throw new RecordNotFoundException("Record with id of " + id + " could not be found");

        repository.deleteById(id);
    }
}
