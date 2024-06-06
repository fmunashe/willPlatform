package zw.co.zim.willplatform.processor.willBurialRequest;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import zw.co.zim.willplatform.dto.WillBurialRequestDto;
import zw.co.zim.willplatform.dto.mapper.WillBurialRequestDtoMapper;
import zw.co.zim.willplatform.exceptions.RecordExistsException;
import zw.co.zim.willplatform.exceptions.RecordNotFoundException;
import zw.co.zim.willplatform.model.Client;
import zw.co.zim.willplatform.model.WillBurialRequest;
import zw.co.zim.willplatform.service.ClientsService;
import zw.co.zim.willplatform.service.WillBurialRequestService;
import zw.co.zim.willplatform.utils.AppConstants;
import zw.co.zim.willplatform.utils.enums.RecordStatus;
import zw.co.zim.willplatform.utils.messages.request.BurialRequest;
import zw.co.zim.willplatform.utils.messages.response.basic.ApiResponse;
import zw.co.zim.willplatform.utils.messages.response.helper.HelperResponse;

import java.util.Objects;
import java.util.Optional;

@Service
public final class WillBurialRequestProcessorImpl implements WillBurialRequestProcessor {
    private final WillBurialRequestService burialRequestService;
    private final ClientsService clientsService;
    private final WillBurialRequestDtoMapper mapper;
    private final ModelMapper modelMapper;

    public WillBurialRequestProcessorImpl(WillBurialRequestService burialRequestService, ClientsService clientsService, WillBurialRequestDtoMapper mapper, ModelMapper modelMapper) {
        this.burialRequestService = burialRequestService;
        this.clientsService = clientsService;
        this.mapper = mapper;
        this.modelMapper = modelMapper;
    }

    @Override
    public ApiResponse<WillBurialRequestDto> findAll(Integer pageNo, Integer pageSize) {
        Page<WillBurialRequest> burialRequestPage = burialRequestService.findAll(pageNo, pageSize);
        return HelperResponse.buildApiResponse(burialRequestPage, mapper, true, 200, true, AppConstants.LIST_MESSAGE, null);
    }

    @Override
    public ApiResponse<WillBurialRequestDto> findById(Long id) {
        Optional<WillBurialRequest> optional = burialRequestService.findById(id);

        return optional.map(burialRequest -> HelperResponse.buildApiResponse(null, null, false, 200, true, AppConstants.FOUND_MESSAGE, mapper.apply(burialRequest)))
            .orElseThrow(() -> new RecordNotFoundException("Failed to find burial request record with Id of " + id));

    }

    @Override
    public ApiResponse<WillBurialRequestDto> save(BurialRequest burialRequest) {
        Optional<Client> optionalClient = clientsService.findById(burialRequest.getClientId());
        if (optionalClient.isEmpty()) {
            throw new RecordNotFoundException("Failed to find client with Id " + burialRequest.getClientId());
        }

        Optional<WillBurialRequest> currentBurialRequest = burialRequestService.findFirstByUserId(optionalClient.get());

        if (currentBurialRequest.isPresent()) {
            throw new RecordExistsException("Client already has an existing burial request " + burialRequest.getClientId());
        }
        WillBurialRequestDto recordDto = WillBurialRequestDto.builder()
            .userId(optionalClient.get())
            .burialType(burialRequest.getBurialType())
            .burialInformation(burialRequest.getBurialInformation())
            .livingWill(burialRequest.getLivingWill())
            .recordStatus(RecordStatus.ACTIVE)
            .build();

        WillBurialRequest burial = modelMapper.map(recordDto, WillBurialRequest.class);
        burial = burialRequestService.save(burial);
        return HelperResponse.buildApiResponse(null, null, false, 201, true, AppConstants.SUCCESS_MESSAGE, mapper.apply(burial));
    }

    @Override
    public ApiResponse<WillBurialRequestDto> update(Long id, WillBurialRequestDto willBurialRequestDto) {
        Optional<WillBurialRequest> request = burialRequestService.findById(id);
        if (request.isEmpty() || !Objects.equals(request.get().getId(), id)) {
            throw new RecordNotFoundException("Failed to find a burial request record with Id " + id);
        }
        WillBurialRequest updatedRecord = burialRequestService.update(id, modelMapper.map(willBurialRequestDto, WillBurialRequest.class));
        WillBurialRequestDto mappedDto = mapper.apply(updatedRecord);
        return HelperResponse.buildApiResponse(null, null, false, 200, true, AppConstants.SUCCESS_MESSAGE, mappedDto);
    }

    @Override
    public ApiResponse<WillBurialRequestDto> deleteById(Long id) {
        Optional<WillBurialRequest> burialRequest = burialRequestService.findById(id);

        if (burialRequest.isEmpty() || !burialRequest.get().getId().equals(id)) {
            throw new RecordNotFoundException("Failed to find a burial request record with Id " + id);
        }
        burialRequestService.deleteById(id);
        return HelperResponse.buildApiResponse(null, null, false, 200, true, AppConstants.SUCCESS_MESSAGE, null);

    }

    @Override
    public ApiResponse<WillBurialRequestDto> findFirstByUserId(Long clientId) {
        Optional<Client> optionalClient = clientsService.findById(clientId);
        if (optionalClient.isEmpty()) {
            throw new RecordNotFoundException("Failed to find client with Id " + clientId);
        }
        Optional<WillBurialRequest> optional = burialRequestService.findFirstByUserId(optionalClient.get());

        return optional.map(burialRequest -> HelperResponse.buildApiResponse(null, null, false, 200, true, AppConstants.FOUND_MESSAGE, mapper.apply(burialRequest)))
            .orElseThrow(() -> new RecordNotFoundException("Failed to find burial request record for client id " + clientId));

    }
}
