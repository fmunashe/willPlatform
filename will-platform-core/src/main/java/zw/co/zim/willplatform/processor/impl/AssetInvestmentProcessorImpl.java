package zw.co.zim.willplatform.processor.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import zw.co.zim.willplatform.dto.AssetInvestmentRecordDto;
import zw.co.zim.willplatform.dto.mapper.AssetInvestmentDtoMapper;
import zw.co.zim.willplatform.exceptions.RecordNotFoundException;
import zw.co.zim.willplatform.model.AssetInvestment;
import zw.co.zim.willplatform.model.Client;
import zw.co.zim.willplatform.processor.AssetInvestmentProcessor;
import zw.co.zim.willplatform.service.AssetInvestmentService;
import zw.co.zim.willplatform.service.ClientsService;
import zw.co.zim.willplatform.utils.AppConstants;
import zw.co.zim.willplatform.utils.enums.RecordStatus;
import zw.co.zim.willplatform.utils.messages.request.AssetInvestmentRequest;
import zw.co.zim.willplatform.utils.messages.response.basic.ApiResponse;
import zw.co.zim.willplatform.utils.messages.response.helper.HelperResponse;

import java.util.Optional;

@Service
@Slf4j
public class AssetInvestmentProcessorImpl implements AssetInvestmentProcessor {
    private final AssetInvestmentService service;
    private final AssetInvestmentDtoMapper mapper;
    private final ClientsService clientsService;

    public AssetInvestmentProcessorImpl(AssetInvestmentService service, AssetInvestmentDtoMapper mapper, ClientsService clientsService) {
        this.service = service;
        this.mapper = mapper;
        this.clientsService = clientsService;
    }

    @Override
    public ApiResponse<AssetInvestmentRecordDto> findAll(Integer pageNo, Integer pageSize) {
        Page<AssetInvestment> assetInvestment = service.findAll(pageNo, pageSize);
        return HelperResponse.buildApiResponse(assetInvestment, mapper, true, 200, true, AppConstants.LIST_MESSAGE, null);
    }

    @Override
    public ApiResponse<AssetInvestmentRecordDto> findAllByInvestmentType(String investmentType, Integer pageNo, Integer pageSize) {
        Page<AssetInvestment> investmentPage = service.findAllByInvestmentType(investmentType, pageNo, pageSize);
        return HelperResponse.buildApiResponse(investmentPage, mapper, true, 200, true, AppConstants.LIST_MESSAGE, null);
    }

    @Override
    public ApiResponse<AssetInvestmentRecordDto> findAllByUserId(Long clientId, Integer pageNo, Integer pageSize) {
        Optional<Client> client = clientsService.findById(clientId);

        if (client.isEmpty() || !client.get().getId().equals(clientId)) {
            throw new RecordNotFoundException("Failed to find client record with Id " + clientId);
        }
        Page<AssetInvestment> investmentPage = service.findAllByUserId(client.get(), pageNo, pageSize);
        return HelperResponse.buildApiResponse(investmentPage, mapper, true, 200, true, AppConstants.LIST_MESSAGE, null);
    }


    @Override
    public ApiResponse<AssetInvestmentRecordDto> findById(Long id) {
        Optional<AssetInvestment> investment = service.findById(id);

        return investment.map(invest -> HelperResponse.buildApiResponse(null, null, false, 200, true, AppConstants.FOUND_MESSAGE, mapper.apply(invest)))
            .orElseThrow(() -> new RecordNotFoundException("Failed to find investment record with Id of " + id));
    }


    @Override
    public ApiResponse<AssetInvestmentRecordDto> save(AssetInvestmentRequest investmentRequest) {
        Optional<Client> optionalClient = clientsService.findById(investmentRequest.getClientId());
        if (optionalClient.isEmpty()) {
            throw new RecordNotFoundException("Failed to find client with Id " + investmentRequest.getClientId());
        }
        AssetInvestmentRecordDto recordDto = AssetInvestmentRecordDto.builder()
            .investmentType(investmentRequest.getInvestmentType())
            .company(investmentRequest.getCompany())
            .value(investmentRequest.getInvestmentValue())
            .userId(optionalClient.get())
            .recordStatus(RecordStatus.ACTIVE)
            .build();

        AssetInvestment investment = service.save(new AssetInvestment(recordDto));
        return HelperResponse.buildApiResponse(null, null, false, 201, true, AppConstants.SUCCESS_MESSAGE, mapper.apply(investment));
    }

    @Override
    public ApiResponse<AssetInvestmentRecordDto> update(Long id, AssetInvestmentRecordDto recordDto) {
        Optional<AssetInvestment> investment = service.findById(id);

        if (investment.isEmpty() || !investment.get().getId().equals(id)) {
            throw new RecordNotFoundException("Failed to find investment record with Id " + id);
        }

        AssetInvestment updatedInvestment = service.update(id, new AssetInvestment(recordDto));
        AssetInvestmentRecordDto mappedDto = mapper.apply(updatedInvestment);

        return HelperResponse.buildApiResponse(null, null, false, 200, true, AppConstants.SUCCESS_MESSAGE, mappedDto);
    }

    @Override
    public ApiResponse<AssetInvestmentRecordDto> deleteById(Long id) {
        Optional<AssetInvestment> investment = service.findById(id);

        if (investment.isEmpty() || !investment.get().getId().equals(id)) {
            throw new RecordNotFoundException("Failed to find investment record with Id " + id);
        }
        service.deleteById(id);
        return HelperResponse.buildApiResponse(null, null, false, 200, true, AppConstants.SUCCESS_MESSAGE, null);
    }
}
