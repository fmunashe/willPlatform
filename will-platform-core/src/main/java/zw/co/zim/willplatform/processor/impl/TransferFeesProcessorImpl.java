package zw.co.zim.willplatform.processor.impl;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import zw.co.zim.willplatform.dto.TransferFeesDto;
import zw.co.zim.willplatform.dto.mapper.TransferFeesDtoMapper;
import zw.co.zim.willplatform.exceptions.RecordNotFoundException;
import zw.co.zim.willplatform.model.TransferFees;
import zw.co.zim.willplatform.processor.TransferFeesProcessor;
import zw.co.zim.willplatform.service.TransferFeesService;
import zw.co.zim.willplatform.utils.AppConstants;
import zw.co.zim.willplatform.utils.enums.RecordStatus;
import zw.co.zim.willplatform.utils.messages.request.TransferFeeRequest;
import zw.co.zim.willplatform.utils.messages.response.basic.ApiResponse;
import zw.co.zim.willplatform.utils.messages.response.helper.HelperResponse;

import java.util.Objects;
import java.util.Optional;

@Service
public class TransferFeesProcessorImpl implements TransferFeesProcessor {
    private final TransferFeesService transferFeesService;
    private final ModelMapper modelMapper;
    private final TransferFeesDtoMapper mapper;

    public TransferFeesProcessorImpl(TransferFeesService transferFeesService, ModelMapper modelMapper, TransferFeesDtoMapper mapper) {
        this.transferFeesService = transferFeesService;
        this.modelMapper = modelMapper;
        this.mapper = mapper;
    }

    @Override
    public ApiResponse<TransferFeesDto> findAll(Integer pageNo, Integer pageSize) {
        Page<TransferFees> demisePage = transferFeesService.findAll(pageNo, pageSize);
        return HelperResponse.buildApiResponse(demisePage, mapper, true, 200, true, AppConstants.LIST_MESSAGE, null);
    }

    @Override
    public ApiResponse<TransferFeesDto> findById(Long id) {
        Optional<TransferFees> optional = transferFeesService.findById(id);

        return optional.map(transFee -> HelperResponse.buildApiResponse(null, null, false, 200, true, AppConstants.FOUND_MESSAGE, mapper.apply(transFee)))
            .orElseThrow(() -> new RecordNotFoundException("Failed to find transfer fee record with Id of " + id));
    }

    @Override
    public ApiResponse<TransferFeesDto> save(TransferFeeRequest transferFeeRequest) {

        TransferFeesDto recordDto = TransferFeesDto.builder()
            .transferFee(transferFeeRequest.getTransferFee())
            .transferValue(transferFeeRequest.getTransferValue())
            .levy(transferFeeRequest.getLevy())
            .vat(transferFeeRequest.getVat())
            .recordStatus(RecordStatus.ACTIVE)
            .build();

        TransferFees fees = modelMapper.map(recordDto, TransferFees.class);
        fees = transferFeesService.save(fees);
        return HelperResponse.buildApiResponse(null, null, false, 201, true, AppConstants.SUCCESS_MESSAGE, mapper.apply(fees));

    }

    @Override
    public ApiResponse<TransferFeesDto> update(Long id, TransferFeesDto transferFeesDto) {
        Optional<TransferFees> fees = transferFeesService.findById(id);

        if (fees.isEmpty() || !Objects.equals(fees.get().getId(), id)) {
            throw new RecordNotFoundException("Failed to find transfer fee record with Id " + id);
        }
        TransferFees updatedRecord = transferFeesService.update(id, modelMapper.map(transferFeesDto, TransferFees.class));
        TransferFeesDto mappedDto = mapper.apply(updatedRecord);
        return HelperResponse.buildApiResponse(null, null, false, 200, true, AppConstants.SUCCESS_MESSAGE, mappedDto);
    }

    @Override
    public ApiResponse<TransferFeesDto> deleteById(Long id) {
        Optional<TransferFees> fees = transferFeesService.findById(id);

        if (fees.isEmpty() || !fees.get().getId().equals(id)) {
            throw new RecordNotFoundException("Failed to find transfer fees record with Id " + id);
        }
        transferFeesService.deleteById(id);
        return HelperResponse.buildApiResponse(null, null, false, 200, true, AppConstants.SUCCESS_MESSAGE, null);
    }
}
