package zw.co.zim.willplatform.processor.impl;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import zw.co.zim.willplatform.dto.PropertyAssetRecordDto;
import zw.co.zim.willplatform.dto.mapper.PropertyAssetDtoMapper;
import zw.co.zim.willplatform.exceptions.RecordNotFoundException;
import zw.co.zim.willplatform.model.Address;
import zw.co.zim.willplatform.model.Client;
import zw.co.zim.willplatform.model.PropertyAsset;
import zw.co.zim.willplatform.processor.PropertyAssetProcessor;
import zw.co.zim.willplatform.service.ClientsService;
import zw.co.zim.willplatform.service.PropertyAssetService;
import zw.co.zim.willplatform.utils.AppConstants;
import zw.co.zim.willplatform.utils.enums.RecordStatus;
import zw.co.zim.willplatform.utils.messages.request.PropertyAssetRequest;
import zw.co.zim.willplatform.utils.messages.response.basic.ApiResponse;
import zw.co.zim.willplatform.utils.messages.response.helper.HelperResponse;

import java.util.Objects;
import java.util.Optional;

@Service
public class PropertyAssetProcessorImpl implements PropertyAssetProcessor {
    private final PropertyAssetService propertyAssetService;
    private final PropertyAssetDtoMapper mapper;
    private final ClientsService clientsService;

    public PropertyAssetProcessorImpl(PropertyAssetService propertyAssetService, PropertyAssetDtoMapper mapper, ClientsService clientsService) {
        this.propertyAssetService = propertyAssetService;
        this.mapper = mapper;
        this.clientsService = clientsService;
    }

    @Override
    public ApiResponse<PropertyAssetRecordDto> findAll(Integer pageNo, Integer pageSize) {
        Page<PropertyAsset> propertyAssets = propertyAssetService.findAll(pageNo, pageSize);
        return HelperResponse.buildApiResponse(propertyAssets, mapper, true, 200, true, AppConstants.LIST_MESSAGE, null);
    }

    @Override
    public ApiResponse<PropertyAssetRecordDto> save(PropertyAssetRequest propertyAssetRequest) {
        Optional<Client> optionalClient = clientsService.findById(propertyAssetRequest.getClientId());
        if (optionalClient.isEmpty()) {
            throw new RecordNotFoundException("Failed to find client with Id " + propertyAssetRequest.getClientId());
        }

        PropertyAssetRecordDto recordDto = buildPropertyAssetRecordDto(propertyAssetRequest, optionalClient.get());

        PropertyAsset propertyAsset = propertyAssetService.save(new PropertyAsset(recordDto));
        return HelperResponse.buildApiResponse(null, null, false, 201, true, AppConstants.SUCCESS_MESSAGE, mapper.apply(propertyAsset));
    }

    @Override
    public ApiResponse<PropertyAssetRecordDto> findAllByUserId(Long clientId, Integer pageNo, Integer pageSize) {
        Optional<Client> client = clientsService.findById(clientId);

        if (client.isEmpty() || !client.get().getId().equals(clientId)) {
            throw new RecordNotFoundException("Failed to find client record with Id " + clientId);
        }
        Page<PropertyAsset> propertyAssets = propertyAssetService.findAllByUserId(client.get(), pageNo, pageSize);
        return HelperResponse.buildApiResponse(propertyAssets, mapper, true, 200, true, AppConstants.LIST_MESSAGE, null);
    }

    @Override
    public ApiResponse<PropertyAssetRecordDto> findById(Long id) {
        Optional<PropertyAsset> optional = propertyAssetService.findById(id);

        return optional.map(property -> HelperResponse.buildApiResponse(null, null, false, 200, true, AppConstants.FOUND_MESSAGE, mapper.apply(property)))
            .orElseThrow(() -> new RecordNotFoundException("Failed to find asset property record with id of " + id));
    }


    @Override
    public ApiResponse<PropertyAssetRecordDto> update(Long id, PropertyAssetRecordDto propertyAssetRecordDto) {
        Optional<PropertyAsset> propertyAsset = propertyAssetService.findById(id);

        if (propertyAsset.isEmpty() || !Objects.equals(propertyAsset.get().getId(), id)) {
            throw new RecordNotFoundException("Failed to find asset property record with Id " + id);
        }
        PropertyAsset updatedRecord = propertyAssetService.update(id, new PropertyAsset(propertyAssetRecordDto));
        PropertyAssetRecordDto mappedDto = mapper.apply(updatedRecord);
        return HelperResponse.buildApiResponse(null, null, false, 200, true, AppConstants.SUCCESS_MESSAGE, mappedDto);
    }

    @Override
    public ApiResponse<PropertyAssetRecordDto> deleteById(Long id) {
        Optional<PropertyAsset> propertyAsset = propertyAssetService.findById(id);

        if (propertyAsset.isEmpty() || !propertyAsset.get().getId().equals(id)) {
            throw new RecordNotFoundException("Failed to find asset property record with Id " + id);
        }
        propertyAssetService.deleteById(id);
        return HelperResponse.buildApiResponse(null, null, false, 200, true, AppConstants.SUCCESS_MESSAGE, null);
    }

    private PropertyAssetRecordDto buildPropertyAssetRecordDto(PropertyAssetRequest assetRequest, Client client) {
        return PropertyAssetRecordDto.builder()
            .user(client)
            .propertyName(assetRequest.getPropertyName())
            .address(buidAddress(assetRequest))
            .description(assetRequest.getDescription())
            .value(assetRequest.getPropertyValue())
            .haveABond(assetRequest.getHaveABond())
            .bondIsWith(assetRequest.getBondIsWith())
            .inYourName(assetRequest.getInYourName())
            .isFarm(assetRequest.getIsFarm())
            .personPropertyIsUnder(assetRequest.getPersonPropertyIsUnder())
            .youHoldDeed(assetRequest.getYouHoldDeed())
            .personWhoHoldsDeed(assetRequest.getPersonWhoHoldsDeed())
            .additionalInformation(assetRequest.getAdditionalInformation())
            .recordStatus(RecordStatus.ACTIVE)
            .build();
    }

    private Address buidAddress(PropertyAssetRequest assetRequest) {
        return Address.builder()
            .street(assetRequest.getAddressStreet())
            .suburb(assetRequest.getAddressSuburb())
            .city(assetRequest.getAddressCity())
            .province(assetRequest.getAddressProvince())
            .country(assetRequest.getAddressCountry())
            .build();
    }

}
