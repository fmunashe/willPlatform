package zw.co.zim.willplatform.processor.impl;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import zw.co.zim.willplatform.dto.SignupProgressJourneyDto;
import zw.co.zim.willplatform.dto.mapper.SignupProgressJourneyDtoMapper;
import zw.co.zim.willplatform.exceptions.RecordExistsException;
import zw.co.zim.willplatform.exceptions.RecordNotFoundException;
import zw.co.zim.willplatform.model.Client;
import zw.co.zim.willplatform.model.SignupProgressJourney;
import zw.co.zim.willplatform.processor.SignupProgressJourneyProcessor;
import zw.co.zim.willplatform.service.ClientsService;
import zw.co.zim.willplatform.service.SignupProgressJourneyService;
import zw.co.zim.willplatform.utils.AppConstants;
import zw.co.zim.willplatform.utils.enums.RecordStatus;
import zw.co.zim.willplatform.utils.messages.response.basic.ApiResponse;
import zw.co.zim.willplatform.utils.messages.response.helper.HelperResponse;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SignupProgressJourneyProcessorImpl implements SignupProgressJourneyProcessor {
    private final ClientsService clientsService;
    private final ModelMapper modelMapper;
    private final SignupProgressJourneyService service;
    private final SignupProgressJourneyDtoMapper mapper;

    public SignupProgressJourneyProcessorImpl(ClientsService clientsService, ModelMapper modelMapper, SignupProgressJourneyService service, SignupProgressJourneyDtoMapper mapper) {
        this.clientsService = clientsService;
        this.modelMapper = modelMapper;
        this.service = service;
        this.mapper = mapper;
    }

    @Override
    public ApiResponse<SignupProgressJourneyDto> findAll(Integer pageNo, Integer pageSize) {
        Page<SignupProgressJourney> progressJourney = service.findAll(pageNo, pageSize);
        return HelperResponse.buildApiResponse(progressJourney, mapper, true, 200, true, AppConstants.LIST_MESSAGE, null);

    }

    @Override
    public ApiResponse<SignupProgressJourneyDto> findById(Long id) {
        Optional<SignupProgressJourney> optional = service.findById(id);

        return optional.map(progressJourney -> HelperResponse.buildApiResponse(null, null, false, 200, true, AppConstants.FOUND_MESSAGE, mapper.apply(progressJourney)))
            .orElseThrow(() -> new RecordNotFoundException("Failed to find signup progress journey record with Id of " + id));

    }

    @Override
    public ApiResponse<SignupProgressJourneyDto> save(SignupProgressJourneyDto signupProgressJourneyDto) {

        Optional<SignupProgressJourney> optionalJourney = service.findById(signupProgressJourneyDto.getUserId().getId());
        if (optionalJourney.isPresent()) {
            throw new RecordExistsException("Signup progress journey Record already exist for client " + signupProgressJourneyDto.getUserId().getFirstName());
        }
        signupProgressJourneyDto.setRecordStatus(RecordStatus.ACTIVE);
        SignupProgressJourney journey = modelMapper.map(signupProgressJourneyDto, SignupProgressJourney.class);
        journey = service.save(journey);
        return HelperResponse.buildApiResponse(null, null, false, 201, true, AppConstants.SUCCESS_MESSAGE, mapper.apply(journey));

    }

    @Override
    public ApiResponse<SignupProgressJourneyDto> update(Long id, SignupProgressJourneyDto signupProgressJourneyDto) {
        Optional<SignupProgressJourney> journey = service.findById(id);

        if (journey.isEmpty() || !Objects.equals(journey.get().getId(), id)) {
            throw new RecordNotFoundException("Failed to find signup progress journey record with Id " + id);
        }
        SignupProgressJourney updatedRecord = service.update(id, modelMapper.map(signupProgressJourneyDto, SignupProgressJourney.class));
        SignupProgressJourneyDto mappedDto = mapper.apply(updatedRecord);
        return HelperResponse.buildApiResponse(null, null, false, 200, true, AppConstants.SUCCESS_MESSAGE, mappedDto);

    }

    @Override
    public ApiResponse<SignupProgressJourneyDto> deleteById(Long id) {
        Optional<SignupProgressJourney> journey = service.findById(id);

        if (journey.isEmpty() || !journey.get().getId().equals(id)) {
            throw new RecordNotFoundException("Failed to find signup progress journey record with Id " + id);
        }
        service.deleteById(id);
        return HelperResponse.buildApiResponse(null, null, false, 200, true, AppConstants.SUCCESS_MESSAGE, null);

    }

    @Override
    public ApiResponse<SignupProgressJourneyDto> findFirstByClientId(Long clientId) {
        Optional<Client> optionalClient = clientsService.findById(clientId);
        if (optionalClient.isEmpty()) {
            throw new RecordNotFoundException("Failed to find client with Id " + clientId);
        }
        Optional<SignupProgressJourney> optional = service.findFirstByUserId(optionalClient.get());

        return optional.map(progressJourney -> HelperResponse.buildApiResponse(null, null, false, 200, true, AppConstants.FOUND_MESSAGE, mapper.apply(progressJourney)))
            .orElseThrow(() -> new RecordNotFoundException("Failed to find signup progress journey record for client " + optionalClient.get().getFirstName()));

    }

    @Override
    public ApiResponse<Map<String, Long>> signupProgressStatistics() {
        List<SignupProgressJourney> signupProgress = service.findAll();
        List<SignupProgressJourneyDto> signupProgressList = HelperResponse.mappedDtoList(signupProgress, mapper);
        Map<String, Long> response = signupProgressList.stream().collect(Collectors.collectingAndThen(Collectors.toList(), list -> Map.of(
            "completedQuestionnaire", list.stream().filter(SignupProgressJourneyDto::getCompletedQuestionnaire).count(),
            "completedPersonalSection", list.stream().filter(SignupProgressJourneyDto::getCompletedPersonalSection).count(),
            "completedSpouseSection", list.stream().filter(SignupProgressJourneyDto::getCompletedSpouseSection).count(),
            "completedAssetsSection", list.stream().filter(SignupProgressJourneyDto::getCompletedAssetsSection).count(),
            "completedLiabilitiesSection", list.stream().filter(SignupProgressJourneyDto::getCompletedLiabilitiesSection).count(),
            "completedWillSection", list.stream().filter(SignupProgressJourneyDto::getCompletedWillSection).count(),
            "willComplete", list.stream().filter(SignupProgressJourneyDto::getWillComplete).count(),
            "finalisedWill", list.stream().filter(SignupProgressJourneyDto::getFinalisedWill).count(),
            "subscribed", list.stream().filter(SignupProgressJourneyDto::getSubscribed).count()
        )));

        return HelperResponse.buildApiResponse(null, null, false, 200, true, AppConstants.SUCCESS_MESSAGE, response);
    }
}
