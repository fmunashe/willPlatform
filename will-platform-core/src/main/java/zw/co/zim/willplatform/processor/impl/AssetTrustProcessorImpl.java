package zw.co.zim.willplatform.processor.impl;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import zw.co.zim.willplatform.dto.AssetTrustDto;
import zw.co.zim.willplatform.dto.mapper.AssetTrustDtoMapper;
import zw.co.zim.willplatform.exceptions.RecordNotFoundException;
import zw.co.zim.willplatform.model.AssetTrust;
import zw.co.zim.willplatform.model.Client;
import zw.co.zim.willplatform.processor.AssetTrustProcessor;
import zw.co.zim.willplatform.service.AssetTrustService;
import zw.co.zim.willplatform.service.ClientsService;
import zw.co.zim.willplatform.utils.AppConstants;
import zw.co.zim.willplatform.utils.enums.RecordStatus;
import zw.co.zim.willplatform.utils.messages.request.AssetTrustRequest;
import zw.co.zim.willplatform.utils.messages.response.basic.ApiResponse;
import zw.co.zim.willplatform.utils.messages.response.helper.HelperResponse;

import java.util.Objects;
import java.util.Optional;

@Service
public class AssetTrustProcessorImpl implements AssetTrustProcessor {
    private final AssetTrustService assetTrustService;
    private final AssetTrustDtoMapper mapper;
    private final ClientsService clientsService;

    public AssetTrustProcessorImpl(AssetTrustService assetTrustService, AssetTrustDtoMapper mapper, ClientsService clientsService) {
        this.assetTrustService = assetTrustService;
        this.mapper = mapper;
        this.clientsService = clientsService;
    }

    @Override
    public ApiResponse<AssetTrustDto> findAll(Integer pageNo, Integer pageSize) {
        Page<AssetTrust> trustPage = assetTrustService.findAll(pageNo, pageSize);
        return HelperResponse.buildApiResponse(trustPage, mapper, true, 200, true, AppConstants.LIST_MESSAGE, null);
    }

    @Override
    public ApiResponse<AssetTrustDto> save(AssetTrustRequest assetTrustRequest) {

        Optional<Client> optionalClient = clientsService.findById(assetTrustRequest.getClientId());
        if (optionalClient.isEmpty()) {
            throw new RecordNotFoundException("Failed to find client with Id " + assetTrustRequest.getClientId());
        }
        AssetTrustDto recordDto = AssetTrustDto.builder()
            .value(assetTrustRequest.getValue())
            .userId(optionalClient.get())
            .nameOfTrust(assetTrustRequest.getNameOfTrust())
            .recordStatus(RecordStatus.ACTIVE)
            .build();

        AssetTrust trust = assetTrustService.save(new AssetTrust(recordDto));
        return HelperResponse.buildApiResponse(null, null, false, 201, true, AppConstants.SUCCESS_MESSAGE, mapper.apply(trust));
    }

    @Override
    public ApiResponse<AssetTrustDto> findAllByUserId(Long clientId, Integer pageNo, Integer pageSize) {

        Optional<Client> client = clientsService.findById(clientId);

        if (client.isEmpty() || !client.get().getId().equals(clientId)) {
            throw new RecordNotFoundException("Failed to find client record with Id " + clientId);
        }
        Page<AssetTrust> trustPage = assetTrustService.findAllByUserId(client.get(), pageNo, pageSize);
        return HelperResponse.buildApiResponse(trustPage, mapper, true, 200, true, AppConstants.LIST_MESSAGE, null);

    }

    @Override
    public ApiResponse<AssetTrustDto> findById(Long id) {
        Optional<AssetTrust> optional = assetTrustService.findById(id);

        return optional.map(trust -> HelperResponse.buildApiResponse(null, null, false, 200, true, AppConstants.FOUND_MESSAGE, mapper.apply(trust)))
            .orElseThrow(() -> new RecordNotFoundException("Failed to find asset trust record with id of " + id));
    }

    @Override
    public ApiResponse<AssetTrustDto> update(Long id, AssetTrustDto assetTrustDto) {
        Optional<AssetTrust> trust = assetTrustService.findById(id);

        if (trust.isEmpty() || !Objects.equals(trust.get().getId(), id)) {
            throw new RecordNotFoundException("Failed to find asset trust record with Id " + id);
        }
        AssetTrust updatedRecord = assetTrustService.update(id, new AssetTrust(assetTrustDto));
        AssetTrustDto mappedDto = mapper.apply(updatedRecord);
        return HelperResponse.buildApiResponse(null, null, false, 200, true, AppConstants.SUCCESS_MESSAGE, mappedDto);

    }

    @Override
    public ApiResponse<AssetTrustDto> deleteById(Long id) {
        Optional<AssetTrust> trust = assetTrustService.findById(id);

        if (trust.isEmpty() || !trust.get().getId().equals(id)) {
            throw new RecordNotFoundException("Failed to find asset trust record with Id " + id);
        }
        assetTrustService.deleteById(id);
        return HelperResponse.buildApiResponse(null, null, false, 200, true, AppConstants.SUCCESS_MESSAGE, null);

    }
}
