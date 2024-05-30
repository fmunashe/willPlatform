package zw.co.zim.willplatform.processor.impl;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import zw.co.zim.willplatform.dto.BankAssetRecordDto;
import zw.co.zim.willplatform.dto.mapper.BankAssetDtoMapper;
import zw.co.zim.willplatform.exceptions.RecordExistsException;
import zw.co.zim.willplatform.exceptions.RecordNotFoundException;
import zw.co.zim.willplatform.model.BankAsset;
import zw.co.zim.willplatform.model.Client;
import zw.co.zim.willplatform.model.Currency;
import zw.co.zim.willplatform.processor.BankAssetProcessor;
import zw.co.zim.willplatform.service.BankAssetService;
import zw.co.zim.willplatform.service.ClientsService;
import zw.co.zim.willplatform.service.CurrencyService;
import zw.co.zim.willplatform.utils.AppConstants;
import zw.co.zim.willplatform.utils.enums.RecordStatus;
import zw.co.zim.willplatform.utils.messages.request.BankAssetRequest;
import zw.co.zim.willplatform.utils.messages.response.basic.ApiResponse;
import zw.co.zim.willplatform.utils.messages.response.helper.HelperResponse;

import java.util.Objects;
import java.util.Optional;

@Service
public class BankAssetProcessorImpl implements BankAssetProcessor {
    private final BankAssetService bankAssetService;
    private final BankAssetDtoMapper mapper;
    private final ClientsService clientsService;
    private final CurrencyService currencyService;

    public BankAssetProcessorImpl(BankAssetService bankAssetService, BankAssetDtoMapper mapper, ClientsService clientsService, CurrencyService currencyService) {
        this.bankAssetService = bankAssetService;
        this.mapper = mapper;
        this.clientsService = clientsService;
        this.currencyService = currencyService;
    }

    @Override
    public ApiResponse<BankAssetRecordDto> findAll(Integer pageNo, Integer pageSize) {
        Page<BankAsset> bankAssets = bankAssetService.findAll(pageNo, pageSize);
        return HelperResponse.buildApiResponse(bankAssets, mapper, true, 200, true, AppConstants.LIST_MESSAGE, null);

    }

    @Override
    public ApiResponse<BankAssetRecordDto> save(BankAssetRequest bankAssetRequest) {
        Optional<Client> optionalClient = clientsService.findById(bankAssetRequest.getClientId());
        if (optionalClient.isEmpty()) {
            throw new RecordNotFoundException("Failed to find client with Id " + bankAssetRequest.getClientId());
        }

        Optional<BankAsset> optionalBankAsset = bankAssetService.findBankByAccountNumber(bankAssetRequest.getAccountNumber());
        if (optionalBankAsset.isPresent()) {
            throw new RecordExistsException("There already exists a bank with account number " + bankAssetRequest.getAccountNumber());
        }

        Optional<Currency> optionalCurrency = currencyService.findCurrencyByName(bankAssetRequest.getCurrency());
        if (optionalCurrency.isEmpty()) {
            throw new RecordNotFoundException("Failed to find currency with name " + bankAssetRequest.getCurrency());
        }


        BankAssetRecordDto recordDto = BankAssetRecordDto.builder()
            .user(optionalClient.get())
            .bankName(bankAssetRequest.getBankName())
            .balance(bankAssetRequest.getBalance())
            .currency(optionalCurrency.get())
            .accountNumber(bankAssetRequest.getAccountNumber())
            .recordStatus(RecordStatus.ACTIVE)
            .build();

        BankAsset bankAsset = bankAssetService.save(new BankAsset(recordDto));
        return HelperResponse.buildApiResponse(null, null, false, 201, true, AppConstants.SUCCESS_MESSAGE, mapper.apply(bankAsset));

    }

    @Override
    public ApiResponse<BankAssetRecordDto> findBankByAccountNumber(String accountNumber) {
        Optional<BankAsset> optional = bankAssetService.findBankByAccountNumber(accountNumber);

        return optional.map(bank -> HelperResponse.buildApiResponse(null, null, false, 200, true, AppConstants.FOUND_MESSAGE, mapper.apply(bank)))
            .orElseThrow(() -> new RecordNotFoundException("Failed to find asset bank record with account number of " + accountNumber));
    }

    @Override
    public ApiResponse<BankAssetRecordDto> findAllByUserId(Long clientId, Integer pageNo, Integer pageSize) {
        Optional<Client> client = clientsService.findById(clientId);

        if (client.isEmpty() || !client.get().getId().equals(clientId)) {
            throw new RecordNotFoundException("Failed to find client record with Id " + clientId);
        }
        Page<BankAsset> bankAssets = bankAssetService.findAllByUserId(client.get(), pageNo, pageSize);
        return HelperResponse.buildApiResponse(bankAssets, mapper, true, 200, true, AppConstants.LIST_MESSAGE, null);

    }

    @Override
    public ApiResponse<BankAssetRecordDto> findById(Long id) {
        Optional<BankAsset> optional = bankAssetService.findById(id);

        return optional.map(bank -> HelperResponse.buildApiResponse(null, null, false, 200, true, AppConstants.FOUND_MESSAGE, mapper.apply(bank)))
            .orElseThrow(() -> new RecordNotFoundException("Failed to find asset bank record with id of " + id));

    }

    @Override
    public ApiResponse<BankAssetRecordDto> update(Long id, BankAssetRecordDto bankAssetRecordDto) {
        Optional<BankAsset> bankAsset = bankAssetService.findById(id);

        if (bankAsset.isEmpty() || !Objects.equals(bankAsset.get().getId(), id)) {
            throw new RecordNotFoundException("Failed to find asset bank record with Id " + id);
        }
        BankAsset updatedRecord = bankAssetService.update(id, new BankAsset(bankAssetRecordDto));
        BankAssetRecordDto mappedDto = mapper.apply(updatedRecord);
        return HelperResponse.buildApiResponse(null, null, false, 200, true, AppConstants.SUCCESS_MESSAGE, mappedDto);

    }

    @Override
    public ApiResponse<BankAssetRecordDto> deleteById(Long id) {
        Optional<BankAsset> bankAsset = bankAssetService.findById(id);

        if (bankAsset.isEmpty() || !bankAsset.get().getId().equals(id)) {
            throw new RecordNotFoundException("Failed to find asset bank record with Id " + id);
        }
        bankAssetService.deleteById(id);
        return HelperResponse.buildApiResponse(null, null, false, 200, true, AppConstants.SUCCESS_MESSAGE, null);

    }
}
