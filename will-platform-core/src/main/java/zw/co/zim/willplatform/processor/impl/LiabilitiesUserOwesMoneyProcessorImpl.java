package zw.co.zim.willplatform.processor.impl;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import zw.co.zim.willplatform.dto.LiabilitiesUserOwesMoneyDto;
import zw.co.zim.willplatform.dto.mapper.LiabilitiesUserOweMoneyDtoMapper;
import zw.co.zim.willplatform.exceptions.RecordNotFoundException;
import zw.co.zim.willplatform.model.Client;
import zw.co.zim.willplatform.model.LiabilitiesUserOwesMoney;
import zw.co.zim.willplatform.processor.LiabilitiesUserOwesMoneyProcessor;
import zw.co.zim.willplatform.service.ClientsService;
import zw.co.zim.willplatform.service.LiabilitiesUserOweMoneyService;
import zw.co.zim.willplatform.utils.AppConstants;
import zw.co.zim.willplatform.utils.enums.RecordStatus;
import zw.co.zim.willplatform.utils.messages.request.UserOwesMoneyRequest;
import zw.co.zim.willplatform.utils.messages.response.basic.ApiResponse;
import zw.co.zim.willplatform.utils.messages.response.helper.HelperResponse;

import java.util.Objects;
import java.util.Optional;

@Service
public class LiabilitiesUserOwesMoneyProcessorImpl implements LiabilitiesUserOwesMoneyProcessor {
    private final LiabilitiesUserOweMoneyService userOweMoneyService;
    private final ClientsService clientsService;
    private final ModelMapper modelMapper;
    private final LiabilitiesUserOweMoneyDtoMapper mapper;

    public LiabilitiesUserOwesMoneyProcessorImpl(LiabilitiesUserOweMoneyService userOweMoneyService, ClientsService clientsService, ModelMapper modelMapper, LiabilitiesUserOweMoneyDtoMapper mapper) {
        this.userOweMoneyService = userOweMoneyService;
        this.clientsService = clientsService;
        this.modelMapper = modelMapper;
        this.mapper = mapper;
    }

    @Override
    public ApiResponse<LiabilitiesUserOwesMoneyDto> findAll(Integer pageNo, Integer pageSize) {
        Page<LiabilitiesUserOwesMoney> moneyPage = userOweMoneyService.findAll(pageNo, pageSize);
        return HelperResponse.buildApiResponse(moneyPage, mapper, true, 200, true, AppConstants.LIST_MESSAGE, null);
    }

    @Override
    public ApiResponse<LiabilitiesUserOwesMoneyDto> findById(Long id) {
        Optional<LiabilitiesUserOwesMoney> optional = userOweMoneyService.findById(id);

        return optional.map(loan -> HelperResponse.buildApiResponse(null, null, false, 200, true, AppConstants.FOUND_MESSAGE, mapper.apply(loan)))
            .orElseThrow(() -> new RecordNotFoundException("Failed to find a user who owes money record with Id of " + id));

    }

    @Override
    public ApiResponse<LiabilitiesUserOwesMoneyDto> save(UserOwesMoneyRequest userOwesMoneyRequest) {
        Optional<Client> optionalClient = clientsService.findById(userOwesMoneyRequest.getClientId());
        if (optionalClient.isEmpty()) {
            throw new RecordNotFoundException("Failed to find client with Id " + userOwesMoneyRequest.getClientId());
        }
        LiabilitiesUserOwesMoneyDto recordDto = LiabilitiesUserOwesMoneyDto.builder()
            .userId(optionalClient.get())
            .personContactDetails(userOwesMoneyRequest.getPersonContactDetails())
            .personDetails(userOwesMoneyRequest.getPersonDetails())
            .amountOwed(userOwesMoneyRequest.getAmountOwed())
            .recordStatus(RecordStatus.ACTIVE)
            .build();

        LiabilitiesUserOwesMoney moneyOwed = modelMapper.map(recordDto, LiabilitiesUserOwesMoney.class);
        moneyOwed = userOweMoneyService.save(moneyOwed);
        return HelperResponse.buildApiResponse(null, null, false, 201, true, AppConstants.SUCCESS_MESSAGE, mapper.apply(moneyOwed));

    }

    @Override
    public ApiResponse<LiabilitiesUserOwesMoneyDto> update(Long id, LiabilitiesUserOwesMoneyDto liabilitiesUserOwesMoneyDto) {

        Optional<LiabilitiesUserOwesMoney> owesMoney = userOweMoneyService.findById(id);

        if (owesMoney.isEmpty() || !Objects.equals(owesMoney.get().getId(), id)) {
            throw new RecordNotFoundException("Failed to find user owes money record with Id " + id);
        }
        LiabilitiesUserOwesMoney updatedRecord = userOweMoneyService.update(id, modelMapper.map(liabilitiesUserOwesMoneyDto, LiabilitiesUserOwesMoney.class));
        LiabilitiesUserOwesMoneyDto mappedDto = mapper.apply(updatedRecord);
        return HelperResponse.buildApiResponse(null, null, false, 200, true, AppConstants.SUCCESS_MESSAGE, mappedDto);
    }

    @Override
    public ApiResponse<LiabilitiesUserOwesMoneyDto> deleteById(Long id) {
        Optional<LiabilitiesUserOwesMoney> owesMoney = userOweMoneyService.findById(id);

        if (owesMoney.isEmpty() || !owesMoney.get().getId().equals(id)) {
            throw new RecordNotFoundException("Failed to find user owes money record with Id " + id);
        }
        userOweMoneyService.deleteById(id);
        return HelperResponse.buildApiResponse(null, null, false, 200, true, AppConstants.SUCCESS_MESSAGE, null);

    }

    @Override
    public ApiResponse<LiabilitiesUserOwesMoneyDto> findAllByUserId(Long clientId, Integer pageNo, Integer pageSize) {
        Optional<Client> client = clientsService.findById(clientId);

        if (client.isEmpty() || !client.get().getId().equals(clientId)) {
            throw new RecordNotFoundException("Failed to find client record with Id " + clientId);
        }
        Page<LiabilitiesUserOwesMoney> loanPage = userOweMoneyService.findAllByUserId(client.get(), pageNo, pageSize);
        return HelperResponse.buildApiResponse(loanPage, mapper, true, 200, true, AppConstants.LIST_MESSAGE, null);
    }
}
