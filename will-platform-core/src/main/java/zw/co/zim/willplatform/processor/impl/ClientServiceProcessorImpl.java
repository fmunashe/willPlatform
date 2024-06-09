package zw.co.zim.willplatform.processor.impl;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import zw.co.zim.willplatform.dto.ClientDto;
import zw.co.zim.willplatform.dto.SignupProgressJourneyDto;
import zw.co.zim.willplatform.dto.mapper.ClientDtoMapper;
import zw.co.zim.willplatform.exceptions.RecordExistsException;
import zw.co.zim.willplatform.exceptions.RecordNotFoundException;
import zw.co.zim.willplatform.model.Address;
import zw.co.zim.willplatform.model.Client;
import zw.co.zim.willplatform.processor.ClientServiceProcessor;
import zw.co.zim.willplatform.processor.SignupProgressJourneyProcessor;
import zw.co.zim.willplatform.service.ClientsService;
import zw.co.zim.willplatform.utils.AppConstants;
import zw.co.zim.willplatform.utils.OTPGen;
import zw.co.zim.willplatform.utils.enums.RecordStatus;
import zw.co.zim.willplatform.utils.messages.request.ChangePasswordRequest;
import zw.co.zim.willplatform.utils.messages.request.ClientRequest;
import zw.co.zim.willplatform.utils.messages.request.LoginRequest;
import zw.co.zim.willplatform.utils.messages.response.basic.ApiResponse;
import zw.co.zim.willplatform.utils.messages.response.helper.HelperResponse;

import java.util.Objects;
import java.util.Optional;

@Service
public class ClientServiceProcessorImpl implements ClientServiceProcessor {

    private final ClientsService clientsService;
    private final ModelMapper modelMapper;
    private final ClientDtoMapper mapper;
    private final SignupProgressJourneyProcessor progressJourneyProcessor;

    public ClientServiceProcessorImpl(ClientsService clientsService, ModelMapper modelMapper, ClientDtoMapper mapper, SignupProgressJourneyProcessor progressJourneyProcessor) {
        this.clientsService = clientsService;
        this.modelMapper = modelMapper;
        this.mapper = mapper;
        this.progressJourneyProcessor = progressJourneyProcessor;
    }

    @Override
    public ApiResponse<ClientDto> findById(Long id) {
        Optional<Client> optional = clientsService.findById(id);

        return optional.map(client -> HelperResponse.buildApiResponse(null, null, false, 200, true, AppConstants.FOUND_MESSAGE, mapper.apply(client)))
            .orElseThrow(() -> new RecordNotFoundException("Failed to find client record with id of " + id));

    }

    @Override
    public ApiResponse<ClientDto> save(ClientRequest clientRequest) {
        Optional<Client> optional = clientsService.findFirstByEmailOrNationalIdNumber(clientRequest.getEmail(), clientRequest.getNationalIdNumber());
        if (optional.isPresent()) {
            throw new RecordExistsException("Profile with email " + clientRequest.getEmail() + " or national id " + clientRequest.getNationalIdNumber() + " already exist");
        }
//generate and send otp to client via selected otp delivery channel
        String otp = OTPGen.generateOTP();
        ClientDto recordDto = ClientDto.builder()
            .firstName(clientRequest.getFirstName())
            .middleName(clientRequest.getMiddleName())
            .lastName(clientRequest.getLastName())
            .firstLanguage(clientRequest.getFirstLanguage())
            .knownAs(clientRequest.getKnownAs())
            .dateOfBirth(clientRequest.getDateOfBirth())
            .email(clientRequest.getEmail())
            .mobileNumber(clientRequest.getMobileNumber())
            .nationalIdNumber(clientRequest.getNationalIdNumber())
            .sendOtpVia(clientRequest.getSendOtpVia())
            .OTP(otp)
            .password(clientRequest.getPassword())
            .acceptedTermsAndConditions(clientRequest.getAcceptedTermsAndConditions())
            .physicalAddress(buildAddress(clientRequest))
            .recordStatus(RecordStatus.ACTIVE)
            .build();

        Client client = modelMapper.map(recordDto, Client.class);
        client = clientsService.save(client);

        SignupProgressJourneyDto journeyDto = buildSignupProgressJourney(client);
        progressJourneyProcessor.save(journeyDto);

        return HelperResponse.buildApiResponse(null, null, false, 201, true, AppConstants.SUCCESS_MESSAGE, mapper.apply(client));

    }


