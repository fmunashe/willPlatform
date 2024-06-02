package zw.co.zim.willplatform.processor.impl;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import zw.co.zim.willplatform.dto.UsersGuardianDto;
import zw.co.zim.willplatform.dto.mapper.UsersGuardianDtoMapper;
import zw.co.zim.willplatform.exceptions.BusinessException;
import zw.co.zim.willplatform.exceptions.RecordNotFoundException;
import zw.co.zim.willplatform.model.Address;
import zw.co.zim.willplatform.model.Client;
import zw.co.zim.willplatform.model.UsersGuardian;
import zw.co.zim.willplatform.processor.UsersGuardianProcessor;
import zw.co.zim.willplatform.service.ClientsService;
import zw.co.zim.willplatform.service.UsersGuardianService;
import zw.co.zim.willplatform.utils.AppConstants;
import zw.co.zim.willplatform.utils.enums.RecordStatus;
import zw.co.zim.willplatform.utils.messages.request.GuardianRequest;
import zw.co.zim.willplatform.utils.messages.response.basic.ApiResponse;
import zw.co.zim.willplatform.utils.messages.response.helper.HelperResponse;

import java.time.LocalDate;
import java.util.Objects;
import java.util.Optional;

@Service
public class UsersGuardianProcessorImpl implements UsersGuardianProcessor {
    private final UsersGuardianService guardianService;
    private final ClientsService clientsService;
    private final ModelMapper modelMapper;
    private final UsersGuardianDtoMapper mapper;

    public UsersGuardianProcessorImpl(UsersGuardianService guardianService, ClientsService clientsService, ModelMapper modelMapper, UsersGuardianDtoMapper mapper) {
        this.guardianService = guardianService;
        this.clientsService = clientsService;
        this.modelMapper = modelMapper;
        this.mapper = mapper;
    }

    @Override
    public ApiResponse<UsersGuardianDto> findAll(Integer pageNo, Integer pageSize) {
        Page<UsersGuardian> guardianPage = guardianService.findAll(pageNo, pageSize);
        return HelperResponse.buildApiResponse(guardianPage, mapper, true, 200, true, AppConstants.LIST_MESSAGE, null);
    }

    @Override
    public ApiResponse<UsersGuardianDto> findById(Long id) {
        Optional<UsersGuardian> optional = guardianService.findById(id);

        return optional.map(guardian -> HelperResponse.buildApiResponse(null, null, false, 200, true, AppConstants.FOUND_MESSAGE, mapper.apply(guardian)))
            .orElseThrow(() -> new RecordNotFoundException("Failed to find guardian record with Id of " + id));

    }

    @Override
    public ApiResponse<UsersGuardianDto> save(GuardianRequest guardianRequest) {
        Optional<Client> optionalClient = clientsService.findById(guardianRequest.getClientId());
        if (optionalClient.isEmpty()) {
            throw new RecordNotFoundException("Failed to find client with Id " + guardianRequest.getClientId());
        }

        UsersGuardianDto recordDto = getUsersGuardianDto(guardianRequest, optionalClient.get());
        if (recordDto.getDob().isAfter(LocalDate.now())) {
            throw new BusinessException("Date of birth cannot be in the future " + recordDto.getDob());
        }

        UsersGuardian guardian = modelMapper.map(recordDto, UsersGuardian.class);
        guardian = guardianService.save(guardian);
        return HelperResponse.buildApiResponse(null, null, false, 201, true, AppConstants.SUCCESS_MESSAGE, mapper.apply(guardian));

    }


    @Override
    public ApiResponse<UsersGuardianDto> update(Long id, UsersGuardianDto usersGuardianDto) {
        Optional<UsersGuardian> guardian = guardianService.findById(id);

        if (guardian.isEmpty() || !Objects.equals(guardian.get().getId(), id)) {
            throw new RecordNotFoundException("Failed to find a guardian record with Id " + id);
        }
        if (usersGuardianDto.getDob().isAfter(LocalDate.now())) {
            throw new BusinessException("Date of birth cannot be in the future " + usersGuardianDto.getDob());
        }

        UsersGuardian updatedRecord = guardianService.update(id, modelMapper.map(usersGuardianDto, UsersGuardian.class));
        UsersGuardianDto mappedDto = mapper.apply(updatedRecord);
        return HelperResponse.buildApiResponse(null, null, false, 200, true, AppConstants.SUCCESS_MESSAGE, mappedDto);

    }

    @Override
    public ApiResponse<UsersGuardianDto> deleteById(Long id) {
        Optional<UsersGuardian> guardian = guardianService.findById(id);

        if (guardian.isEmpty() || !guardian.get().getId().equals(id)) {
            throw new RecordNotFoundException("Failed to find a guardian record with Id " + id);
        }
        guardianService.deleteById(id);
        return HelperResponse.buildApiResponse(null, null, false, 200, true, AppConstants.SUCCESS_MESSAGE, null);

    }

    @Override
    public ApiResponse<UsersGuardianDto> findAllByUserId(Long userId, Integer pageNo, Integer pageSize) {
        Optional<Client> client = clientsService.findById(userId);

        if (client.isEmpty() || !client.get().getId().equals(userId)) {
            throw new RecordNotFoundException("Failed to find client record with Id " + userId);
        }
        Page<UsersGuardian> guardianPage = guardianService.findAllByUserId(client.get(), pageNo, pageSize);
        return HelperResponse.buildApiResponse(guardianPage, mapper, true, 200, true, AppConstants.LIST_MESSAGE, null);

    }

    private static Address getAddress(GuardianRequest guardianRequest) {
        return Address.builder()
            .street(guardianRequest.getStreet())
            .city(guardianRequest.getCity())
            .suburb(guardianRequest.getSuburb())
            .province(guardianRequest.getProvince())
            .country(guardianRequest.getCountry())
            .build();
    }

    private static UsersGuardianDto getUsersGuardianDto(GuardianRequest guardianRequest, Client client) {
        return UsersGuardianDto.builder()
            .userId(client)
            .title(guardianRequest.getTitle())
            .name(guardianRequest.getName())
            .middleName(guardianRequest.getMiddleName())
            .surname(guardianRequest.getSurname())
            .email(guardianRequest.getEmail())
            .contactNumber(guardianRequest.getContactNumber())
            .IdNumber(guardianRequest.getIdNumber())
            .passportNumber(guardianRequest.getPassportNumber())
            .gender(guardianRequest.getGender())
            .relationship(guardianRequest.getRelationship())
            .dob(guardianRequest.getDob())
            .address(getAddress(guardianRequest))
            .recordStatus(RecordStatus.ACTIVE)
            .build();
    }
}
