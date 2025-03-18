package zw.co.zim.willplatform.processor.impl;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import zw.co.zim.willplatform.dto.QuestionnaireDto;
import zw.co.zim.willplatform.dto.mapper.QuestionnaireDtoMapper;
import zw.co.zim.willplatform.exceptions.BusinessException;
import zw.co.zim.willplatform.exceptions.RecordExistsException;
import zw.co.zim.willplatform.exceptions.RecordNotFoundException;
import zw.co.zim.willplatform.model.Client;
import zw.co.zim.willplatform.model.Questionnaire;
import zw.co.zim.willplatform.processor.QuestionnaireProcessor;
import zw.co.zim.willplatform.service.ClientsService;
import zw.co.zim.willplatform.service.QuestionnaireService;
import zw.co.zim.willplatform.utils.AppConstants;
import zw.co.zim.willplatform.utils.enums.RecordStatus;
import zw.co.zim.willplatform.utils.messages.request.QuestionnaireRequest;
import zw.co.zim.willplatform.utils.messages.response.basic.ApiResponse;
import zw.co.zim.willplatform.utils.messages.response.helper.HelperResponse;

import java.util.Objects;
import java.util.Optional;

@Service
public class QuestionnaireProcessorImpl implements QuestionnaireProcessor {

    private final QuestionnaireService questionnaireService;
    private final ClientsService clientsService;
    private final ModelMapper modelMapper;
    private final QuestionnaireDtoMapper mapper;

    public QuestionnaireProcessorImpl(QuestionnaireService questionnaireService, ClientsService clientsService, ModelMapper modelMapper, QuestionnaireDtoMapper mapper) {
        this.questionnaireService = questionnaireService;
        this.clientsService = clientsService;
        this.modelMapper = modelMapper;
        this.mapper = mapper;
    }

    @Override
    public ApiResponse<QuestionnaireDto> findAll(Integer pageNo, Integer pageSize) {
        Page<Questionnaire> questionnaires = questionnaireService.findAll(pageNo, pageSize);
        return HelperResponse.buildApiResponse(questionnaires, mapper, true, 200, true, AppConstants.LIST_MESSAGE, null);
    }

    @Override
    public ApiResponse<QuestionnaireDto> findById(Long id) {
        Optional<Questionnaire> optional = questionnaireService.findById(id);

        return optional.map(questionnaire -> HelperResponse.buildApiResponse(null, null, false, 200, true, AppConstants.FOUND_MESSAGE, mapper.apply(questionnaire)))
                .orElseThrow(() -> new RecordNotFoundException("Failed to find a questionnaire record with id of " + id));

    }

    @Override
    public ApiResponse<QuestionnaireDto> save(QuestionnaireRequest questionnaireRequest) {
        Optional<Client> optionalClient = clientsService.findById(questionnaireRequest.getClientId());
        if (optionalClient.isEmpty()) {
            throw new RecordNotFoundException("Failed to find client with Id " + questionnaireRequest.getClientId());
        }

        Optional<Questionnaire> optional = questionnaireService.findFirstByUserId(optionalClient.get());
        if (optional.isPresent()) {
            throw new RecordExistsException("There is already a questionnaire record for client with Id " + questionnaireRequest.getClientId());
        }

        if (questionnaireRequest.getHaveFireArm() && questionnaireRequest.getNumberOfFireArms() <= 0) {
            throw new BusinessException("Number of fire arms cannot be zero if you indicate that you possess a firearm");
        }
        if (questionnaireRequest.getNumberOfFireArms() > 0 && !questionnaireRequest.getHaveFireArm()) {
            throw new BusinessException("Number of fire arms cannot be greater than zero if you do not possess a firearm");
        }
        if (questionnaireRequest.getNumberOfFireArms() < 0) {
            throw new BusinessException("Number of fire arms cannot be less than zero");
        }

        QuestionnaireDto recordDto = buildQuestionnaireDto(questionnaireRequest, optionalClient.get());

        Questionnaire questionnaire = modelMapper.map(recordDto, Questionnaire.class);
        questionnaire = questionnaireService.save(questionnaire);
        return HelperResponse.buildApiResponse(null, null, false, 201, true, AppConstants.SUCCESS_MESSAGE, mapper.apply(questionnaire));

    }


