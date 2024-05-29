package zw.co.zim.willplatform.processor.impl;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import zw.co.zim.willplatform.dto.AssetPolicyRecordDto;
import zw.co.zim.willplatform.dto.mapper.AssetPolicyDtoMapper;
import zw.co.zim.willplatform.exceptions.RecordNotFoundException;
import zw.co.zim.willplatform.model.AssetPolicy;
import zw.co.zim.willplatform.model.Client;
import zw.co.zim.willplatform.processor.AssetPolicyProcessor;
import zw.co.zim.willplatform.service.AssetPolicyService;
import zw.co.zim.willplatform.service.ClientsService;
import zw.co.zim.willplatform.utils.AppConstants;
import zw.co.zim.willplatform.utils.enums.RecordStatus;
import zw.co.zim.willplatform.utils.messages.request.AssetPolicyRequest;
import zw.co.zim.willplatform.utils.messages.response.basic.ApiResponse;
import zw.co.zim.willplatform.utils.messages.response.helper.HelperResponse;

import java.util.Objects;
import java.util.Optional;

@Service
public class AssetPolicyProcessorImpl implements AssetPolicyProcessor {
    private final AssetPolicyService service;
    private final AssetPolicyDtoMapper mapper;
    private final ClientsService clientsService;

    public AssetPolicyProcessorImpl(AssetPolicyService service, AssetPolicyDtoMapper mapper, ClientsService clientsService) {
        this.service = service;
        this.mapper = mapper;
        this.clientsService = clientsService;
    }

    @Override
    public ApiResponse<AssetPolicyRecordDto> findAll(Integer pageNo, Integer pageSize) {
        Page<AssetPolicy> policyPage = service.findAll(pageNo, pageSize);
        return HelperResponse.buildApiResponse(policyPage, mapper, true, 200, true, AppConstants.LIST_MESSAGE, null);
    }

    @Override
    public ApiResponse<AssetPolicyRecordDto> save(AssetPolicyRequest assetPolicyRequest) {
        Optional<Client> optionalClient = clientsService.findById(assetPolicyRequest.getClientId());
        if (optionalClient.isEmpty()) {
            throw new RecordNotFoundException("Failed to find client with Id " + assetPolicyRequest.getClientId());
        }
        AssetPolicyRecordDto recordDto = AssetPolicyRecordDto.builder()
            .value(assetPolicyRequest.getValue())
            .userId(optionalClient.get())
            .policyType(assetPolicyRequest.getPolicyType())
            .policyNumber(assetPolicyRequest.getPolicyNumber())
            .company(assetPolicyRequest.getCompany())
            .recordStatus(RecordStatus.ACTIVE)
            .build();

        AssetPolicy policy = service.save(new AssetPolicy(recordDto));
        return HelperResponse.buildApiResponse(null, null, false, 201, true, AppConstants.SUCCESS_MESSAGE, mapper.apply(policy));

    }

    @Override
    public ApiResponse<AssetPolicyRecordDto> findById(Long id) {
        Optional<AssetPolicy> optional = service.findById(id);

        return optional.map(policy -> HelperResponse.buildApiResponse(null, null, false, 200, true, AppConstants.FOUND_MESSAGE, mapper.apply(policy)))
            .orElseThrow(() -> new RecordNotFoundException("Failed to find policy record with id of " + id));
    }


    @Override
    public ApiResponse<AssetPolicyRecordDto> update(Long id, AssetPolicyRecordDto policyRecordDto) {
        Optional<AssetPolicy> policy = service.findById(id);

        if (policy.isEmpty() || !Objects.equals(policy.get().getId(), id)) {
            throw new RecordNotFoundException("Failed to find policy record with Id " + id);
        }
        AssetPolicy updatedRecord = service.update(id, new AssetPolicy(policyRecordDto));
        AssetPolicyRecordDto mappedDto = mapper.apply(updatedRecord);
        return HelperResponse.buildApiResponse(null, null, false, 200, true, AppConstants.SUCCESS_MESSAGE, mappedDto);

    }

    @Override
    public ApiResponse<AssetPolicyRecordDto> deleteById(Long id) {
        Optional<AssetPolicy> policy = service.findById(id);

        if (policy.isEmpty() || !policy.get().getId().equals(id)) {
            throw new RecordNotFoundException("Failed to find policy record with Id " + id);
        }
        service.deleteById(id);
        return HelperResponse.buildApiResponse(null, null, false, 200, true, AppConstants.SUCCESS_MESSAGE, null);

    }

    @Override
    public ApiResponse<AssetPolicyRecordDto> findFirstByPolicyNumber(String policyNumber) {
        Optional<AssetPolicy> optional = service.findFirstByPolicyNumber(policyNumber);

        return optional.map(policy -> HelperResponse.buildApiResponse(null, null, false, 200, true, AppConstants.FOUND_MESSAGE, mapper.apply(policy)))
            .orElseThrow(() -> new RecordNotFoundException("Failed to find policy record with number " + policyNumber));
    }

    @Override
    public ApiResponse<AssetPolicyRecordDto> findAllByUserId(Long clientId, Integer pageNo, Integer pageSize) {
        Optional<Client> client = clientsService.findById(clientId);

        if (client.isEmpty() || !client.get().getId().equals(clientId)) {
            throw new RecordNotFoundException("Failed to find client record with Id " + clientId);
        }
        Page<AssetPolicy> policyPage = service.findAllByUserId(client.get(), pageNo, pageSize);
        return HelperResponse.buildApiResponse(policyPage, mapper, true, 200, true, AppConstants.LIST_MESSAGE, null);

    }
}
