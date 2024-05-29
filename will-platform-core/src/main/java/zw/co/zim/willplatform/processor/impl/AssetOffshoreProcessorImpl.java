package zw.co.zim.willplatform.processor.impl;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import zw.co.zim.willplatform.dto.AssetOffshoreRecordDto;
import zw.co.zim.willplatform.dto.mapper.AssetOffshoreDtoMapper;
import zw.co.zim.willplatform.exceptions.RecordNotFoundException;
import zw.co.zim.willplatform.model.AssetOffshore;
import zw.co.zim.willplatform.model.Client;
import zw.co.zim.willplatform.processor.AssetOffshoreProcessor;
import zw.co.zim.willplatform.service.AssetOffshoreService;
import zw.co.zim.willplatform.service.ClientsService;
import zw.co.zim.willplatform.utils.AppConstants;
import zw.co.zim.willplatform.utils.enums.RecordStatus;
import zw.co.zim.willplatform.utils.messages.request.AssetOffshoreRequest;
import zw.co.zim.willplatform.utils.messages.response.basic.ApiResponse;
import zw.co.zim.willplatform.utils.messages.response.helper.HelperResponse;

import java.util.Optional;

@Service
public class AssetOffshoreProcessorImpl implements AssetOffshoreProcessor {
    private final AssetOffshoreService service;
    private final AssetOffshoreDtoMapper mapper;
    private final ClientsService clientsService;

    public AssetOffshoreProcessorImpl(AssetOffshoreService service, AssetOffshoreDtoMapper mapper, ClientsService clientsService) {
        this.service = service;
        this.mapper = mapper;
        this.clientsService = clientsService;
    }

    @Override
    public ApiResponse<AssetOffshoreRecordDto> findAll(Integer pageNo, Integer pageSize) {
        Page<AssetOffshore> offshorePage = service.findAll(pageNo, pageSize);
        return HelperResponse.buildApiResponse(offshorePage, mapper, true, 200, true, AppConstants.LIST_MESSAGE, null);

    }

    @Override
    public ApiResponse<AssetOffshoreRecordDto> findAllByUserId(Long clientId, Integer pageNo, Integer pageSize) {
        Optional<Client> client = clientsService.findById(clientId);

        if (client.isEmpty() || !client.get().getId().equals(clientId)) {
            throw new RecordNotFoundException("Failed to find client record with Id " + clientId);
        }
        Page<AssetOffshore> offshorePage = service.findAllByUserId(client.get(), pageNo, pageSize);
        return HelperResponse.buildApiResponse(offshorePage, mapper, true, 200, true, AppConstants.LIST_MESSAGE, null);

    }

    @Override
    public ApiResponse<AssetOffshoreRecordDto> findById(Long id) {
        Optional<AssetOffshore> optionalAssetOffshore = service.findById(id);

        return optionalAssetOffshore.map(offshore -> HelperResponse.buildApiResponse(null, null, false, 200, true, AppConstants.FOUND_MESSAGE, mapper.apply(offshore)))
            .orElseThrow(() -> new RecordNotFoundException("Failed to find offshore record with Id of " + id));
    }


    @Override
    public ApiResponse<AssetOffshoreRecordDto> save(AssetOffshoreRequest offshoreRequest) {
        Optional<Client> optionalClient = clientsService.findById(offshoreRequest.getClientId());
        if (optionalClient.isEmpty()) {
            throw new RecordNotFoundException("Failed to find client with Id " + offshoreRequest.getClientId());
        }
        AssetOffshoreRecordDto recordDto = AssetOffshoreRecordDto.builder()
            .description(offshoreRequest.getDescription())
            .value(offshoreRequest.getAssetValue())
            .userId(optionalClient.get())
            .recordStatus(RecordStatus.ACTIVE)
            .build();

        AssetOffshore offshore = service.save(new AssetOffshore(recordDto));
        return HelperResponse.buildApiResponse(null, null, false, 201, true, AppConstants.SUCCESS_MESSAGE, mapper.apply(offshore));

    }


    @Override
    public ApiResponse<AssetOffshoreRecordDto> update(Long id, AssetOffshoreRecordDto recordDto) {
        Optional<AssetOffshore> offshore = service.findById(id);

        if (offshore.isEmpty() || !offshore.get().getId().equals(id)) {
            throw new RecordNotFoundException("Failed to find offshore record with Id " + id);
        }
        AssetOffshore updatedOffshore = service.update(id, new AssetOffshore(recordDto));
        AssetOffshoreRecordDto mappedDto = mapper.apply(updatedOffshore);
        return HelperResponse.buildApiResponse(null, null, false, 200, true, AppConstants.SUCCESS_MESSAGE, mappedDto);
    }

    @Override
    public ApiResponse<AssetOffshoreRecordDto> deleteById(Long id) {
        Optional<AssetOffshore> offshore = service.findById(id);

        if (offshore.isEmpty() || !offshore.get().getId().equals(id)) {
            throw new RecordNotFoundException("Failed to find offshore record with Id " + id);
        }
        service.deleteById(id);
        return HelperResponse.buildApiResponse(null, null, false, 200, true, AppConstants.SUCCESS_MESSAGE, null);
    }
}
