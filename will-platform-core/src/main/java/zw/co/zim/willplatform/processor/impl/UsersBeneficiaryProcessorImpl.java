package zw.co.zim.willplatform.processor.impl;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import zw.co.zim.willplatform.dto.UsersBeneficiaryDto;
import zw.co.zim.willplatform.dto.mapper.UsersBeneficiaryDtoMapper;
import zw.co.zim.willplatform.exceptions.RecordNotFoundException;
import zw.co.zim.willplatform.model.Address;
import zw.co.zim.willplatform.model.Client;
import zw.co.zim.willplatform.model.UsersBeneficiary;
import zw.co.zim.willplatform.processor.UsersBeneficiaryProcessor;
import zw.co.zim.willplatform.service.ClientsService;
import zw.co.zim.willplatform.service.UsersBeneficiaryService;
import zw.co.zim.willplatform.utils.AppConstants;
import zw.co.zim.willplatform.utils.enums.RecordStatus;
import zw.co.zim.willplatform.utils.messages.request.UsersBeneficiaryRequest;
import zw.co.zim.willplatform.utils.messages.response.basic.ApiResponse;
import zw.co.zim.willplatform.utils.messages.response.helper.HelperResponse;
import zw.co.zim.willplatform.utils.rules.UsersBeneficiaryChecker;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UsersBeneficiaryProcessorImpl implements UsersBeneficiaryProcessor {
    private final UsersBeneficiaryService beneficiaryService;
    private final ClientsService clientsService;
    private final ModelMapper modelMapper;
    private final UsersBeneficiaryDtoMapper mapper;
    private final UsersBeneficiaryChecker beneficiaryChecker;

    public UsersBeneficiaryProcessorImpl(UsersBeneficiaryService beneficiaryService, ClientsService clientsService, ModelMapper modelMapper, UsersBeneficiaryDtoMapper mapper, UsersBeneficiaryChecker beneficiaryChecker) {
        this.beneficiaryService = beneficiaryService;
        this.clientsService = clientsService;
        this.modelMapper = modelMapper;
        this.mapper = mapper;
        this.beneficiaryChecker = beneficiaryChecker;
    }

    @Override
    public ApiResponse<UsersBeneficiaryDto> findAll(Integer pageNo, Integer pageSize) {

        Page<UsersBeneficiary> beneficiaries = beneficiaryService.findAll(pageNo, pageSize);
        return HelperResponse.buildApiResponse(beneficiaries, mapper, true, 200, true, AppConstants.LIST_MESSAGE, null);

    }

    @Override
    public ApiResponse<UsersBeneficiaryDto> findById(Long id) {

        Optional<UsersBeneficiary> optional = beneficiaryService.findById(id);

        return optional.map(beneficiary -> HelperResponse.buildApiResponse(null, null, false, 200, true, AppConstants.FOUND_MESSAGE, mapper.apply(beneficiary)))
            .orElseThrow(() -> new RecordNotFoundException("Failed to find beneficiary record with Id of " + id));
    }

    @Override
    public ApiResponse<UsersBeneficiaryDto> findBeneficiaryByClientIdAndEmail(Long clientId, String email) {
        Optional<Client> optionalClient = clientsService.findById(clientId);
        if (optionalClient.isEmpty()) {
            throw new RecordNotFoundException("Failed to find client with Id " + clientId);
        }
        Optional<UsersBeneficiary> optional = beneficiaryService.findFirstByClientIdAndEmail(optionalClient.get(), email);

        return optional.map(beneficiary -> HelperResponse.buildApiResponse(null, null, false, 200, true, AppConstants.FOUND_MESSAGE, mapper.apply(beneficiary)))
            .orElseThrow(() -> new RecordNotFoundException("Failed to find beneficiary record with email of " + email));

    }

    @Override
    public ApiResponse<UsersBeneficiaryDto> findBeneficiaryByClientIdAndIdNumber(Long clientId, String nationalId) {
        Optional<Client> optionalClient = clientsService.findById(clientId);
        if (optionalClient.isEmpty()) {
            throw new RecordNotFoundException("Failed to find client with Id " + clientId);
        }

        Optional<UsersBeneficiary> optional = beneficiaryService.findFirstByClientIdAndIDNumber(optionalClient.get(), nationalId);

        return optional.map(beneficiary -> HelperResponse.buildApiResponse(null, null, false, 200, true, AppConstants.FOUND_MESSAGE, mapper.apply(beneficiary)))
            .orElseThrow(() -> new RecordNotFoundException("Failed to find beneficiary record with national id number of " + nationalId));

    }

    @Override
    public ApiResponse<UsersBeneficiaryDto> save(UsersBeneficiaryRequest usersBeneficiaryRequest) {
        Optional<Client> optionalClient = clientsService.findById(usersBeneficiaryRequest.getClientId());
        if (optionalClient.isEmpty()) {
            throw new RecordNotFoundException("Failed to find client with Id " + usersBeneficiaryRequest.getClientId());
        }

        UsersBeneficiaryDto recordDto = buildBeneficiary(optionalClient.get(), usersBeneficiaryRequest);

        if (recordDto.getPercentage() < 0 || recordDto.getPercentage() > 100) {
            throw new RecordNotFoundException("Percentage value provided is invalid " + recordDto.getPercentage());
        }

        Optional<UsersBeneficiary> optionalBeneficiaryEmail = beneficiaryService.findFirstByClientIdAndEmail(optionalClient.get(), recordDto.getEmail());
        if (optionalBeneficiaryEmail.isPresent()) {
            throw new RecordNotFoundException("Beneficiary with email address already exists " + recordDto.getEmail());
        }

        Optional<UsersBeneficiary> optionalBeneficiaryIdNumber = beneficiaryService.findFirstByClientIdAndEmail(optionalClient.get(), recordDto.getIdNumber());
        if (optionalBeneficiaryIdNumber.isPresent()) {
            throw new RecordNotFoundException("Beneficiary with national id number already exists " + recordDto.getEmail());
        }

        Page<UsersBeneficiary> existingBeneficiaries = beneficiaryService.findAllByUserId(optionalClient.get(), Integer.parseInt(AppConstants.DEFAULT_PAGE_NUMBER), Integer.parseInt(AppConstants.DEFAULT_PAGE_SIZE));

        boolean checkPercentage = beneficiaryChecker.test(existingBeneficiaries.getContent(), recordDto.getPercentage());

        if (!checkPercentage) {
            throw new RecordNotFoundException("Total beneficiary percentage share cannot exceed 100% ");
        }

        UsersBeneficiary beneficiary = modelMapper.map(recordDto, UsersBeneficiary.class);
        beneficiary = beneficiaryService.save(beneficiary);
        return HelperResponse.buildApiResponse(null, null, false, 201, true, AppConstants.SUCCESS_MESSAGE, mapper.apply(beneficiary));

    }


    @Override
    public ApiResponse<UsersBeneficiaryDto> update(Long id, UsersBeneficiaryDto usersBeneficiaryDto) {
        Optional<UsersBeneficiary> beneficiary = beneficiaryService.findById(id);

        if (beneficiary.isEmpty() || !Objects.equals(beneficiary.get().getId(), id)) {
            throw new RecordNotFoundException("Failed to find beneficiary record with Id " + id);
        }

        if (usersBeneficiaryDto.getPercentage() < 0 || usersBeneficiaryDto.getPercentage() > 100) {
            throw new RecordNotFoundException("Percentage value provided is invalid " + usersBeneficiaryDto.getPercentage());
        }

        Optional<Client> optionalClient = clientsService.findById(usersBeneficiaryDto.getUserId().getId());
        if (optionalClient.isEmpty()) {
            throw new RecordNotFoundException("Failed to find client with Id " + usersBeneficiaryDto.getUserId().getId());
        }

        Page<UsersBeneficiary> existingBeneficiaries = beneficiaryService.findAllByUserId(optionalClient.get(), Integer.parseInt(AppConstants.DEFAULT_PAGE_NUMBER), Integer.parseInt(AppConstants.DEFAULT_PAGE_SIZE));

        List<UsersBeneficiary> beneficiaryList = existingBeneficiaries.getContent().stream().filter(benefit -> !benefit.getEmail().equalsIgnoreCase(usersBeneficiaryDto.getEmail())).collect(Collectors.toList());

        boolean checkPercentage = beneficiaryChecker.test(beneficiaryList, usersBeneficiaryDto.getPercentage());

        if (!checkPercentage) {
            throw new RecordNotFoundException("Total beneficiary percentage share cannot exceed 100% ");
        }

        UsersBeneficiary updatedRecord = beneficiaryService.update(id, modelMapper.map(usersBeneficiaryDto, UsersBeneficiary.class));

        UsersBeneficiaryDto mappedDto = mapper.apply(updatedRecord);

        return HelperResponse.buildApiResponse(null, null, false, 200, true, AppConstants.SUCCESS_MESSAGE, mappedDto);
    }

    @Override
    public ApiResponse<UsersBeneficiaryDto> deleteById(Long id) {
        Optional<UsersBeneficiary> beneficiary = beneficiaryService.findById(id);

        if (beneficiary.isEmpty() || !beneficiary.get().getId().equals(id)) {
            throw new RecordNotFoundException("Failed to find beneficiary record with Id " + id);
        }
        beneficiaryService.deleteById(id);
        return HelperResponse.buildApiResponse(null, null, false, 200, true, AppConstants.SUCCESS_MESSAGE, null);

    }

    @Override
    public ApiResponse<UsersBeneficiaryDto> findAllByUserId(Long clientId, Integer pageNo, Integer pageSize) {
        Optional<Client> client = clientsService.findById(clientId);

        if (client.isEmpty() || !client.get().getId().equals(clientId)) {
            throw new RecordNotFoundException("Failed to find client record with Id " + clientId);
        }
        Page<UsersBeneficiary> beneficiaries = beneficiaryService.findAllByUserId(client.get(), pageNo, pageSize);
        return HelperResponse.buildApiResponse(beneficiaries, mapper, true, 200, true, AppConstants.LIST_MESSAGE, null);

    }

    private UsersBeneficiaryDto buildBeneficiary(Client client, UsersBeneficiaryRequest usersBeneficiaryRequest) {
        return UsersBeneficiaryDto.builder()
            .userId(client)
            .title(usersBeneficiaryRequest.getTitle())
            .name(usersBeneficiaryRequest.getName())
            .middleName(usersBeneficiaryRequest.getMiddleName())
            .lastName(usersBeneficiaryRequest.getLastName())
            .dob(usersBeneficiaryRequest.getDob())
            .idNumber(usersBeneficiaryRequest.getIdNumber())
            .passportNumber(usersBeneficiaryRequest.getPassportNumber())
            .contactNumber(usersBeneficiaryRequest.getContactNumber())
            .email(usersBeneficiaryRequest.getEmail())
            .trustName(usersBeneficiaryRequest.getTrustName())
            .trustNumber(usersBeneficiaryRequest.getTrustNumber())
            .gender(usersBeneficiaryRequest.getGender())
            .relationship(usersBeneficiaryRequest.getRelationship())
            .percentage(usersBeneficiaryRequest.getPercentage())
            .instructions(usersBeneficiaryRequest.getInstructions())
            .overEighteen(usersBeneficiaryRequest.getOverEighteen())
            .address(buildAddress(usersBeneficiaryRequest))
            .recordStatus(RecordStatus.ACTIVE)
            .build();
    }

    private Address buildAddress(UsersBeneficiaryRequest beneficiaryRequest) {
        return Address.builder()
            .street(beneficiaryRequest.getStreet())
            .city(beneficiaryRequest.getCity())
            .suburb(beneficiaryRequest.getSuburb())
            .province(beneficiaryRequest.getProvince())
            .country(beneficiaryRequest.getCountry())
            .build();
    }
}
