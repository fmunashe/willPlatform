package zw.co.zim.willplatform.processor.impl;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import zw.co.zim.willplatform.dto.AssetOtherRecordDto;
import zw.co.zim.willplatform.dto.mapper.AssetOtherDtoMapper;
import zw.co.zim.willplatform.exceptions.RecordNotFoundException;
import zw.co.zim.willplatform.model.AssetOther;
import zw.co.zim.willplatform.model.Client;
import zw.co.zim.willplatform.processor.AssetOtherProcessor;
import zw.co.zim.willplatform.service.AssetOtherService;
import zw.co.zim.willplatform.service.ClientsService;
import zw.co.zim.willplatform.utils.AppConstants;
import zw.co.zim.willplatform.utils.enums.RecordStatus;
import zw.co.zim.willplatform.utils.messages.request.AssetOtherRequest;
import zw.co.zim.willplatform.utils.messages.response.basic.ApiResponse;
import zw.co.zim.willplatform.utils.messages.response.helper.HelperResponse;

import java.util.Optional;

@Service
public class AssetOtherProcessorImpl implements AssetOtherProcessor {
    private final AssetOtherService service;
    private final AssetOtherDtoMapper mapper;
    private final ClientsService clientsService;

    public AssetOtherProcessorImpl(AssetOtherService service, AssetOtherDtoMapper mapper, ClientsService clientsService) {
        this.service = service;
        this.mapper = mapper;
        this.clientsService = clientsService;
    }

    @Override
    public ApiResponse<AssetOtherRecordDto> findAll(Integer pageNo, Integer pageSize) {
        Page<AssetOther> otherPage = service.findAll(pageNo, pageSize);
        return HelperResponse.buildApiResponse(otherPage, mapper, true, 200, true, AppConstants.LIST_MESSAGE, null);
    }

    @Override
    public ApiResponse<AssetOtherRecordDto> findAllByUserId(Long clientId, Integer pageNo, Integer pageSize) {
        Optional<Client> client = clientsService.findById(clientId);

        if (client.isEmpty() || !client.get().getId().equals(clientId)) {
            throw new RecordNotFoundException("Failed to find client record with Id " + clientId);
        }
        Page<AssetOther> otherPage = service.findAllByUserId(client.get(), pageNo, pageSize);
        return HelperResponse.buildApiResponse(otherPage, mapper, true, 200, true, AppConstants.LIST_MESSAGE, null);
    }

    @Override
    public ApiResponse<AssetOtherRecordDto> findById(Long id) {
        Optional<AssetOther> optionalAssetOther = service.findById(id);

        return optionalAssetOther.map(other -> HelperResponse.buildApiResponse(null, null, false, 200, true, AppConstants.FOUND_MESSAGE, mapper.apply(other)))
            .orElseThrow(() -> new RecordNotFoundException("Failed to find other asset record with Id of " + id));

    }

    @Override
    public ApiResponse<AssetOtherRecordDto> save(AssetOtherRequest assetOtherRequest) {
        Optional<Client> optionalClient = clientsService.findById(assetOtherRequest.getClientId());
        if (optionalClient.isEmpty()) {
            throw new RecordNotFoundException("Failed to find client with Id " + assetOtherRequest.getClientId());
        }
        AssetOtherRecordDto recordDto = AssetOtherRecordDto.builder()
            .description(assetOtherRequest.getDescription())
            .value(assetOtherRequest.getValue())
            .userId(optionalClient.get())
            .recordStatus(RecordStatus.ACTIVE)
            .build();

        AssetOther other = service.save(new AssetOther(recordDto));
        return HelperResponse.buildApiResponse(null, null, false, 201, true, AppConstants.SUCCESS_MESSAGE, mapper.apply(other));
    }

    @Override
    public ApiResponse<AssetOtherRecordDto> update(Long id, AssetOtherRecordDto recordDto) {

        Optional<AssetOther> assetOther = service.findById(id);

        if (assetOther.isEmpty() || !assetOther.get().getId().equals(id)) {
            throw new RecordNotFoundException("Failed to find assetOther record with Id " + id);
        }
        AssetOther updatedOther = service.update(id, new AssetOther(recordDto));
        AssetOtherRecordDto mappedDto = mapper.apply(updatedOther);
        return HelperResponse.buildApiResponse(null, null, false, 200, true, AppConstants.SUCCESS_MESSAGE, mappedDto);

    }

    @Override
    public ApiResponse<AssetOtherRecordDto> deleteById(Long id) {
        Optional<AssetOther> otherAsset = service.findById(id);

        if (otherAsset.isEmpty() || !otherAsset.get().getId().equals(id)) {
            throw new RecordNotFoundException("Failed to find otherAsset record with Id " + id);
        }
        service.deleteById(id);
        return HelperResponse.buildApiResponse(null, null, false, 200, true, AppConstants.SUCCESS_MESSAGE, null);

    }
}
