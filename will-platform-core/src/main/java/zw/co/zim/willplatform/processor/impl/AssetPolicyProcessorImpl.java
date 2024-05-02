package zw.co.zim.willplatform.processor.impl;

import zw.co.zim.willplatform.dto.AssetPolicyRecordDto;
import zw.co.zim.willplatform.dto.mapper.AssetPolicyDtoMapper;
import zw.co.zim.willplatform.model.AssetPolicy;
import zw.co.zim.willplatform.processor.AssetPolicyProcessor;
import zw.co.zim.willplatform.service.AssetPolicyService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AssetPolicyProcessorImpl implements AssetPolicyProcessor {
    private final AssetPolicyService service;
    private final AssetPolicyDtoMapper mapper;

    public AssetPolicyProcessorImpl(AssetPolicyService service, AssetPolicyDtoMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @Override
    public List<AssetPolicyRecordDto> findAll() {
        return service.findAll()
            .stream()
            .map(mapper)
            .collect(Collectors.toList());
    }

    @Override
    public Optional<AssetPolicyRecordDto> findById(Long id) {
        Optional<AssetPolicy> optionalAssetPolicy = service.findById(id);
        return optionalAssetPolicy.map(mapper);
    }

    @Override
    public AssetPolicyRecordDto save(AssetPolicyRecordDto policyRecordDto) {
        AssetPolicy policy = service.save(new AssetPolicy(policyRecordDto));
        return mapper.apply(policy);
    }

    @Override
    public AssetPolicyRecordDto update(Long id, AssetPolicyRecordDto policyRecordDto) {
        AssetPolicy updatedPolicy = service.update(id, new AssetPolicy(policyRecordDto));
        return mapper.apply(updatedPolicy);
    }

    @Override
    public void deleteById(Long id) {
        service.deleteById(id);
    }

    @Override
    public Optional<AssetPolicyRecordDto> findFirstByPolicyNumber(String policyNumber) {
        Optional<AssetPolicy> policy= service.findFirstByPolicyNumber(policyNumber);
        return policy.map(mapper);
    }
}
