package zw.co.zim.willplatform.service.impl;

import zw.co.zim.willplatform.exceptions.RecordNotFoundException;
import zw.co.zim.willplatform.model.AssetOther;
import zw.co.zim.willplatform.repository.AssetOtherRepository;
import zw.co.zim.willplatform.service.AssetOtherService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AssetOtherServiceImpl implements AssetOtherService {

    private final AssetOtherRepository repository;

    public AssetOtherServiceImpl(AssetOtherRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<AssetOther> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<AssetOther> findById(Long id) {
        return Optional.ofNullable(repository.findById(id).orElseThrow(() -> new RecordNotFoundException("Record with id of " + id + " could not be found")));
    }

    @Override
    public AssetOther save(AssetOther assetOther) {
        return repository.save(assetOther);
    }

    @Override
    public AssetOther update(Long id, AssetOther assetOther) {
        Optional<AssetOther> optionalAssetOther = this.findById(id);

        if (optionalAssetOther.isEmpty())
            throw new RecordNotFoundException("Record with id of " + id + " could not be found");

        assetOther.setId(optionalAssetOther.get().getId());
        return repository.save(assetOther);
    }

    @Override
    public void deleteById(Long id) {
        Optional<AssetOther> optionalAssetOther = this.findById(id);

        if (optionalAssetOther.isEmpty())
            throw new RecordNotFoundException("Record with id of " + id + " could not be found");

        repository.deleteById(id);
    }
}
