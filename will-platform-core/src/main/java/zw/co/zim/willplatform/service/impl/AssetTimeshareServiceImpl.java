package zw.co.zim.willplatform.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import zw.co.zim.willplatform.utils.enums.RecordStatus;
import zw.co.zim.willplatform.model.AssetTimeShare;
import zw.co.zim.willplatform.model.Client;
import zw.co.zim.willplatform.repository.AssetTimeShareRepository;
import zw.co.zim.willplatform.service.AssetTimeshareService;
import zw.co.zim.willplatform.utils.PageableHelper;

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
        return repository.findAllByRecordStatusNot(RecordStatus.DELETED);
    }

    @Override
    public Page<AssetTimeShare> findAll(Integer pageNo, Integer pageSize) {
        pageNo = PageableHelper.cleanPageNumber(pageNo);
        pageSize = PageableHelper.cleanPageSize(pageSize);
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.Direction.DESC, "id");
        return repository.findAllByRecordStatusNot(pageable, RecordStatus.DELETED);
    }

    @Override
    public Page<AssetTimeShare> findAllByUserId(Client clientId, Integer pageNo, Integer pageSize) {
        pageNo = PageableHelper.cleanPageNumber(pageNo);
        pageSize = PageableHelper.cleanPageSize(pageSize);
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.Direction.DESC, "id");
        return repository.findAllByUserIdAndRecordStatusNot(pageable, clientId, RecordStatus.DELETED);
    }

    @Override
    public Optional<AssetTimeShare> findById(Long id) {
        return repository.findFirstByIdAndRecordStatusNot(id, RecordStatus.DELETED);
    }

    @Override
    public AssetTimeShare save(AssetTimeShare assetTimeShare) {
        return repository.save(assetTimeShare);
    }

    @Override
    public AssetTimeShare update(Long id, AssetTimeShare assetTimeShare) {
        Optional<AssetTimeShare> optionalAssetTimeShare = this.findById(id);

        if (optionalAssetTimeShare.isPresent()) {
            assetTimeShare.setId(optionalAssetTimeShare.get().getId());
            return repository.save(assetTimeShare);
        }

        return assetTimeShare;
    }

    @Override
    public void deleteById(Long id) {
        Optional<AssetTimeShare> optionalAssetTimeShare = this.findById(id);
        if (optionalAssetTimeShare.isPresent()) {
            AssetTimeShare timeShare = optionalAssetTimeShare.get();
            timeShare.setRecordStatus(RecordStatus.DELETED);
            repository.save(timeShare);
        }
    }
}
