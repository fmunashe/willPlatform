package zw.co.zim.willplatform.service.impl;

import zw.co.zim.willplatform.exceptions.RecordNotFoundException;
import zw.co.zim.willplatform.model.AssetTrust;
import zw.co.zim.willplatform.repository.AssetTrustRepository;
import zw.co.zim.willplatform.service.AssetTrustService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AssetTrustServiceImpl implements AssetTrustService {

    private final AssetTrustRepository assetTrustRepository;

    public AssetTrustServiceImpl(AssetTrustRepository assetTrustRepository) {
        this.assetTrustRepository = assetTrustRepository;
    }

    @Override
    public List<AssetTrust> findAll() {
        return assetTrustRepository.findAll();
    }

    @Override
    public Optional<AssetTrust> findById(Long id) {
        return Optional.ofNullable(assetTrustRepository.findById(id).orElseThrow(() -> new RecordNotFoundException("Record with id of " + id + " could not be found")));
    }

    @Override
    public AssetTrust save(AssetTrust assetTrust) {
        return assetTrustRepository.save(assetTrust);
    }

    @Override
    public AssetTrust update(Long id, AssetTrust assetTrust) {
        Optional<AssetTrust> optionalAssetTrust = this.findById(id);
        if (optionalAssetTrust.isEmpty())
            throw new RecordNotFoundException("Asset Trust with id of " + id + " could not be found");

        assetTrust.setId(optionalAssetTrust.get().getId());
        return assetTrustRepository.save(assetTrust);
    }

    @Override
    public void deleteById(Long id) {
        Optional<AssetTrust> optionalAssetTrust = this.findById(id);
        if (optionalAssetTrust.isEmpty())
            throw new RecordNotFoundException("Asset Trust with id of " + id + " could not be found");
        assetTrustRepository.deleteById(id);
    }
}
