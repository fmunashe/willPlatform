package zw.co.zim.willplatform.processor.impl;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import zw.co.zim.willplatform.dto.UsersSpouseDto;
import zw.co.zim.willplatform.dto.mapper.UsersSpouseDtoMapper;
import zw.co.zim.willplatform.exceptions.RecordNotFoundException;
import zw.co.zim.willplatform.model.Client;
import zw.co.zim.willplatform.model.UsersSpouse;
import zw.co.zim.willplatform.processor.UsersSpouseProcessor;
import zw.co.zim.willplatform.service.ClientsService;
import zw.co.zim.willplatform.service.UsersSpouseService;
import zw.co.zim.willplatform.utils.AppConstants;
import zw.co.zim.willplatform.utils.enums.RecordStatus;
import zw.co.zim.willplatform.utils.messages.request.SpouseRequest;
import zw.co.zim.willplatform.utils.messages.response.basic.ApiResponse;
import zw.co.zim.willplatform.utils.messages.response.helper.HelperResponse;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UsersSpouseProcessorImpl implements UsersSpouseProcessor {
    private final UsersSpouseService spouseService;
    private final ClientsService clientsService;
    private final ModelMapper modelMapper;
    private final UsersSpouseDtoMapper mapper;

    public UsersSpouseProcessorImpl(UsersSpouseService spouseService, ClientsService clientsService, ModelMapper modelMapper, UsersSpouseDtoMapper mapper) {
        this.spouseService = spouseService;
        this.clientsService = clientsService;
        this.modelMapper = modelMapper;
        this.mapper = mapper;
    }

    @Override
    public ApiResponse<UsersSpouseDto> findAll(Integer pageNo, Integer pageSize) {
        Page<UsersSpouse> spousePage = spouseService.findAll(pageNo, pageSize);
        return HelperResponse.buildApiResponse(spousePage, mapper, true, 200, true, AppConstants.LIST_MESSAGE, null);
    }

    @Override
    public ApiResponse<UsersSpouseDto> findById(Long id) {
        Optional<UsersSpouse> optional = spouseService.findById(id);

        return optional.map(spouse -> HelperResponse.buildApiResponse(null, null, false, 200, true, AppConstants.FOUND_MESSAGE, mapper.apply(spouse)))
            .orElseThrow(() -> new RecordNotFoundException("Failed to find spouse record with Id of " + id));

    }

    @Override
    public ApiResponse<UsersSpouseDto> save(SpouseRequest spouseRequest) {
        Optional<Client> optionalClient = clientsService.findById(spouseRequest.getClientId());
        if (optionalClient.isEmpty()) {
            throw new RecordNotFoundException("Failed to find client with Id " + spouseRequest.getClientId());
        }

        UsersSpouseDto recordDto = getUsersSpouseDto(spouseRequest, optionalClient.get());

        List<UsersSpouse> currentSpouseList = spouseService.findAllByUserId(optionalClient.get(), Integer.parseInt(AppConstants.DEFAULT_PAGE_NUMBER), Integer.parseInt(AppConstants.DEFAULT_PAGE_SIZE))
            .getContent()
            .stream()
            .filter(spouse -> spouse.getEmail().equals(spouseRequest.getEmail()))
            .filter(spouse -> spouse.getMobile().equals(spouseRequest.getMobile()))
            .toList();

        if (!currentSpouseList.isEmpty()) {
            throw new RecordNotFoundException("Spouse with the given email already exist " + spouseRequest.getEmail());
        }

        UsersSpouse spouse = modelMapper.map(recordDto, UsersSpouse.class);
        spouse = spouseService.save(spouse);
        return HelperResponse.buildApiResponse(null, null, false, 201, true, AppConstants.SUCCESS_MESSAGE, mapper.apply(spouse));

    }


    @Override
    public ApiResponse<UsersSpouseDto> update(Long id, UsersSpouseDto usersSpouseDto) {
        Optional<UsersSpouse> spouse = spouseService.findById(id);
        if (spouse.isEmpty() || !Objects.equals(spouse.get().getId(), id)) {
            throw new RecordNotFoundException("Failed to find a spouse record with Id " + id);
        }
        UsersSpouse updatedRecord = spouseService.update(id, modelMapper.map(usersSpouseDto, UsersSpouse.class));
        UsersSpouseDto mappedDto = mapper.apply(updatedRecord);
        return HelperResponse.buildApiResponse(null, null, false, 200, true, AppConstants.SUCCESS_MESSAGE, mappedDto);

    }

    @Override
    public ApiResponse<UsersSpouseDto> deleteById(Long id) {
        Optional<UsersSpouse> spouse = spouseService.findById(id);

        if (spouse.isEmpty() || !spouse.get().getId().equals(id)) {
            throw new RecordNotFoundException("Failed to find a spouse record with Id " + id);
        }
        spouseService.deleteById(id);
        return HelperResponse.buildApiResponse(null, null, false, 200, true, AppConstants.SUCCESS_MESSAGE, null);

    }

    @Override
    public ApiResponse<UsersSpouseDto> findAllByUserId(Long clientId, Integer pageNo, Integer pageSize) {
        Optional<Client> client = clientsService.findById(clientId);

        if (client.isEmpty() || !client.get().getId().equals(clientId)) {
            throw new RecordNotFoundException("Failed to find client record with Id " + clientId);
        }
        Page<UsersSpouse> spousePage = spouseService.findAllByUserId(client.get(), pageNo, pageSize);
        return HelperResponse.buildApiResponse(spousePage, mapper, true, 200, true, AppConstants.LIST_MESSAGE, null);

    }

    private UsersSpouseDto getUsersSpouseDto(SpouseRequest spouseRequest, Client client) {
        return UsersSpouseDto.builder()
            .userId(client)
            .name(spouseRequest.getName())
            .surname(spouseRequest.getSurname())
            .mobile(spouseRequest.getMobile())
            .email(spouseRequest.getEmail())
            .idNumber(spouseRequest.getIdNumber())
            .maritalStatus(spouseRequest.getMaritalStatus())
            .civillyMarriedStatus(spouseRequest.getCivillyMarriedStatus())
            .recordStatus(RecordStatus.ACTIVE)
            .build();
    }
}
