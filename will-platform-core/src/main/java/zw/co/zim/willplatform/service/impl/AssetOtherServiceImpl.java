package zw.co.zim.willplatform.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import zw.co.zim.willplatform.utils.enums.RecordStatus;
import zw.co.zim.willplatform.model.AssetOther;
import zw.co.zim.willplatform.model.Client;
import zw.co.zim.willplatform.repository.AssetOtherRepository;
import zw.co.zim.willplatform.service.AssetOtherService;
import zw.co.zim.willplatform.utils.PageableHelper;

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
        return repository.findAllByRecordStatusNot(RecordStatus.DELETED);
    }

    @Override
    public Page<AssetOther> findAll(Integer pageNo, Integer pageSize) {
        pageNo = PageableHelper.cleanPageNumber(pageNo);
        pageSize = PageableHelper.cleanPageSize(pageSize);
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.Direction.DESC, "id");
        return repository.findAllByRecordStatusNot(pageable, RecordStatus.DELETED);
    }

    @Override
    public Page<AssetOther> findAllByUserId(Client clientId, Integer pageNo, Integer pageSize) {
        pageNo = PageableHelper.cleanPageNumber(pageNo);
        pageSize = PageableHelper.cleanPageSize(pageSize);
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.Direction.DESC, "id");
        return repository.findAllByUserIdAndRecordStatusNot(pageable, clientId, RecordStatus.DELETED);
    }

    @Override
    public Optional<AssetOther> findById(Long id) {
        return repository.findFirstByIdAndRecordStatusNot(id, RecordStatus.DELETED);
    }

    @Override
    public AssetOther save(AssetOther assetOther) {
        return repository.save(assetOther);
    }

    @Override
    public AssetOther update(Long id, AssetOther assetOther) {
        Optional<AssetOther> optionalAssetOther = this.findById(id);
        if (optionalAssetOther.isPresent()) {
            assetOther.setId(optionalAssetOther.get().getId());
            return repository.save(assetOther);
        }
        return assetOther;
    }

    @Override
    public void deleteById(Long id) {
        Optional<AssetOther> optionalAssetOther = this.findById(id);
        if (optionalAssetOther.isPresent()) {
            AssetOther other = optionalAssetOther.get();
            other.setRecordStatus(RecordStatus.DELETED);
            repository.save(other);
        }
    }
}
