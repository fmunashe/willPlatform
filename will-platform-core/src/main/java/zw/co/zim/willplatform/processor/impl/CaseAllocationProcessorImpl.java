package zw.co.zim.willplatform.processor.impl;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import zw.co.zim.willplatform.dto.CaseAllocationDto;
import zw.co.zim.willplatform.dto.mapper.CaseAllocationDtoMapper;
import zw.co.zim.willplatform.exceptions.RecordNotFoundException;
import zw.co.zim.willplatform.model.CaseAllocation;
import zw.co.zim.willplatform.model.Cases;
import zw.co.zim.willplatform.processor.CaseAllocationProcessor;
import zw.co.zim.willplatform.service.CaseAllocationService;
import zw.co.zim.willplatform.service.CaseService;
import zw.co.zim.willplatform.utils.AppConstants;
import zw.co.zim.willplatform.utils.enums.CaseType;
import zw.co.zim.willplatform.utils.messages.response.basic.ApiResponse;
import zw.co.zim.willplatform.utils.messages.response.helper.HelperResponse;

import java.util.Objects;
import java.util.Optional;

@Service
public class CaseAllocationProcessorImpl implements CaseAllocationProcessor {
    private final CaseAllocationService allocationService;
    private final CaseService caseService;
    private final ModelMapper modelMapper;
    private final CaseAllocationDtoMapper mapper;

    public CaseAllocationProcessorImpl(CaseAllocationService allocationService, CaseService caseService, ModelMapper modelMapper, CaseAllocationDtoMapper mapper) {
        this.allocationService = allocationService;
        this.caseService = caseService;
        this.modelMapper = modelMapper;
        this.mapper = mapper;
    }

    @Override
    public ApiResponse<CaseAllocationDto> findById(Long id) {
        Optional<CaseAllocation> optional = allocationService.findById(id);
        return optional.map(cases -> HelperResponse.buildApiResponse(null, null, false, 200, true, AppConstants.FOUND_MESSAGE, mapper.apply(cases)))
            .orElseThrow(() -> new RecordNotFoundException("Failed to find case allocation record with Id of " + id));
    }

    @Override
    public ApiResponse<CaseAllocationDto> save(CaseAllocationDto caseAllocationDto) {
        Optional<Cases> optionalCase = caseService.findById(caseAllocationDto.getCaseId());
        if (optionalCase.isEmpty()) {
            throw new RecordNotFoundException("Failed to find case with Id " + caseAllocationDto.getCaseId());
        }
        Cases cases = optionalCase.get();

        CaseAllocation allocation = CaseAllocation.builder()
            .caseId(cases)
            .allocatedAgent(cases.getAssignedAgent().getName())
            .caseType(cases.getCaseType())
            .allocationTime(cases.getCreatedAt())
            .recordStatus(cases.getStatus())
            .build();
        allocation = allocationService.save(allocation);
        return HelperResponse.buildApiResponse(null, null, false, 201, true, AppConstants.SUCCESS_MESSAGE, mapper.apply(allocation));
    }

    @Override
    public ApiResponse<CaseAllocationDto> update(Long id, CaseAllocationDto caseAllocationDto) {
        Optional<CaseAllocation> optional = allocationService.findById(id);
        if (optional.isEmpty() || !Objects.equals(optional.get().getId(), id)) {
            throw new RecordNotFoundException("Failed to find case allocation with Id " + id);
        }
        CaseAllocation allocation = modelMapper.map(caseAllocationDto, CaseAllocation.class);
        allocation = allocationService.update(id, allocation);

        return HelperResponse.buildApiResponse(null, null, false, 200, true, AppConstants.SUCCESS_MESSAGE, mapper.apply(allocation));
    }

    @Override
    public ApiResponse<CaseAllocationDto> deleteById(Long id) {
        Optional<CaseAllocation> allocationOptional = allocationService.findById(id);
        if (allocationOptional.isEmpty() || !Objects.equals(allocationOptional.get().getId(), id)) {
            throw new RecordNotFoundException("Failed to find case allocation with Id of " + id);
        }
        allocationService.deleteById(id);
        return HelperResponse.buildApiResponse(null, null, false, 200, true, AppConstants.SUCCESS_MESSAGE, null);
    }

    @Override
    public ApiResponse<CaseAllocationDto> findAll(Integer pageNo, Integer pageSize) {
        Page<CaseAllocation> pagedAllocations = allocationService.findAll(pageNo, pageSize);
        return HelperResponse.buildApiResponse(pagedAllocations, mapper, true, 200, true, AppConstants.LIST_MESSAGE, null);
    }

    @Override
    public ApiResponse<CaseAllocationDto> findAllByCaseType(String caseType, Integer pageNo, Integer pageSize) {
        Page<CaseAllocation> pagedAllocations = allocationService.findAllByCaseType(CaseType.valueOf(caseType), pageNo, pageSize);
        return HelperResponse.buildApiResponse(pagedAllocations, mapper, true, 200, true, AppConstants.LIST_MESSAGE, null);
    }

    @Override
    public ApiResponse<CaseAllocationDto> findFirstByCaseId(Long caseId) {
        Optional<Cases> optionalCase = caseService.findById(caseId);

        if (optionalCase.isEmpty() || !Objects.equals(optionalCase.get().getId(), caseId)) {
            throw new RecordNotFoundException("Failed to find case allocation with case Id of " + caseId);
        }
        Optional<CaseAllocation> allocation = allocationService.findFirstByCaseId(optionalCase.get());
        return HelperResponse.buildApiResponse(null, null, false, 200, true, AppConstants.FOUND_MESSAGE, mapper.apply(allocation.get()));
    }
}
