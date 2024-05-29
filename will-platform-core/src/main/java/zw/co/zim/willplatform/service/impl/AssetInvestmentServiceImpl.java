package zw.co.zim.willplatform.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import zw.co.zim.willplatform.utils.enums.RecordStatus;
import zw.co.zim.willplatform.model.AssetInvestment;
import zw.co.zim.willplatform.model.Client;
import zw.co.zim.willplatform.repository.AssetInvestmentRepository;
import zw.co.zim.willplatform.repository.spcecifications.AssetInvestmentSpecification;
import zw.co.zim.willplatform.service.AssetInvestmentService;
import zw.co.zim.willplatform.utils.PageableHelper;

import java.util.List;
import java.util.Optional;

@Service
public class AssetInvestmentServiceImpl implements AssetInvestmentService {

    private final AssetInvestmentRepository repository;

    public AssetInvestmentServiceImpl(AssetInvestmentRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<AssetInvestment> findAll() {

        return repository.findAllByRecordStatusNot(RecordStatus.DELETED);
    }

    @Override
    public Page<AssetInvestment> findAll(Integer pageNo, Integer pageSize) {
        pageNo = PageableHelper.cleanPageNumber(pageNo);
        pageSize = PageableHelper.cleanPageSize(pageSize);
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.Direction.DESC, "id");
        return repository.findAllByRecordStatusNot(pageable, RecordStatus.DELETED);
    }

    @Override
    public Page<AssetInvestment> findAllByInvestmentType(String investmentType, Integer pageNo, Integer pageSize) {
        pageNo = PageableHelper.cleanPageNumber(pageNo);
        pageSize = PageableHelper.cleanPageSize(pageSize);
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.Direction.DESC, "id");

        Specification<AssetInvestment> spec = Specification.where(null);
        if (investmentType != null && !investmentType.isEmpty()) {
            spec = spec.and(AssetInvestmentSpecification.ofType(investmentType));
        }
        return repository.findAll(spec, pageable);
    }

    @Override
    public Page<AssetInvestment> findAllByUserId(Client clientId, Integer pageNo, Integer pageSize) {
        pageNo = PageableHelper.cleanPageNumber(pageNo);
        pageSize = PageableHelper.cleanPageSize(pageSize);
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.Direction.DESC, "id");
        return repository.findAllByUserIdAndRecordStatusNot(pageable, clientId, RecordStatus.DELETED);
    }

    @Override
    public Optional<AssetInvestment> findById(Long id) {
        return repository.findFirstByIdAndRecordStatusNot(id, RecordStatus.DELETED);
    }

    @Override
    public AssetInvestment save(AssetInvestment assetInvestment) {
        return repository.save(assetInvestment);
    }

    @Override
    public AssetInvestment update(Long id, AssetInvestment assetInvestment) {
        Optional<AssetInvestment> optionalAssetInvestment = this.findById(id);
        if (optionalAssetInvestment.isPresent()) {
            AssetInvestment investment = optionalAssetInvestment.get();
            assetInvestment.setId(investment.getId());
            return repository.save(assetInvestment);
        }
        return assetInvestment;
    }

    @Override
    public void deleteById(Long id) {
        Optional<AssetInvestment> optionalAssetInvestment = this.findById(id);
        if (optionalAssetInvestment.isPresent()) {
            AssetInvestment investment = optionalAssetInvestment.get();
            investment.setRecordStatus(RecordStatus.DELETED);
            repository.save(investment);
        }
    }
}
