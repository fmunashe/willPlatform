package zw.co.zim.willplatform.service.impl;

import zw.co.zim.willplatform.enums.RecordStatus;
import zw.co.zim.willplatform.exceptions.RecordExistsException;
import zw.co.zim.willplatform.exceptions.RecordNotFoundException;
import zw.co.zim.willplatform.model.AssetPolicy;
import zw.co.zim.willplatform.repository.AssetPolicyRepository;
import zw.co.zim.willplatform.service.AssetPolicyService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AssetPolicyServiceImpl implements AssetPolicyService {

    private final AssetPolicyRepository repository;

    public AssetPolicyServiceImpl(AssetPolicyRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<AssetPolicy> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<AssetPolicy> findById(Long id) {
        return Optional.ofNullable(repository.findById(id).orElseThrow(() -> new RecordNotFoundException("Policy with id of " + id + " could not be found")));
    }

    @Override
    public AssetPolicy save(AssetPolicy assetPolicy) {
        Optional<AssetPolicy> optionalAssetPolicy = this.findFirstByPolicyNumber(assetPolicy.getPolicyNumber());

        if (optionalAssetPolicy.isEmpty())
            return repository.save(assetPolicy);

        throw new RecordExistsException("Record with policy number of " + optionalAssetPolicy.get().getPolicyNumber() + " already exists");
    }

    @Override
    public AssetPolicy update(Long id, AssetPolicy assetPolicy) {
        Optional<AssetPolicy> optionalAssetPolicy = this.findById(id);

        if (optionalAssetPolicy.isEmpty())
            throw new RecordNotFoundException("Record with id of " + id + " could not be found");

        assetPolicy.setId(optionalAssetPolicy.get().getId());
        return repository.save(assetPolicy);
    }

    @Override
    public void deleteById(Long id) {
        Optional<AssetPolicy> optionalAssetPolicy = this.findById(id);

        if (optionalAssetPolicy.isEmpty())
            throw new RecordNotFoundException("Record with id of " + id + " could not be found");

        repository.deleteById(id);
    }

    @Override
    public Optional<AssetPolicy> findFirstByPolicyNumber(String policyNumber) {
        return Optional.ofNullable(repository.findFirstByPolicyNumber(policyNumber, RecordStatus.DELETED).orElseThrow(() -> new RecordNotFoundException("Policy with number " + policyNumber + " could not be found")));
    }
}
