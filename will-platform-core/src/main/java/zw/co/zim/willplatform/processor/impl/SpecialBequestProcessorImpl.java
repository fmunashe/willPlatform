package zw.co.zim.willplatform.processor.impl;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import zw.co.zim.willplatform.dto.SpecialBequestDto;
import zw.co.zim.willplatform.dto.mapper.SpecialBequestDtoMapper;
import zw.co.zim.willplatform.exceptions.RecordNotFoundException;
import zw.co.zim.willplatform.model.Client;
import zw.co.zim.willplatform.model.SpecialBequest;
import zw.co.zim.willplatform.processor.SpecialBequestProcessor;
import zw.co.zim.willplatform.service.ClientsService;
import zw.co.zim.willplatform.service.SpecialBequestService;
import zw.co.zim.willplatform.utils.AppConstants;
import zw.co.zim.willplatform.utils.enums.RecordStatus;
import zw.co.zim.willplatform.utils.messages.request.SpecialBequestRequest;
import zw.co.zim.willplatform.utils.messages.response.basic.ApiResponse;
import zw.co.zim.willplatform.utils.messages.response.helper.HelperResponse;

import java.util.Objects;
import java.util.Optional;

@Service
public class SpecialBequestProcessorImpl implements SpecialBequestProcessor {
    private final SpecialBequestService bequestService;
    private final ModelMapper modelMapper;
    private final SpecialBequestDtoMapper mapper;
    private final ClientsService clientsService;

    public SpecialBequestProcessorImpl(SpecialBequestService bequestService, ModelMapper modelMapper, SpecialBequestDtoMapper mapper, ClientsService clientsService) {
        this.bequestService = bequestService;
        this.modelMapper = modelMapper;
        this.mapper = mapper;
        this.clientsService = clientsService;
    }

    @Override
    public ApiResponse<SpecialBequestDto> findAll(Integer pageNo, Integer pageSize) {
        Page<SpecialBequest> moneyPage = bequestService.findAll(pageNo, pageSize);
        return HelperResponse.buildApiResponse(moneyPage, mapper, true, 200, true, AppConstants.LIST_MESSAGE, null);
    }

    @Override
    public ApiResponse<SpecialBequestDto> findById(Long id) {
        Optional<SpecialBequest> optional = bequestService.findById(id);

        return optional.map(bequest -> HelperResponse.buildApiResponse(null, null, false, 200, true, AppConstants.FOUND_MESSAGE, mapper.apply(bequest)))
            .orElseThrow(() -> new RecordNotFoundException("Failed to find a special bequest record with Id of " + id));

    }

    @Override
    public ApiResponse<SpecialBequestDto> save(SpecialBequestRequest specialBequestRequest) {
        Optional<Client> optionalClient = clientsService.findById(specialBequestRequest.getClientId());
        if (optionalClient.isEmpty()) {
            throw new RecordNotFoundException("Failed to find client with Id " + specialBequestRequest.getClientId());
        }
        SpecialBequestDto recordDto = SpecialBequestDto.builder()
            .userId(optionalClient.get())
            .personDetails(specialBequestRequest.getPersonDetails())
            .specialBequestInformation(specialBequestRequest.getSpecialBequestInformation())
            .recordStatus(RecordStatus.ACTIVE)
            .build();

        SpecialBequest bequest = modelMapper.map(recordDto, SpecialBequest.class);
        bequest = bequestService.save(bequest);
        return HelperResponse.buildApiResponse(null, null, false, 201, true, AppConstants.SUCCESS_MESSAGE, mapper.apply(bequest));

    }

    @Override
    public ApiResponse<SpecialBequestDto> update(Long id, SpecialBequestDto specialBequestDto) {

        Optional<SpecialBequest> bequest = bequestService.findById(id);

        if (bequest.isEmpty() || !Objects.equals(bequest.get().getId(), id)) {
            throw new RecordNotFoundException("Failed to find special bequest record with Id " + id);
        }
        SpecialBequest updatedRecord = bequestService.update(id, modelMapper.map(specialBequestDto, SpecialBequest.class));
        SpecialBequestDto mappedDto = mapper.apply(updatedRecord);
        return HelperResponse.buildApiResponse(null, null, false, 200, true, AppConstants.SUCCESS_MESSAGE, mappedDto);
    }

    @Override
    public ApiResponse<SpecialBequestDto> deleteById(Long id) {
        Optional<SpecialBequest> bequest = bequestService.findById(id);

        if (bequest.isEmpty() || !bequest.get().getId().equals(id)) {
            throw new RecordNotFoundException("Failed to find special bequest record with Id " + id);
        }
        bequestService.deleteById(id);
        return HelperResponse.buildApiResponse(null, null, false, 200, true, AppConstants.SUCCESS_MESSAGE, null);

    }

    @Override
    public ApiResponse<SpecialBequestDto> findFirstByUserId(Long clientId) {
        Optional<Client> client = clientsService.findById(clientId);

        if (client.isEmpty() || !client.get().getId().equals(clientId)) {
            throw new RecordNotFoundException("Failed to find client record with Id " + clientId);
        }
        Optional<SpecialBequest> bequest = bequestService.findFirstByUserId(client.get());

        if (bequest.isEmpty() || !bequest.get().getId().equals(clientId)) {
            throw new RecordNotFoundException("Failed to find special bequest for client id " + clientId);
        }
        return HelperResponse.buildApiResponse(null, null, true, 200, true, AppConstants.LIST_MESSAGE, mapper.apply(bequest.get()));
    }
}
