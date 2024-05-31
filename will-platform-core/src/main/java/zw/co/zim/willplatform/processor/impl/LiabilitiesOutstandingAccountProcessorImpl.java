package zw.co.zim.willplatform.processor.impl;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import zw.co.zim.willplatform.dto.LiabilitiesOutstandingAccountDto;
import zw.co.zim.willplatform.dto.mapper.LiabilitiesOutstandingAccountDtoMapper;
import zw.co.zim.willplatform.exceptions.RecordNotFoundException;
import zw.co.zim.willplatform.model.Client;
import zw.co.zim.willplatform.model.LiabilitiesOutstandingAccount;
import zw.co.zim.willplatform.processor.LiabilitiesOutstandingAccountProcessor;
import zw.co.zim.willplatform.service.ClientsService;
import zw.co.zim.willplatform.service.LiabilitiesOutstandingAccountService;
import zw.co.zim.willplatform.utils.AppConstants;
import zw.co.zim.willplatform.utils.enums.RecordStatus;
import zw.co.zim.willplatform.utils.messages.request.OutstandingAccountRequest;
import zw.co.zim.willplatform.utils.messages.response.basic.ApiResponse;
import zw.co.zim.willplatform.utils.messages.response.helper.HelperResponse;

import java.util.Objects;
import java.util.Optional;

@Service
public class LiabilitiesOutstandingAccountProcessorImpl implements LiabilitiesOutstandingAccountProcessor {
    private final ClientsService clientsService;
    private final LiabilitiesOutstandingAccountService accountService;
    private final LiabilitiesOutstandingAccountDtoMapper mapper;
    private final ModelMapper modelMapper;

    public LiabilitiesOutstandingAccountProcessorImpl(ClientsService clientsService, LiabilitiesOutstandingAccountService accountService, LiabilitiesOutstandingAccountDtoMapper mapper, ModelMapper modelMapper) {
        this.clientsService = clientsService;
        this.accountService = accountService;
        this.mapper = mapper;
        this.modelMapper = modelMapper;
    }


    @Override
    public ApiResponse<LiabilitiesOutstandingAccountDto> findAll(Integer pageNo, Integer pageSize) {
        Page<LiabilitiesOutstandingAccount> outstandingAccounts = accountService.findAll(pageNo, pageSize);
        return HelperResponse.buildApiResponse(outstandingAccounts, mapper, true, 200, true, AppConstants.LIST_MESSAGE, null);
    }

    @Override
    public ApiResponse<LiabilitiesOutstandingAccountDto> findById(Long id) {
        Optional<LiabilitiesOutstandingAccount> optional = accountService.findById(id);

        return optional.map(account -> HelperResponse.buildApiResponse(null, null, false, 200, true, AppConstants.FOUND_MESSAGE, mapper.apply(account)))
            .orElseThrow(() -> new RecordNotFoundException("Failed to find outstanding account record with Id of " + id));
    }

    @Override
    public ApiResponse<LiabilitiesOutstandingAccountDto> save(OutstandingAccountRequest outstandingAccountRequest) {
        Optional<Client> optionalClient = clientsService.findById(outstandingAccountRequest.getClientId());
        if (optionalClient.isEmpty()) {
            throw new RecordNotFoundException("Failed to find client with Id " + outstandingAccountRequest.getClientId());
        }
        LiabilitiesOutstandingAccountDto recordDto = LiabilitiesOutstandingAccountDto.builder()
            .userId(optionalClient.get())
            .nameOfAccount(outstandingAccountRequest.getNameOfAccount())
            .accountValue(outstandingAccountRequest.getAccountValue())
            .recordStatus(RecordStatus.ACTIVE)
            .build();

        LiabilitiesOutstandingAccount account = modelMapper.map(recordDto, LiabilitiesOutstandingAccount.class);
        account = accountService.save(account);
        return HelperResponse.buildApiResponse(null, null, false, 201, true, AppConstants.SUCCESS_MESSAGE, mapper.apply(account));

    }

    @Override
    public ApiResponse<LiabilitiesOutstandingAccountDto> update(Long id, LiabilitiesOutstandingAccountDto liabilitiesOutstandingAccountDto) {
        Optional<LiabilitiesOutstandingAccount> account = accountService.findById(id);

        if (account.isEmpty() || !Objects.equals(account.get().getId(), id)) {
            throw new RecordNotFoundException("Failed to find outstanding account record with Id " + id);
        }
        LiabilitiesOutstandingAccount updatedRecord = accountService.update(id, modelMapper.map(liabilitiesOutstandingAccountDto, LiabilitiesOutstandingAccount.class));
        LiabilitiesOutstandingAccountDto mappedDto = mapper.apply(updatedRecord);
        return HelperResponse.buildApiResponse(null, null, false, 200, true, AppConstants.SUCCESS_MESSAGE, mappedDto);

    }

    @Override
    public ApiResponse<LiabilitiesOutstandingAccountDto> deleteById(Long id) {
        Optional<LiabilitiesOutstandingAccount> account = accountService.findById(id);

        if (account.isEmpty() || !account.get().getId().equals(id)) {
            throw new RecordNotFoundException("Failed to find outstanding account record with Id " + id);
        }
        accountService.deleteById(id);
        return HelperResponse.buildApiResponse(null, null, false, 200, true, AppConstants.SUCCESS_MESSAGE, null);
    }

    @Override
    public ApiResponse<LiabilitiesOutstandingAccountDto> findAllByUserId(Long clientId, Integer pageNo, Integer pageSize) {
        Optional<Client> client = clientsService.findById(clientId);

        if (client.isEmpty() || !client.get().getId().equals(clientId)) {
            throw new RecordNotFoundException("Failed to find client record with Id " + clientId);
        }
        Page<LiabilitiesOutstandingAccount> accountPage = accountService.findAllByUserId(client.get(), pageNo, pageSize);
        return HelperResponse.buildApiResponse(accountPage, mapper, true, 200, true, AppConstants.LIST_MESSAGE, null);
    }
}
