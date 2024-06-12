package zw.co.zim.willplatform.processor.impl;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import zw.co.zim.willplatform.dto.TotalDemiseDto;
import zw.co.zim.willplatform.dto.mapper.TotalDemiseDtoMapper;
import zw.co.zim.willplatform.exceptions.RecordNotFoundException;
import zw.co.zim.willplatform.model.Client;
import zw.co.zim.willplatform.model.TotalDemise;
import zw.co.zim.willplatform.processor.TotalDemiseProcessor;
import zw.co.zim.willplatform.service.ClientsService;
import zw.co.zim.willplatform.service.TotalDemiseService;
import zw.co.zim.willplatform.utils.AppConstants;
import zw.co.zim.willplatform.utils.enums.RecordStatus;
import zw.co.zim.willplatform.utils.messages.request.TotalDemiseRequest;
import zw.co.zim.willplatform.utils.messages.response.basic.ApiResponse;
import zw.co.zim.willplatform.utils.messages.response.helper.HelperResponse;

import java.util.Objects;
import java.util.Optional;

@Service
public class TotalDemiseProcessorImpl implements TotalDemiseProcessor {
    private final TotalDemiseService totalDemiseService;
    private final ClientsService clientsService;
    private final ModelMapper modelMapper;
    private final TotalDemiseDtoMapper mapper;

    public TotalDemiseProcessorImpl(TotalDemiseService totalDemiseService, ClientsService clientsService, ModelMapper modelMapper, TotalDemiseDtoMapper mapper) {
        this.totalDemiseService = totalDemiseService;
        this.clientsService = clientsService;
        this.modelMapper = modelMapper;
        this.mapper = mapper;
    }


    @Override
    public ApiResponse<TotalDemiseDto> findAll(Integer pageNo, Integer pageSize) {
        Page<TotalDemise> demisePage = totalDemiseService.findAll(pageNo, pageSize);
        return HelperResponse.buildApiResponse(demisePage, mapper, true, 200, true, AppConstants.LIST_MESSAGE, null);
    }

    @Override
    public ApiResponse<TotalDemiseDto> findById(Long id) {
        Optional<TotalDemise> optional = totalDemiseService.findById(id);

        return optional.map(bequest -> HelperResponse.buildApiResponse(null, null, false, 200, true, AppConstants.FOUND_MESSAGE, mapper.apply(bequest)))
            .orElseThrow(() -> new RecordNotFoundException("Failed to find total demise record with Id of " + id));

    }

    @Override
    public ApiResponse<TotalDemiseDto> save(TotalDemiseRequest totalDemiseRequest) {
        Optional<Client> optionalClient = clientsService.findById(totalDemiseRequest.getClientId());
        if (optionalClient.isEmpty()) {
            throw new RecordNotFoundException("Failed to find client with Id " + totalDemiseRequest.getClientId());
        }

        TotalDemiseDto recordDto = TotalDemiseDto.builder()
            .userId(optionalClient.get())
            .beneficiaryDetails(totalDemiseRequest.getBeneficiaryDetails())
            .recordStatus(RecordStatus.ACTIVE)
            .build();

        TotalDemise demise = modelMapper.map(recordDto, TotalDemise.class);
        demise = totalDemiseService.save(demise);
        return HelperResponse.buildApiResponse(null, null, false, 201, true, AppConstants.SUCCESS_MESSAGE, mapper.apply(demise));
    }

    @Override
    public ApiResponse<TotalDemiseDto> update(Long id, TotalDemiseDto totalDemiseDto) {
        Optional<TotalDemise> bequest = totalDemiseService.findById(id);

        if (bequest.isEmpty() || !Objects.equals(bequest.get().getId(), id)) {
            throw new RecordNotFoundException("Failed to find total demise record with Id " + id);
        }
        TotalDemise updatedRecord = totalDemiseService.update(id, modelMapper.map(totalDemiseDto, TotalDemise.class));
        TotalDemiseDto mappedDto = mapper.apply(updatedRecord);
        return HelperResponse.buildApiResponse(null, null, false, 200, true, AppConstants.SUCCESS_MESSAGE, mappedDto);
    }

    @Override
    public ApiResponse<TotalDemiseDto> deleteById(Long id) {
        Optional<TotalDemise> demise = totalDemiseService.findById(id);

        if (demise.isEmpty() || !demise.get().getId().equals(id)) {
            throw new RecordNotFoundException("Failed to find total demise record with Id " + id);
        }
        totalDemiseService.deleteById(id);
        return HelperResponse.buildApiResponse(null, null, false, 200, true, AppConstants.SUCCESS_MESSAGE, null);
    }

    @Override
    public ApiResponse<TotalDemiseDto> findAllByUserId(Long clientId, Integer pageNo, Integer pageSize) {
        Optional<Client> client = clientsService.findById(clientId);

        if (client.isEmpty() || !client.get().getId().equals(clientId)) {
            throw new RecordNotFoundException("Failed to find client record with Id " + clientId);
        }
        Page<TotalDemise> demisePage = totalDemiseService.findAllByUserId(client.get(), pageNo, pageSize);
        return HelperResponse.buildApiResponse(demisePage, mapper, true, 200, true, AppConstants.LIST_MESSAGE, null);
    }
}
