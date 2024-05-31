package zw.co.zim.willplatform.processor.impl;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import zw.co.zim.willplatform.dto.LiabilitiesOutstandingLoanDto;
import zw.co.zim.willplatform.dto.mapper.LiabilitiesOutStandingLoanDtoMapper;
import zw.co.zim.willplatform.exceptions.RecordNotFoundException;
import zw.co.zim.willplatform.model.Client;
import zw.co.zim.willplatform.model.LiabilitiesOutstandingLoan;
import zw.co.zim.willplatform.processor.LiabilitiesOutstandingLoanProcessor;
import zw.co.zim.willplatform.service.ClientsService;
import zw.co.zim.willplatform.service.LiabilitiesOutstandingLoanService;
import zw.co.zim.willplatform.utils.AppConstants;
import zw.co.zim.willplatform.utils.enums.RecordStatus;
import zw.co.zim.willplatform.utils.messages.request.OutstandingLoanRequest;
import zw.co.zim.willplatform.utils.messages.response.basic.ApiResponse;
import zw.co.zim.willplatform.utils.messages.response.helper.HelperResponse;

import java.util.Objects;
import java.util.Optional;

@Service
public class LiabilitiesOutstandingLoanProcessorImpl implements LiabilitiesOutstandingLoanProcessor {
    private final LiabilitiesOutstandingLoanService loanService;
    private final LiabilitiesOutStandingLoanDtoMapper mapper;
    private final ModelMapper modelMapper;
    private final ClientsService clientsService;

    public LiabilitiesOutstandingLoanProcessorImpl(LiabilitiesOutstandingLoanService loanService, LiabilitiesOutStandingLoanDtoMapper mapper, ModelMapper modelMapper, ClientsService clientsService) {
        this.loanService = loanService;
        this.mapper = mapper;
        this.modelMapper = modelMapper;
        this.clientsService = clientsService;
    }


    @Override
    public ApiResponse<LiabilitiesOutstandingLoanDto> findAll(Integer pageNo, Integer pageSize) {
        Page<LiabilitiesOutstandingLoan> loanPage = loanService.findAll(pageNo, pageSize);
        return HelperResponse.buildApiResponse(loanPage, mapper, true, 200, true, AppConstants.SUCCESS_MESSAGE, null);
    }

    @Override
    public ApiResponse<LiabilitiesOutstandingLoanDto> findById(Long id) {
        Optional<LiabilitiesOutstandingLoan> optional = loanService.findById(id);

        return optional.map(loan -> HelperResponse.buildApiResponse(null, null, false, 200, true, AppConstants.FOUND_MESSAGE, mapper.apply(loan)))
            .orElseThrow(() -> new RecordNotFoundException("Failed to find outstanding loan record with Id of " + id));
    }

    @Override
    public ApiResponse<LiabilitiesOutstandingLoanDto> save(OutstandingLoanRequest outstandingLoanRequest) {
        Optional<Client> optionalClient = clientsService.findById(outstandingLoanRequest.getClientId());
        if (optionalClient.isEmpty()) {
            throw new RecordNotFoundException("Failed to find client with Id " + outstandingLoanRequest.getClientId());
        }
        LiabilitiesOutstandingLoanDto recordDto = LiabilitiesOutstandingLoanDto.builder()
            .userId(optionalClient.get())
            .nameOfCreditor(outstandingLoanRequest.getNameOfCreditor())
            .amountOwed(outstandingLoanRequest.getAmountOwed())
            .recordStatus(RecordStatus.ACTIVE)
            .build();

        LiabilitiesOutstandingLoan loan = modelMapper.map(recordDto, LiabilitiesOutstandingLoan.class);
        loan = loanService.save(loan);
        return HelperResponse.buildApiResponse(null, null, false, 201, true, AppConstants.SUCCESS_MESSAGE, mapper.apply(loan));
    }

    @Override
    public ApiResponse<LiabilitiesOutstandingLoanDto> update(Long id, LiabilitiesOutstandingLoanDto liabilitiesOutstandingLoanDto) {

        Optional<LiabilitiesOutstandingLoan> loan = loanService.findById(id);

        if (loan.isEmpty() || !Objects.equals(loan.get().getId(), id)) {
            throw new RecordNotFoundException("Failed to find outstanding loan record with Id " + id);
        }
        LiabilitiesOutstandingLoan updatedRecord = loanService.update(id, modelMapper.map(liabilitiesOutstandingLoanDto, LiabilitiesOutstandingLoan.class));
        LiabilitiesOutstandingLoanDto mappedDto = mapper.apply(updatedRecord);
        return HelperResponse.buildApiResponse(null, null, false, 200, true, AppConstants.SUCCESS_MESSAGE, mappedDto);

    }

    @Override
    public ApiResponse<LiabilitiesOutstandingLoanDto> deleteById(Long id) {
        Optional<LiabilitiesOutstandingLoan> loan = loanService.findById(id);

        if (loan.isEmpty() || !loan.get().getId().equals(id)) {
            throw new RecordNotFoundException("Failed to find outstanding loan record with Id " + id);
        }
        loanService.deleteById(id);
        return HelperResponse.buildApiResponse(null, null, false, 200, true, AppConstants.SUCCESS_MESSAGE, null);
    }

    @Override
    public ApiResponse<LiabilitiesOutstandingLoanDto> findAllByUserId(Long clientId, Integer pageNo, Integer pageSize) {
        Optional<Client> client = clientsService.findById(clientId);

        if (client.isEmpty() || !client.get().getId().equals(clientId)) {
            throw new RecordNotFoundException("Failed to find client record with Id " + clientId);
        }
        Page<LiabilitiesOutstandingLoan> loanPage = loanService.findAllByUserId(client.get(), pageNo, pageSize);
        return HelperResponse.buildApiResponse(loanPage, mapper, true, 200, true, AppConstants.LIST_MESSAGE, null);
    }
}