    @Override
    public ApiResponse<ClientDto> update(Long id, ClientDto clientDto) {
        Optional<Client> client = clientsService.findById(id);

        if (client.isEmpty() || !Objects.equals(client.get().getId(), id)) {
            throw new RecordNotFoundException("Failed to find client record with Id " + id);
        }


        Client updatedRecord = clientsService.update(id, modelMapper.map(clientDto, Client.class));

        ClientDto mappedDto = mapper.apply(updatedRecord);

        return HelperResponse.buildApiResponse(null, null, false, 200, true, AppConstants.SUCCESS_MESSAGE, mappedDto);
    }

    @Override
    public ApiResponse<ClientDto> deleteById(Long id) {
        Optional<Client> client = clientsService.findById(id);

        if (client.isEmpty() || !client.get().getId().equals(id)) {
            throw new RecordNotFoundException("Failed to find client record with Id " + id);
        }
        clientsService.deleteById(id);
        return HelperResponse.buildApiResponse(null, null, false, 200, true, AppConstants.SUCCESS_MESSAGE, null);

    }

    @Override
    public ApiResponse<ClientDto> findAll(Integer pageNo, Integer pageSize) {
        Page<Client> clients = clientsService.findAll(pageNo, pageSize);
        return HelperResponse.buildApiResponse(clients, mapper, true, 200, true, AppConstants.LIST_MESSAGE, null);

    }

    @Override
    public ApiResponse<ClientDto> findFirstByEmailOrNationalIdNumber(String email, String nationalId) {
        Optional<Client> optional = clientsService.findFirstByEmailOrNationalIdNumber(email, nationalId);

        return optional.map(client -> HelperResponse.buildApiResponse(null, null, false, 200, true, AppConstants.FOUND_MESSAGE, mapper.apply(client)))
            .orElseThrow(() -> new RecordNotFoundException("Failed to find client record with email " + email + " or national id " + nationalId));

    }

    @Override
    public ApiResponse<ClientDto> findFirstByNationalIdNumber(String nationalId) {
        Optional<Client> optional = clientsService.findFirstByNationalIdNumber(nationalId);

        return optional.map(client -> HelperResponse.buildApiResponse(null, null, false, 200, true, AppConstants.FOUND_MESSAGE, mapper.apply(client)))
            .orElseThrow(() -> new RecordNotFoundException("Failed to find client record with national id " + nationalId));

    }

    @Override
    public ApiResponse<ClientDto> findFirstByPassportNumber(String passportNumber) {
        Optional<Client> optional = clientsService.findFirstByPassportNumber(passportNumber);

        return optional.map(client -> HelperResponse.buildApiResponse(null, null, false, 200, true, AppConstants.FOUND_MESSAGE, mapper.apply(client)))
            .orElseThrow(() -> new RecordNotFoundException("Failed to find client record with passport number " + passportNumber));

    }

    @Override
    public ApiResponse<ClientDto> changePassword(ChangePasswordRequest changePasswordRequest) {
        return null;
    }

    @Override
    public ApiResponse<ClientDto> login(LoginRequest loginRequest) {
        return null;
    }

    private Address buildAddress(ClientRequest clientRequest) {
        return Address.builder()
            .street(clientRequest.getStreet())
            .city(clientRequest.getCity())
            .suburb(clientRequest.getSuburb())
            .province(clientRequest.getProvince())
            .country(clientRequest.getCountry())
            .build();
    }

    private SignupProgressJourneyDto buildSignupProgressJourney(Client client) {
        return SignupProgressJourneyDto.builder()
            .userId(client)
            .completedQuestionnaire(false)
            .completedPersonalSection(false)
            .completedSpouseSection(false)
            .completedAssetsSection(false)
            .completedLiabilitiesSection(false)
            .completedWillSection(false)
            .willComplete(false)
            .completedBeneficiarySection(false)
            .completedChildrenSection(false)
            .completedGuardianSection(false)
            .finalisedWill(false)
            .subscribed(false)
            .recordStatus(RecordStatus.ACTIVE)
            .build();
    }
}
