package zw.co.zim.willplatform.processor.impl;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import zw.co.zim.willplatform.dto.VehicleAssetRecordDto;
import zw.co.zim.willplatform.dto.mapper.VehicleAssetDtoMapper;
import zw.co.zim.willplatform.exceptions.RecordExistsException;
import zw.co.zim.willplatform.exceptions.RecordNotFoundException;
import zw.co.zim.willplatform.model.BankAsset;
import zw.co.zim.willplatform.model.Client;
import zw.co.zim.willplatform.model.VehicleAsset;
import zw.co.zim.willplatform.processor.VehicleAssetProcessor;
import zw.co.zim.willplatform.service.ClientsService;
import zw.co.zim.willplatform.service.VehicleAssetService;
import zw.co.zim.willplatform.utils.AppConstants;
import zw.co.zim.willplatform.utils.enums.RecordStatus;
import zw.co.zim.willplatform.utils.messages.request.VehicleAssetRequest;
import zw.co.zim.willplatform.utils.messages.response.basic.ApiResponse;
import zw.co.zim.willplatform.utils.messages.response.helper.HelperResponse;

import java.util.Objects;
import java.util.Optional;

@Service
public class VehicleAssetProcessorImpl implements VehicleAssetProcessor {
    private final VehicleAssetService vehicleAssetService;
    private final VehicleAssetDtoMapper mapper;
    private final ClientsService clientsService;

    public VehicleAssetProcessorImpl(VehicleAssetService vehicleAssetService, VehicleAssetDtoMapper vehicleAssetMapper, ClientsService clientsService) {
        this.vehicleAssetService = vehicleAssetService;
        this.mapper = vehicleAssetMapper;
        this.clientsService = clientsService;
    }

    @Override
    public ApiResponse<VehicleAssetRecordDto> findAll(Integer pageNo, Integer pageSize) {
        Page<VehicleAsset> vehicleAssets = vehicleAssetService.findAll(pageNo, pageSize);
        return HelperResponse.buildApiResponse(vehicleAssets, mapper, true, 200, true, AppConstants.LIST_MESSAGE, null);

    }

    @Override
    public ApiResponse<VehicleAssetRecordDto> save(VehicleAssetRequest vehicleAssetRequest) {
        Optional<Client> optionalClient = clientsService.findById(vehicleAssetRequest.getClientId());
        if (optionalClient.isEmpty()) {
            throw new RecordNotFoundException("Failed to find client with Id " + vehicleAssetRequest.getClientId());
        }

        Optional<VehicleAsset> optionalRegNumber = vehicleAssetService.findVehicleByRegNumber(vehicleAssetRequest.getRegistrationNumber());
        if (optionalRegNumber.isPresent()) {
            throw new RecordExistsException("Vehicle already registered for registration number " + vehicleAssetRequest.getRegistrationNumber());
        }
        Optional<VehicleAsset> optionalEngineNumber = vehicleAssetService.findVehicleByEngineNumber(vehicleAssetRequest.getEngineNumber());
        if (optionalEngineNumber.isPresent()) {
            throw new RecordExistsException("Vehicle already registered for engine number " + vehicleAssetRequest.getEngineNumber());
        }

        VehicleAssetRecordDto recordDto = VehicleAssetRecordDto.builder()
            .user(optionalClient.get())
            .color(vehicleAssetRequest.getColor())
            .model(vehicleAssetRequest.getModel())
            .make(vehicleAssetRequest.getMake())
            .engineNumber(vehicleAssetRequest.getEngineNumber())
            .registrationNumber(vehicleAssetRequest.getRegistrationNumber())
            .registrationPaperWith(vehicleAssetRequest.getRegistrationPaperWith())
            .manufactureYear(vehicleAssetRequest.getManufactureYear())
            .value(vehicleAssetRequest.getValue())
            .fullyPaid(vehicleAssetRequest.getFullyPaid())
            .recordStatus(RecordStatus.ACTIVE)
            .build();

        VehicleAsset vehicleAsset = vehicleAssetService.save(new VehicleAsset(recordDto));
        return HelperResponse.buildApiResponse(null, null, false, 201, true, AppConstants.SUCCESS_MESSAGE, mapper.apply(vehicleAsset));
    }

