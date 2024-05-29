package zw.co.zim.willplatform.processor.impl;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import zw.co.zim.willplatform.dto.AssetTimeShareDto;
import zw.co.zim.willplatform.dto.mapper.AssetTimeshareDtoMapper;
import zw.co.zim.willplatform.exceptions.RecordNotFoundException;
import zw.co.zim.willplatform.model.AssetTimeShare;
import zw.co.zim.willplatform.model.Client;
import zw.co.zim.willplatform.processor.AssetTimeshareProcessor;
import zw.co.zim.willplatform.service.AssetTimeshareService;
import zw.co.zim.willplatform.service.ClientsService;
import zw.co.zim.willplatform.utils.AppConstants;
import zw.co.zim.willplatform.utils.enums.RecordStatus;
import zw.co.zim.willplatform.utils.messages.request.TimeshareRequest;
import zw.co.zim.willplatform.utils.messages.response.basic.ApiResponse;
import zw.co.zim.willplatform.utils.messages.response.helper.HelperResponse;

import java.util.Objects;
import java.util.Optional;

@Service
public class AssetTimeshareProcessorImpl implements AssetTimeshareProcessor {
    private final AssetTimeshareService assetTimeshareService;
    private final AssetTimeshareDtoMapper mapper;
    private final ClientsService clientsService;

    public AssetTimeshareProcessorImpl(AssetTimeshareService assetTimeshareService, AssetTimeshareDtoMapper mapper, ClientsService clientsService) {
        this.assetTimeshareService = assetTimeshareService;
        this.mapper = mapper;
        this.clientsService = clientsService;
    }


    @Override
    public ApiResponse<AssetTimeShareDto> findAll(Integer pageNo, Integer pageSize) {
        Page<AssetTimeShare> timeSharePage = assetTimeshareService.findAll(pageNo, pageSize);
        return HelperResponse.buildApiResponse(timeSharePage, mapper, true, 200, true, AppConstants.LIST_MESSAGE, null);

    }

    @Override
    public ApiResponse<AssetTimeShareDto> save(TimeshareRequest timeshareRequest) {
        Optional<Client> optionalClient = clientsService.findById(timeshareRequest.getClientId());
        if (optionalClient.isEmpty()) {
            throw new RecordNotFoundException("Failed to find client with Id " + timeshareRequest.getClientId());
        }
        AssetTimeShareDto recordDto = AssetTimeShareDto.builder()
            .value(timeshareRequest.getValue())
            .userId(optionalClient.get())
            .description(timeshareRequest.getDescription())
            .recordStatus(RecordStatus.ACTIVE)
            .build();

        AssetTimeShare timeShare = assetTimeshareService.save(new AssetTimeShare(recordDto));
        return HelperResponse.buildApiResponse(null, null, false, 201, true, AppConstants.SUCCESS_MESSAGE, mapper.apply(timeShare));

    }

    @Override
    public ApiResponse<AssetTimeShareDto> findAllByUserId(Long clientId, Integer pageNo, Integer pageSize) {
        Optional<Client> client = clientsService.findById(clientId);

        if (client.isEmpty() || !client.get().getId().equals(clientId)) {
            throw new RecordNotFoundException("Failed to find client record with Id " + clientId);
        }
        Page<AssetTimeShare> timeSharePage = assetTimeshareService.findAllByUserId(client.get(), pageNo, pageSize);
        return HelperResponse.buildApiResponse(timeSharePage, mapper, true, 200, true, AppConstants.LIST_MESSAGE, null);

    }

    @Override
    public ApiResponse<AssetTimeShareDto> findById(Long id) {
        Optional<AssetTimeShare> optional = assetTimeshareService.findById(id);

        return optional.map(policy -> HelperResponse.buildApiResponse(null, null, false, 200, true, AppConstants.FOUND_MESSAGE, mapper.apply(policy)))
            .orElseThrow(() -> new RecordNotFoundException("Failed to find timeshare record with id of " + id));

    }

    @Override
    public ApiResponse<AssetTimeShareDto> update(Long id, AssetTimeShareDto assetTimeShareDto) {
        Optional<AssetTimeShare> timeShare = assetTimeshareService.findById(id);

        if (timeShare.isEmpty() || !Objects.equals(timeShare.get().getId(), id)) {
            throw new RecordNotFoundException("Failed to find timeshare record with Id " + id);
        }
        AssetTimeShare updatedRecord = assetTimeshareService.update(id, new AssetTimeShare(assetTimeShareDto));
        AssetTimeShareDto mappedDto = mapper.apply(updatedRecord);
        return HelperResponse.buildApiResponse(null, null, false, 200, true, AppConstants.SUCCESS_MESSAGE, mappedDto);

    }

    @Override
    public ApiResponse<AssetTimeShareDto> deleteById(Long id) {
        Optional<AssetTimeShare> policy = assetTimeshareService.findById(id);

        if (policy.isEmpty() || !policy.get().getId().equals(id)) {
            throw new RecordNotFoundException("Failed to find timeshare record with Id " + id);
        }
        assetTimeshareService.deleteById(id);
        return HelperResponse.buildApiResponse(null, null, false, 200, true, AppConstants.SUCCESS_MESSAGE, null);

    }
}
