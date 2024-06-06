package zw.co.zim.willplatform.processor.impl;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import zw.co.zim.willplatform.dto.LiabilitiesSubscribedWrittenPublicationDto;
import zw.co.zim.willplatform.dto.mapper.WrittenPublicationDtoMapper;
import zw.co.zim.willplatform.exceptions.RecordNotFoundException;
import zw.co.zim.willplatform.model.Client;
import zw.co.zim.willplatform.model.LiabilitiesSubscribedWrittenPublication;
import zw.co.zim.willplatform.processor.LiabilitiesSubscribedWrittenPublicationProcessor;
import zw.co.zim.willplatform.service.ClientsService;
import zw.co.zim.willplatform.service.LiabilitiesSubscribedWrittenPublicationService;
import zw.co.zim.willplatform.utils.AppConstants;
import zw.co.zim.willplatform.utils.enums.RecordStatus;
import zw.co.zim.willplatform.utils.messages.request.WrittenPublicationRequest;
import zw.co.zim.willplatform.utils.messages.response.basic.ApiResponse;
import zw.co.zim.willplatform.utils.messages.response.helper.HelperResponse;

import java.util.Objects;
import java.util.Optional;

@Service
public class LiabilitiesSubscribedWrittenPublicationProcessorImpl implements LiabilitiesSubscribedWrittenPublicationProcessor {
    private final LiabilitiesSubscribedWrittenPublicationService service;
    private final ClientsService clientsService;
    private final ModelMapper modelMapper;
    private final WrittenPublicationDtoMapper mapper;

    public LiabilitiesSubscribedWrittenPublicationProcessorImpl(LiabilitiesSubscribedWrittenPublicationService service, ClientsService clientsService, ModelMapper modelMapper, WrittenPublicationDtoMapper mapper) {
        this.service = service;
        this.clientsService = clientsService;
        this.modelMapper = modelMapper;
        this.mapper = mapper;
    }

    @Override
    public ApiResponse<LiabilitiesSubscribedWrittenPublicationDto> findAll(Integer pageNo, Integer pageSize) {
        Page<LiabilitiesSubscribedWrittenPublication> publications = service.findAll(pageNo, pageSize);
        return HelperResponse.buildApiResponse(publications, mapper, true, 200, true, AppConstants.SUCCESS_MESSAGE, null);

    }

    @Override
    public ApiResponse<LiabilitiesSubscribedWrittenPublicationDto> findById(Long id) {
        Optional<LiabilitiesSubscribedWrittenPublication> optional = service.findById(id);

        return optional.map(loan -> HelperResponse.buildApiResponse(null, null, false, 200, true, AppConstants.FOUND_MESSAGE, mapper.apply(loan)))
            .orElseThrow(() -> new RecordNotFoundException("Failed to find publication record with Id of " + id));

    }

    @Override
    public ApiResponse<LiabilitiesSubscribedWrittenPublicationDto> save(WrittenPublicationRequest writtenPublicationRequest) {
        Optional<Client> optionalClient = clientsService.findById(writtenPublicationRequest.getClientId());
        if (optionalClient.isEmpty()) {
            throw new RecordNotFoundException("Failed to find client with Id " + writtenPublicationRequest.getClientId());
        }
        LiabilitiesSubscribedWrittenPublicationDto recordDto = LiabilitiesSubscribedWrittenPublicationDto.builder()
            .userId(optionalClient.get())
            .nameOfPublication(writtenPublicationRequest.getNameOfPublication())
            .recordStatus(RecordStatus.ACTIVE)
            .build();

        LiabilitiesSubscribedWrittenPublication publication = modelMapper.map(recordDto, LiabilitiesSubscribedWrittenPublication.class);
        publication = service.save(publication);
        return HelperResponse.buildApiResponse(null, null, false, 201, true, AppConstants.SUCCESS_MESSAGE, mapper.apply(publication));

    }

    @Override
    public ApiResponse<LiabilitiesSubscribedWrittenPublicationDto> update(Long id, LiabilitiesSubscribedWrittenPublicationDto liabilitiesSubscribedWrittenPublicationDto) {
        Optional<LiabilitiesSubscribedWrittenPublication> loan = service.findById(id);

        if (loan.isEmpty() || !Objects.equals(loan.get().getId(), id)) {
            throw new RecordNotFoundException("Failed to find publication record with Id " + id);
        }
        LiabilitiesSubscribedWrittenPublication updatedRecord = service.update(id, modelMapper.map(liabilitiesSubscribedWrittenPublicationDto, LiabilitiesSubscribedWrittenPublication.class));
        LiabilitiesSubscribedWrittenPublicationDto mappedDto = mapper.apply(updatedRecord);
        return HelperResponse.buildApiResponse(null, null, false, 200, true, AppConstants.SUCCESS_MESSAGE, mappedDto);

    }

    @Override
    public ApiResponse<LiabilitiesSubscribedWrittenPublicationDto> deleteById(Long id) {
        Optional<LiabilitiesSubscribedWrittenPublication> publication = service.findById(id);

        if (publication.isEmpty() || !publication.get().getId().equals(id)) {
            throw new RecordNotFoundException("Failed to find publication record with Id " + id);
        }
        service.deleteById(id);
        return HelperResponse.buildApiResponse(null, null, false, 200, true, AppConstants.SUCCESS_MESSAGE, null);

    }

    @Override
    public ApiResponse<LiabilitiesSubscribedWrittenPublicationDto> findAllByUserId(Long clientId, Integer pageNo, Integer pageSize) {
        Optional<Client> client = clientsService.findById(clientId);

        if (client.isEmpty() || !client.get().getId().equals(clientId)) {
            throw new RecordNotFoundException("Failed to find client record with Id " + clientId);
        }
        Page<LiabilitiesSubscribedWrittenPublication> loanPage = service.findAllByUserId(client.get(), pageNo, pageSize);
        return HelperResponse.buildApiResponse(loanPage, mapper, true, 200, true, AppConstants.LIST_MESSAGE, null);

    }
}