    @Override
    public ApiResponse<QuestionnaireDto> update(Long id, QuestionnaireDto questionnaireDto) {
        Optional<Questionnaire> questionnaire = questionnaireService.findById(id);

        if (questionnaire.isEmpty() || !Objects.equals(questionnaire.get().getId(), id)) {
            throw new RecordNotFoundException("Failed to find a questionnaire record with Id " + id);
        }
        if (questionnaireDto.getHaveFireArm() && questionnaireDto.getNumberOfFireArms() <= 0) {
            throw new BusinessException("Number of fire arms cannot be zero if you indicate that you possess a firearm");
        }
        if (questionnaireDto.getNumberOfFireArms() > 0 && !questionnaireDto.getHaveFireArm()) {
            throw new BusinessException("Number of fire arms cannot be greater than zero if you do not possess a firearm");
        }
        if (questionnaireDto.getNumberOfFireArms() < 0) {
            throw new BusinessException("Number of fire arms cannot be less than zero");
        }
        Questionnaire updatedRecord = questionnaireService.update(id, modelMapper.map(questionnaireDto, Questionnaire.class));
        QuestionnaireDto mappedDto = mapper.apply(updatedRecord);
        return HelperResponse.buildApiResponse(null, null, false, 200, true, AppConstants.SUCCESS_MESSAGE, mappedDto);

    }

    @Override
    public ApiResponse<QuestionnaireDto> deleteById(Long id) {
        Optional<Questionnaire> questionnaire = questionnaireService.findById(id);

        if (questionnaire.isEmpty() || !questionnaire.get().getId().equals(id)) {
            throw new RecordNotFoundException("Failed to find questionnaire record with Id " + id);
        }
        questionnaireService.deleteById(id);
        return HelperResponse.buildApiResponse(null, null, false, 200, true, AppConstants.SUCCESS_MESSAGE, null);

    }

    @Override
    public ApiResponse<QuestionnaireDto> findFirstByUserId(Long clientId) {
        Optional<Client> client = clientsService.findById(clientId);

        if (client.isEmpty() || !client.get().getId().equals(clientId)) {
            throw new RecordNotFoundException("Failed to find client record with Id " + clientId);
        }

        Optional<Questionnaire> optional = questionnaireService.findFirstByUserId(client.get());

        return optional.map(questionnaire -> HelperResponse.buildApiResponse(null, null, false, 200, true, AppConstants.FOUND_MESSAGE, mapper.apply(questionnaire)))
                .orElseThrow(() -> new RecordNotFoundException("Failed to find a questionnaire record for client with id " + clientId));
    }

    private QuestionnaireDto buildQuestionnaireDto(QuestionnaireRequest questionnaireRequest, Client client) {
        return QuestionnaireDto.builder()
                .userId(client)
                .formerSpouse(questionnaireRequest.getFormerSpouse())
                .maritalStatus(questionnaireRequest.getMaritalStatus())
                .haveChildren(questionnaireRequest.getHaveChildren())
                .registeredTaxPayer(questionnaireRequest.getRegisteredTaxPayer())
                .businessOwner(questionnaireRequest.getBusinessOwner())
                .haveProperty(questionnaireRequest.getHaveProperty())
                .haveVehicle(questionnaireRequest.getHaveVehicle())
                .haveAssetsOffshore(questionnaireRequest.getHaveAssetsOffshore())
                .haveOverseaWill(questionnaireRequest.getHaveOverseaWill())
                .haveAssetsTrust(questionnaireRequest.getHaveAssetsTrust())
                .haveOtherAssets(questionnaireRequest.getHaveOtherAssets())
                .haveTimeShare(questionnaireRequest.getHaveTimeShare())
                .someOneOweMoney(questionnaireRequest.getSomeOneOweMoney())
                .haveBankAccount(questionnaireRequest.getHaveBankAccount())
                .haveCreditCard(questionnaireRequest.getHaveCreditCard())
                .haveInvestment(questionnaireRequest.getHaveInvestment())
                .havePolicy(questionnaireRequest.getHavePolicy())
                .haveOutstandingLoans(questionnaireRequest.getHaveOutstandingLoans())
                .haveOutstandingAccounts(questionnaireRequest.getHaveOutstandingAccounts())
                .userOweMoney(questionnaireRequest.getUserOweMoney())
                .subscribedWrittenPublication(questionnaireRequest.getSubscribedWrittenPublication())
                .haveFireArm(questionnaireRequest.getHaveFireArm())
                .numberOfFireArms(questionnaireRequest.getNumberOfFireArms())
                .haveMedicalAid(questionnaireRequest.getHaveMedicalAid())
                .medicalAidName(questionnaireRequest.getMedicalAidName())
                .haveDependenciesOverSeas(questionnaireRequest.getHaveDependenciesOverSeas())
                .occupation(questionnaireRequest.getOccupation())
                .educationalLevel(questionnaireRequest.getEducationalLevel())
                .monthlyIncome(questionnaireRequest.getMonthlyIncome())
                .monthlyHouseholdExpense(questionnaireRequest.getMonthlyHouseholdExpense())
                .smoker(questionnaireRequest.getSmoker())
                .recordStatus(RecordStatus.ACTIVE)
                .build();
    }
}
