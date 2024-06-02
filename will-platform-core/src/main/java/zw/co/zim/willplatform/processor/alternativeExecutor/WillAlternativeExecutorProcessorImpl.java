package zw.co.zim.willplatform.processor.alternativeExecutor;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import zw.co.zim.willplatform.dto.WillAlternativeExecutorDto;
import zw.co.zim.willplatform.dto.mapper.WillAlternativeExecutorDtoMapper;
import zw.co.zim.willplatform.exceptions.RecordNotFoundException;
import zw.co.zim.willplatform.model.Address;
import zw.co.zim.willplatform.model.Client;
import zw.co.zim.willplatform.model.WillAlternativeExecutor;
import zw.co.zim.willplatform.service.ClientsService;
import zw.co.zim.willplatform.service.WillAlternativeExecutorService;
import zw.co.zim.willplatform.utils.AppConstants;
import zw.co.zim.willplatform.utils.enums.RecordStatus;
import zw.co.zim.willplatform.utils.messages.request.AlternativeExecutorRequest;
import zw.co.zim.willplatform.utils.messages.response.basic.ApiResponse;
import zw.co.zim.willplatform.utils.messages.response.helper.HelperResponse;

import java.util.Objects;
import java.util.Optional;

@Service
public final class WillAlternativeExecutorProcessorImpl implements WillAlternativeExecutorProcessor {
    private final WillAlternativeExecutorService executorService;
    private final WillAlternativeExecutorDtoMapper mapper;
    private final ModelMapper modelMapper;
    private final ClientsService clientsService;

    public WillAlternativeExecutorProcessorImpl(WillAlternativeExecutorService executorService, WillAlternativeExecutorDtoMapper mapper, ModelMapper modelMapper, ClientsService clientsService) {
        this.executorService = executorService;
        this.mapper = mapper;
        this.modelMapper = modelMapper;
        this.clientsService = clientsService;
    }

    @Override
    public ApiResponse<WillAlternativeExecutorDto> findAll(Integer pageNo, Integer pageSize) {
        Page<WillAlternativeExecutor> executorPage = executorService.findAll(pageNo, pageSize);
        return HelperResponse.buildApiResponse(executorPage, mapper, true, 200, true, AppConstants.LIST_MESSAGE, null);

    }

    @Override
    public ApiResponse<WillAlternativeExecutorDto> findById(Long id) {
        Optional<WillAlternativeExecutor> optional = executorService.findById(id);

        return optional.map(executor -> HelperResponse.buildApiResponse(null, null, false, 200, true, AppConstants.FOUND_MESSAGE, mapper.apply(executor)))
            .orElseThrow(() -> new RecordNotFoundException("Failed to find will alternative executor record with Id of " + id));

    }

    @Override
    public ApiResponse<WillAlternativeExecutorDto> findAllByUserId(Long clientId, Integer pageNo, Integer pageSize) {

        Optional<Client> optionalClient = clientsService.findById(clientId);
        if (optionalClient.isEmpty()) {
            throw new RecordNotFoundException("Failed to find client with Id " + clientId);
        }
        Page<WillAlternativeExecutor> executorPage = executorService.findAllByUserId(optionalClient.get(), pageNo, pageSize);

        return HelperResponse.buildApiResponse(executorPage, mapper, true, 200, true, AppConstants.LIST_MESSAGE, null);
    }

    @Override
    public ApiResponse<WillAlternativeExecutorDto> save(AlternativeExecutorRequest alternativeExecutorRequest) {
        Optional<Client> optionalClient = clientsService.findById(alternativeExecutorRequest.getClientId());
        if (optionalClient.isEmpty()) {
            throw new RecordNotFoundException("Failed to find client with Id " + alternativeExecutorRequest.getClientId());
        }

        WillAlternativeExecutorDto recordDto = getAlternativeExecutorDto(alternativeExecutorRequest, optionalClient.get());


        WillAlternativeExecutor executor = modelMapper.map(recordDto, WillAlternativeExecutor.class);
        executor = executorService.save(executor);
        return HelperResponse.buildApiResponse(null, null, false, 201, true, AppConstants.SUCCESS_MESSAGE, mapper.apply(executor));

    }


    @Override
    public ApiResponse<WillAlternativeExecutorDto> update(Long id, WillAlternativeExecutorDto willAlternativeExecutorDto) {
        Optional<WillAlternativeExecutor> executor = executorService.findById(id);

        if (executor.isEmpty() || !Objects.equals(executor.get().getId(), id)) {
            throw new RecordNotFoundException("Failed to find will alternative executor record with Id " + id);
        }

        WillAlternativeExecutor updatedRecord = executorService.update(id, modelMapper.map(willAlternativeExecutorDto, WillAlternativeExecutor.class));
        WillAlternativeExecutorDto mappedDto = mapper.apply(updatedRecord);
        return HelperResponse.buildApiResponse(null, null, false, 200, true, AppConstants.SUCCESS_MESSAGE, mappedDto);

    }

    @Override
    public ApiResponse<WillAlternativeExecutorDto> deleteById(Long id) {
        Optional<WillAlternativeExecutor> executor = executorService.findById(id);

        if (executor.isEmpty() || !executor.get().getId().equals(id)) {
            throw new RecordNotFoundException("Failed to find will alternative executor record with Id " + id);
        }
        executorService.deleteById(id);
        return HelperResponse.buildApiResponse(null, null, false, 200, true, AppConstants.SUCCESS_MESSAGE, null);

    }

    private static Address getAddress(AlternativeExecutorRequest alternativeExecutorRequest) {
        return Address.builder()
            .street(alternativeExecutorRequest.getStreet())
            .city(alternativeExecutorRequest.getCity())
            .suburb(alternativeExecutorRequest.getSuburb())
            .province(alternativeExecutorRequest.getProvince())
            .country(alternativeExecutorRequest.getCountry())
            .build();
    }

    private WillAlternativeExecutorDto getAlternativeExecutorDto(AlternativeExecutorRequest alternativeExecutorRequest, Client client) {
        return WillAlternativeExecutorDto.builder()
            .userId(client)
            .email(alternativeExecutorRequest.getEmail())
            .executorDetails(alternativeExecutorRequest.getExecutorDetails())
            .contactNumber(alternativeExecutorRequest.getContactNumber())
            .address(getAddress(alternativeExecutorRequest))
            .recordStatus(RecordStatus.ACTIVE)
            .build();
    }
}
