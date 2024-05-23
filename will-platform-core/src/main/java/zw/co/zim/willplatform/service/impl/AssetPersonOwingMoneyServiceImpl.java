package zw.co.zim.willplatform.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import zw.co.zim.willplatform.enums.RecordStatus;
import zw.co.zim.willplatform.model.AssetPersonOwingMoney;
import zw.co.zim.willplatform.model.Client;
import zw.co.zim.willplatform.repository.AssetPersonOwingMoneyRepository;
import zw.co.zim.willplatform.service.AssetPersonOwingMoneyService;
import zw.co.zim.willplatform.utils.PageableHelper;

import java.util.List;
import java.util.Optional;

@Service
public class AssetPersonOwingMoneyServiceImpl implements AssetPersonOwingMoneyService {
    private final AssetPersonOwingMoneyRepository repository;

    public AssetPersonOwingMoneyServiceImpl(AssetPersonOwingMoneyRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<AssetPersonOwingMoney> findAll() {
        return repository.findAllByRecordStatusNot(RecordStatus.DELETED);
    }

    @Override
    public Page<AssetPersonOwingMoney> findAll(Integer pageNo, Integer pageSize) {
        pageNo = PageableHelper.cleanPageNumber(pageNo);
        pageSize = PageableHelper.cleanPageSize(pageSize);
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.Direction.DESC, "id");
        return repository.findAllByRecordStatusNot(pageable, RecordStatus.DELETED);
    }

    @Override
    public Page<AssetPersonOwingMoney> findAllByUserId(Client clientId, Integer pageNo, Integer pageSize) {
        pageNo = PageableHelper.cleanPageNumber(pageNo);
        pageSize = PageableHelper.cleanPageSize(pageSize);
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.Direction.DESC, "id");
        return repository.findAllByUserIdAndRecordStatusNot(pageable, clientId, RecordStatus.DELETED);
    }

    @Override
    public Optional<AssetPersonOwingMoney> findById(Long id) {
        return repository.findFirstByIdAndRecordStatusNot(id, RecordStatus.DELETED);
    }

    @Override
    public AssetPersonOwingMoney save(AssetPersonOwingMoney assetPersonOwingMoney) {
        return repository.save(assetPersonOwingMoney);
    }

    @Override
    public AssetPersonOwingMoney update(Long id, AssetPersonOwingMoney assetPersonOwingMoney) {
        Optional<AssetPersonOwingMoney> optionalAssetPersonOwingMoney = this.findById(id);

        if (optionalAssetPersonOwingMoney.isPresent()) {
            assetPersonOwingMoney.setId(optionalAssetPersonOwingMoney.get().getId());
            return repository.save(assetPersonOwingMoney);
        }
        return assetPersonOwingMoney;
    }

    @Override
    public void deleteById(Long id) {
        Optional<AssetPersonOwingMoney> optionalAssetPersonOwingMoney = this.findById(id);
        if (optionalAssetPersonOwingMoney.isPresent()) {
            AssetPersonOwingMoney owingMoney = optionalAssetPersonOwingMoney.get();
            owingMoney.setRecordStatus(RecordStatus.DELETED);
            repository.save(owingMoney);
        }
    }
}