    @Override
    public ApiResponse<VehicleAssetRecordDto> findVehicleByEngineNumber(String engineNumber) {
        Optional<VehicleAsset> optional = vehicleAssetService.findVehicleByEngineNumber(engineNumber);

        return optional.map(bank -> HelperResponse.buildApiResponse(null, null, false, 200, true, AppConstants.FOUND_MESSAGE, mapper.apply(bank)))
            .orElseThrow(() -> new RecordNotFoundException("Failed to find vehicle record with engine number of " + engineNumber));

    }

    @Override
    public ApiResponse<VehicleAssetRecordDto> findById(Long id) {
        Optional<VehicleAsset> optional = vehicleAssetService.findById(id);

        return optional.map(vehicle -> HelperResponse.buildApiResponse(null, null, false, 200, true, AppConstants.FOUND_MESSAGE, mapper.apply(vehicle)))
            .orElseThrow(() -> new RecordNotFoundException("Failed to find vehicle record with id of " + id));

    }


    @Override
    public ApiResponse<VehicleAssetRecordDto> update(Long id, VehicleAssetRecordDto vehicleAssetRecordDto) {
        Optional<VehicleAsset> vehicleAsset = vehicleAssetService.findById(id);

        if (vehicleAsset.isEmpty() || !Objects.equals(vehicleAsset.get().getId(), id)) {
            throw new RecordNotFoundException("Failed to find asset bank record with Id " + id);
        }
        VehicleAsset updatedRecord = vehicleAssetService.update(id, new VehicleAsset(vehicleAssetRecordDto));
        VehicleAssetRecordDto mappedDto = mapper.apply(updatedRecord);
        return HelperResponse.buildApiResponse(null, null, false, 200, true, AppConstants.SUCCESS_MESSAGE, mappedDto);

    }

    @Override
    public ApiResponse<VehicleAssetRecordDto> deleteById(Long id) {
        Optional<VehicleAsset> vehicleAsset = vehicleAssetService.findById(id);

        if (vehicleAsset.isEmpty() || !vehicleAsset.get().getId().equals(id)) {
            throw new RecordNotFoundException("Failed to find vehicle record with Id " + id);
        }
        vehicleAssetService.deleteById(id);
        return HelperResponse.buildApiResponse(null, null, false, 200, true, AppConstants.SUCCESS_MESSAGE, null);
    }

    @Override
    public ApiResponse<VehicleAssetRecordDto> findVehicleByRegNumber(String regNumber) {
        Optional<VehicleAsset> optional = vehicleAssetService.findVehicleByRegNumber(regNumber);

        return optional.map(vehicle -> HelperResponse.buildApiResponse(null, null, false, 200, true, AppConstants.FOUND_MESSAGE, mapper.apply(vehicle)))
            .orElseThrow(() -> new RecordNotFoundException("Failed to find vehicle record with registration number of " + regNumber));

    }

    @Override
    public ApiResponse<VehicleAssetRecordDto> findAllByUserId(Long clientId, Integer pageNo, Integer pageSize) {
        Optional<Client> client = clientsService.findById(clientId);

        if (client.isEmpty() || !client.get().getId().equals(clientId)) {
            throw new RecordNotFoundException("Failed to find client record with Id " + clientId);
        }
        Page<VehicleAsset> bankAssets = vehicleAssetService.findAllByUserId(client.get(), pageNo, pageSize);
        return HelperResponse.buildApiResponse(bankAssets, mapper, true, 200, true, AppConstants.LIST_MESSAGE, null);

    }
}
