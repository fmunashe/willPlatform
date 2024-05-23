package zw.co.zim.willplatform.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import zw.co.zim.willplatform.enums.RecordStatus;
import zw.co.zim.willplatform.model.AssetTrust;
import zw.co.zim.willplatform.model.Client;
import zw.co.zim.willplatform.repository.AssetTrustRepository;
import zw.co.zim.willplatform.service.AssetTrustService;
import zw.co.zim.willplatform.utils.PageableHelper;

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
        return assetTrustRepository.findAllByRecordStatusNot(RecordStatus.DELETED);
    }

    @Override
    public Page<AssetTrust> findAll(Integer pageNo, Integer pageSize) {
        pageNo = PageableHelper.cleanPageNumber(pageNo);
        pageSize = PageableHelper.cleanPageSize(pageSize);
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.Direction.DESC, "id");
        return assetTrustRepository.findAllByRecordStatusNot(pageable, RecordStatus.DELETED);
    }

    @Override
    public Page<AssetTrust> findAllByUserId(Client clientId, Integer pageNo, Integer pageSize) {
        pageNo = PageableHelper.cleanPageNumber(pageNo);
        pageSize = PageableHelper.cleanPageSize(pageSize);
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.Direction.DESC, "id");
        return assetTrustRepository.findAllByUserIdAndRecordStatusNot(pageable, clientId, RecordStatus.DELIVERED);
    }

    @Override
    public Optional<AssetTrust> findById(Long id) {
        return assetTrustRepository.findFirstByIdAndRecordStatusNot(id, RecordStatus.DELETED);
    }

    @Override
    public AssetTrust save(AssetTrust assetTrust) {
        return assetTrustRepository.save(assetTrust);
    }

    @Override
    public AssetTrust update(Long id, AssetTrust assetTrust) {
        Optional<AssetTrust> optionalAssetTrust = this.findById(id);
        if (optionalAssetTrust.isPresent()) {
            assetTrust.setId(optionalAssetTrust.get().getId());
            return assetTrustRepository.save(assetTrust);
        }

        return assetTrust;
    }

    @Override
    public void deleteById(Long id) {
        Optional<AssetTrust> optionalAssetTrust = this.findById(id);
        if (optionalAssetTrust.isPresent()) {
            AssetTrust trust = optionalAssetTrust.get();
            trust.setRecordStatus(RecordStatus.DELETED);
            assetTrustRepository.save(trust);
        }
    }
}
