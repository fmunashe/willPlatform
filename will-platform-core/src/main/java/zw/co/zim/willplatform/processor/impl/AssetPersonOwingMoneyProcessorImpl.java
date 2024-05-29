package zw.co.zim.willplatform.processor.impl;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import zw.co.zim.willplatform.dto.AssetPersonOwingMoneyRecordDto;
import zw.co.zim.willplatform.dto.mapper.AssetPersonOwingMoneyDtoMapper;
import zw.co.zim.willplatform.exceptions.RecordNotFoundException;
import zw.co.zim.willplatform.model.AssetPersonOwingMoney;
import zw.co.zim.willplatform.model.Client;
import zw.co.zim.willplatform.processor.AssetPersonOwingMoneyProcessor;
import zw.co.zim.willplatform.service.AssetPersonOwingMoneyService;
import zw.co.zim.willplatform.service.ClientsService;
import zw.co.zim.willplatform.utils.AppConstants;
import zw.co.zim.willplatform.utils.enums.RecordStatus;
import zw.co.zim.willplatform.utils.messages.request.AssetPersonOwingMoneyRequest;
import zw.co.zim.willplatform.utils.messages.response.basic.ApiResponse;
import zw.co.zim.willplatform.utils.messages.response.helper.HelperResponse;

import java.util.Objects;
import java.util.Optional;

@Service
public class AssetPersonOwingMoneyProcessorImpl implements AssetPersonOwingMoneyProcessor {
    private final AssetPersonOwingMoneyService service;
    private final AssetPersonOwingMoneyDtoMapper mapper;
    private final ClientsService clientsService;

    public AssetPersonOwingMoneyProcessorImpl(AssetPersonOwingMoneyService service, AssetPersonOwingMoneyDtoMapper mapper, ClientsService clientsService) {
        this.service = service;
        this.mapper = mapper;
        this.clientsService = clientsService;
    }


    @Override
    public ApiResponse<AssetPersonOwingMoneyRecordDto> findById(Long id) {
        Optional<AssetPersonOwingMoney> optional = service.findById(id);

        return optional.map(personOwingMoney -> HelperResponse.buildApiResponse(null, null, false, 200, true, AppConstants.FOUND_MESSAGE, mapper.apply(personOwingMoney)))
            .orElseThrow(() -> new RecordNotFoundException("Failed to find person owing money record with id of " + id));
    }

    @Override
    public ApiResponse<AssetPersonOwingMoneyRecordDto> findAllByUserId(Long clientId, Integer pageNo, Integer pageSize) {
        Optional<Client> client = clientsService.findById(clientId);

        if (client.isEmpty() || !client.get().getId().equals(clientId)) {
            throw new RecordNotFoundException("Failed to find client record with Id " + clientId);
        }
        Page<AssetPersonOwingMoney> personOwingMoneyPage = service.findAllByUserId(client.get(), pageNo, pageSize);
        return HelperResponse.buildApiResponse(personOwingMoneyPage, mapper, true, 200, true, AppConstants.LIST_MESSAGE, null);
    }

    @Override
    public ApiResponse<AssetPersonOwingMoneyRecordDto> findAll(Integer pageNo, Integer pageSize) {
        Page<AssetPersonOwingMoney> personOwingMoneyPage = service.findAll(pageNo, pageSize);
        return HelperResponse.buildApiResponse(personOwingMoneyPage, mapper, true, 200, true, AppConstants.LIST_MESSAGE, null);
    }

    @Override
    public ApiResponse<AssetPersonOwingMoneyRecordDto> save(AssetPersonOwingMoneyRequest assetPersonOwingMoneyRequest) {
        Optional<Client> optionalClient = clientsService.findById(assetPersonOwingMoneyRequest.getClientId());
        if (optionalClient.isEmpty()) {
            throw new RecordNotFoundException("Failed to find client with Id " + assetPersonOwingMoneyRequest.getClientId());
        }
        AssetPersonOwingMoneyRecordDto recordDto = AssetPersonOwingMoneyRecordDto.builder()
            .amountOwed(assetPersonOwingMoneyRequest.getAmountOwed())
            .personDetails(assetPersonOwingMoneyRequest.getPersonDetails())
            .contactNumber(assetPersonOwingMoneyRequest.getContactNumber())
            .userId(optionalClient.get())
            .recordStatus(RecordStatus.ACTIVE)
            .build();

        AssetPersonOwingMoney personOwingMoney = service.save(new AssetPersonOwingMoney(recordDto));
        return HelperResponse.buildApiResponse(null, null, false, 201, true, AppConstants.SUCCESS_MESSAGE, mapper.apply(personOwingMoney));

    }

    @Override
    public ApiResponse<AssetPersonOwingMoneyRecordDto> update(Long id, AssetPersonOwingMoneyRecordDto recordDto) {
        Optional<AssetPersonOwingMoney> personOwingMoney = service.findById(id);

        if (personOwingMoney.isEmpty() || !Objects.equals(personOwingMoney.get().getId(), id)) {
            throw new RecordNotFoundException("Failed to find person owing money record with Id " + id);
        }
        AssetPersonOwingMoney updatedRecord = service.update(id, new AssetPersonOwingMoney(recordDto));
        AssetPersonOwingMoneyRecordDto mappedDto = mapper.apply(updatedRecord);
        return HelperResponse.buildApiResponse(null, null, false, 200, true, AppConstants.SUCCESS_MESSAGE, mappedDto);
    }

    @Override
    public ApiResponse<AssetPersonOwingMoneyRecordDto> deleteById(Long id) {
        Optional<AssetPersonOwingMoney> personOwingMoney = service.findById(id);

        if (personOwingMoney.isEmpty() || !personOwingMoney.get().getId().equals(id)) {
            throw new RecordNotFoundException("Failed to find person owing money record with Id " + id);
        }
        service.deleteById(id);
        return HelperResponse.buildApiResponse(null, null, false, 200, true, AppConstants.SUCCESS_MESSAGE, null);
    }
}
