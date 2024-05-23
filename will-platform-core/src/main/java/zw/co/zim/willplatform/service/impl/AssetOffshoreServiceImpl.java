package zw.co.zim.willplatform.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import zw.co.zim.willplatform.enums.RecordStatus;
import zw.co.zim.willplatform.model.AssetOffshore;
import zw.co.zim.willplatform.model.Client;
import zw.co.zim.willplatform.repository.AssetOffshoreRepository;
import zw.co.zim.willplatform.service.AssetOffshoreService;
import zw.co.zim.willplatform.utils.PageableHelper;

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
        return repository.findAllByRecordStatusNot(RecordStatus.DELETED);
    }

    @Override
    public Page<AssetOffshore> findAll(Integer pageNo, Integer pageSize) {
        pageNo = PageableHelper.cleanPageNumber(pageNo);
        pageSize = PageableHelper.cleanPageSize(pageSize);
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.Direction.DESC, "id");
        return repository.findAllByRecordStatusNot(pageable, RecordStatus.DELETED);
    }

    @Override
    public Page<AssetOffshore> findAllByUserId(Client clientId, Integer pageNo, Integer pageSize) {
        pageNo = PageableHelper.cleanPageNumber(pageNo);
        pageSize = PageableHelper.cleanPageSize(pageSize);
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.Direction.DESC, "id");
        return repository.findAllByUserIdAndRecordStatusNot(pageable, clientId, RecordStatus.DELETED);
    }

    @Override
    public Optional<AssetOffshore> findById(Long id) {
        return repository.findFirstByIdAndRecordStatusNot(id, RecordStatus.DELETED);
    }

    @Override
    public AssetOffshore save(AssetOffshore assetOffshore) {
        return repository.save(assetOffshore);
    }

    @Override
    public AssetOffshore update(Long id, AssetOffshore assetOffshore) {
        Optional<AssetOffshore> optionalAssetOffshore = this.findById(id);

        if (optionalAssetOffshore.isPresent()) {
            assetOffshore.setId(optionalAssetOffshore.get().getId());
            return repository.save(assetOffshore);
        }
        return assetOffshore;
    }

    @Override
    public void deleteById(Long id) {
        Optional<AssetOffshore> optionalAssetOffshore = this.findById(id);
        if (optionalAssetOffshore.isPresent()) {
            AssetOffshore offshore = optionalAssetOffshore.get();
            offshore.setRecordStatus(RecordStatus.DELETED);
            repository.save(offshore);
        }
    }
}
