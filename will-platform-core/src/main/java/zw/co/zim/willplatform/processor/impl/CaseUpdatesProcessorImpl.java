package zw.co.zim.willplatform.processor.impl;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import zw.co.zim.willplatform.dto.CaseUpdatesDto;
import zw.co.zim.willplatform.dto.mapper.CaseUpdatesDtoMapper;
import zw.co.zim.willplatform.enums.RecordStatus;
import zw.co.zim.willplatform.messages.response.basic.ApiResponse;
import zw.co.zim.willplatform.messages.response.helper.HelperResponse;
import zw.co.zim.willplatform.model.CaseUpdate;
import zw.co.zim.willplatform.model.Cases;
import zw.co.zim.willplatform.processor.CaseUpdatesProcessor;
import zw.co.zim.willplatform.service.CaseService;
import zw.co.zim.willplatform.service.CaseUpdatesService;
import zw.co.zim.willplatform.utils.AppConstants;

import java.util.Objects;
import java.util.Optional;

@Service
public class CaseUpdatesProcessorImpl implements CaseUpdatesProcessor {
    private final ModelMapper modelMapper;
    private final CaseUpdatesDtoMapper mapper;
    private final CaseUpdatesService updatesService;
    private final CaseService caseService;

    public CaseUpdatesProcessorImpl(ModelMapper modelMapper, CaseUpdatesDtoMapper mapper, CaseUpdatesService updatesService, CaseService caseService) {
        this.modelMapper = modelMapper;
        this.mapper = mapper;
        this.updatesService = updatesService;
        this.caseService = caseService;
    }

    @Override
    public ApiResponse<CaseUpdatesDto> findAll() {
        return null;
    }

    @Override
    public ApiResponse<CaseUpdatesDto> findById(Long id) {
        Optional<CaseUpdate> optional = updatesService.findById(id);

        return optional.map(cases -> HelperResponse.buildApiResponse(null, null, false, 200, true, AppConstants.FOUND_MESSAGE, mapper.apply(cases)))
            .orElseGet(() -> HelperResponse.buildApiResponse(null, null, false, 404, false, AppConstants.NOT_FOUND_MESSAGE, null));
    }

    @Override
    public ApiResponse<CaseUpdatesDto> save(CaseUpdatesDto caseUpdatesDto) {
        Optional<Cases> optionalCase = caseService.findById(caseUpdatesDto.getCaseId());
        if (optionalCase.isEmpty()) {
            return HelperResponse.buildApiResponse(null, null, false, 404, false, AppConstants.NOT_FOUND_MESSAGE, null);
        }
        CaseUpdate caseUpdate = CaseUpdate.builder()
            .caseId(optionalCase.get())
            .name(caseUpdatesDto.getName())
            .description(caseUpdatesDto.getDescription())
            .createdBy(caseUpdatesDto.getCreatedBy())
            .status(RecordStatus.valueOf(caseUpdatesDto.getStatus()))
            .build();
        caseUpdate = updatesService.save(caseUpdate);
        return HelperResponse.buildApiResponse(null, null, false, 200, false, AppConstants.SUCCESS_MESSAGE, mapper.apply(caseUpdate));
    }

    @Override
    public ApiResponse<CaseUpdatesDto> update(Long id, CaseUpdatesDto caseUpdatesDto) {
        Optional<CaseUpdate> optional = updatesService.findById(id);
        if (optional.isEmpty() || !Objects.equals(optional.get().getId(), id)) {
            return HelperResponse.buildApiResponse(null, null, false, 404, false, AppConstants.NOT_FOUND_MESSAGE, null);
        }
        CaseUpdate updatedCase = updatesService.update(id, modelMapper.map(caseUpdatesDto, CaseUpdate.class));

        return HelperResponse.buildApiResponse(null, null, false, 200, true, AppConstants.SUCCESS_MESSAGE, mapper.apply(updatedCase));
    }

    @Override
    public ApiResponse<CaseUpdatesDto> deleteById(Long id) {
        Optional<CaseUpdate> optionalCase = updatesService.findById(id);
        if (optionalCase.isEmpty()) {
            return HelperResponse.buildApiResponse(null, null, false, 404, false, AppConstants.NOT_FOUND_MESSAGE, null);
        }
        updatesService.deleteById(id);

        return HelperResponse.buildApiResponse(null, null, false, 200, true, AppConstants.SUCCESS_MESSAGE, null);
    }

    @Override
    public ApiResponse<CaseUpdatesDto> findAll(Integer pageNo, Integer pageSize) {
        Page<CaseUpdate> pagedUpdates = updatesService.findAll(pageNo, pageSize);
        return HelperResponse.buildApiResponse(pagedUpdates, mapper, true, 200, true, AppConstants.LIST_MESSAGE, null);
    }

    @Override
    public ApiResponse<CaseUpdatesDto> findAllByCaseId(Long caseId, Integer pageNo, Integer pageSize) {
        Optional<Cases> optionalCase = caseService.findById(caseId);
        if (optionalCase.isEmpty()) {
            return HelperResponse.buildApiResponse(null, null, false, 404, false, AppConstants.NOT_FOUND_MESSAGE, null);
        }

        Page<CaseUpdate> pagedCases = updatesService.findAllByCaseId(optionalCase.get(), pageNo, pageSize);
        return HelperResponse.buildApiResponse(pagedCases, mapper, true, 200, true, AppConstants.LIST_MESSAGE, null);
    }

    @Override
    public ApiResponse<CaseUpdatesDto> findFirstById(Long caseId) {
        Optional<CaseUpdate> optional = updatesService.findById(caseId);
        return optional.map(updates -> HelperResponse.buildApiResponse(null, null, false, 200, true, AppConstants.FOUND_MESSAGE, mapper.apply(updates)))
            .orElseGet(() -> HelperResponse.buildApiResponse(null, null, false, 404, false, AppConstants.NOT_FOUND_MESSAGE, null));

    }
}
