package zw.co.zim.willplatform.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import zw.co.zim.willplatform.enums.RecordStatus;
import zw.co.zim.willplatform.model.AssetPolicy;
import zw.co.zim.willplatform.model.Client;
import zw.co.zim.willplatform.repository.AssetPolicyRepository;
import zw.co.zim.willplatform.service.AssetPolicyService;
import zw.co.zim.willplatform.utils.PageableHelper;

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
        return repository.findAllByRecordStatusNot(RecordStatus.DELETED);
    }

    @Override
    public Page<AssetPolicy> findAll(Integer pageNo, Integer pageSize) {
        pageNo = PageableHelper.cleanPageNumber(pageNo);
        pageSize = PageableHelper.cleanPageSize(pageSize);
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.Direction.DESC, "id");
        return repository.findAllByRecordStatusNot(pageable, RecordStatus.DELETED);
    }

    @Override
    public Page<AssetPolicy> findAllByUserId(Client clientId, Integer pageNo, Integer pageSize) {
        pageNo = PageableHelper.cleanPageNumber(pageNo);
        pageSize = PageableHelper.cleanPageSize(pageSize);
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.Direction.DESC, "id");
        return repository.findAllByUserIdAndRecordStatusNot(pageable, clientId, RecordStatus.DELETED);
    }

    @Override
    public Optional<AssetPolicy> findById(Long id) {
        return repository.findFirstByIdAndRecordStatusNot(id, RecordStatus.DELETED);
    }

    @Override
    public AssetPolicy save(AssetPolicy assetPolicy) {
        return repository.save(assetPolicy);
    }

    @Override
    public AssetPolicy update(Long id, AssetPolicy assetPolicy) {
        Optional<AssetPolicy> optionalAssetPolicy = this.findById(id);

        if (optionalAssetPolicy.isPresent()) {
            assetPolicy.setId(optionalAssetPolicy.get().getId());
            repository.save(assetPolicy);
        }
        return assetPolicy;
    }

    @Override
    public void deleteById(Long id) {
        Optional<AssetPolicy> optionalAssetPolicy = this.findById(id);

        if (optionalAssetPolicy.isPresent()) {
            AssetPolicy assetPolicy = optionalAssetPolicy.get();
            assetPolicy.setRecordStatus(RecordStatus.DELETED);
            repository.save(assetPolicy);
        }
    }

    @Override
    public Optional<AssetPolicy> findFirstByPolicyNumber(String policyNumber) {
        return repository.findFirstByPolicyNumberAndRecordStatusNot(policyNumber, RecordStatus.DELETED);
    }
}
