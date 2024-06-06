package zw.co.zim.willplatform.processor.impl;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import zw.co.zim.willplatform.dto.CaseDto;
import zw.co.zim.willplatform.dto.mapper.CaseDtoMapper;
import zw.co.zim.willplatform.exceptions.RecordNotFoundException;
import zw.co.zim.willplatform.model.CaseAllocation;
import zw.co.zim.willplatform.model.Cases;
import zw.co.zim.willplatform.model.Client;
import zw.co.zim.willplatform.model.SystemUser;
import zw.co.zim.willplatform.processor.CaseServiceProcessor;
import zw.co.zim.willplatform.service.CaseAllocationService;
import zw.co.zim.willplatform.service.CaseService;
import zw.co.zim.willplatform.service.ClientsService;
import zw.co.zim.willplatform.service.SystemUserService;
import zw.co.zim.willplatform.utils.AppConstants;
import zw.co.zim.willplatform.utils.enums.CasePriority;
import zw.co.zim.willplatform.utils.enums.CaseType;
import zw.co.zim.willplatform.utils.enums.RecordStatus;
import zw.co.zim.willplatform.utils.enums.RoleEnum;
import zw.co.zim.willplatform.utils.messages.response.basic.ApiResponse;
import zw.co.zim.willplatform.utils.messages.response.helper.HelperResponse;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class CaseServiceProcessorImpl implements CaseServiceProcessor {
    private final CaseDtoMapper mapper;
    private final CaseService service;
    private final ClientsService clientsService;
    private final SystemUserService systemUserService;
    private final CaseAllocationService allocationService;
    private final ModelMapper modelMapper;
    private final AtomicInteger counter = new AtomicInteger(0);


    public CaseServiceProcessorImpl(CaseDtoMapper mapper, CaseService service, ClientsService clientsService, SystemUserService systemUserService, CaseAllocationService allocationService, ModelMapper modelMapper) {
        this.mapper = mapper;
        this.service = service;
        this.clientsService = clientsService;
        this.systemUserService = systemUserService;
        this.allocationService = allocationService;
        this.modelMapper = modelMapper;
    }


    @Override
    public ApiResponse<CaseDto> findAll(Integer pageNo, Integer pageSize) {
        Page<Cases> pagedCases = service.findAll(pageNo, pageSize);
        return HelperResponse.buildApiResponse(pagedCases, mapper, true, 200, true, AppConstants.LIST_MESSAGE, null);
    }

    @Override
    public ApiResponse<CaseDto> findById(Long id) {
        Optional<Cases> optional = service.findById(id);
        return optional.map(cases -> HelperResponse.buildApiResponse(null, null, false, 200, true, AppConstants.FOUND_MESSAGE, mapper.apply(cases)))
            .orElseThrow(() -> new RecordNotFoundException("Failed to find case record with Id of " + id));
    }

    @Override
    public ApiResponse<CaseDto> save(CaseDto caseDto) {

        Optional<Client> optionalClient = clientsService.findById(caseDto.getClientId());
        if (optionalClient.isEmpty()) {
            throw new RecordNotFoundException("Failed to find client with Id " + caseDto.getClientId());
        }

        Client client = optionalClient.get();

        List<SystemUser> agents = getAgents();

        if (agents.isEmpty()) {
            throw new RecordNotFoundException(AppConstants.AGENTS_NOT_FOUND_MESSAGE);
        }

        int userIndex = counter.getAndIncrement() % agents.size();
        SystemUser assignedUser = agents.get(userIndex);

        Cases builtCase = buildCase(caseDto, client, assignedUser);

        builtCase = service.save(builtCase);
        CaseAllocation allocation = buildAllocation(builtCase);
        allocationService.save(allocation);
        return HelperResponse.buildApiResponse(null, null, false, 201, true, AppConstants.SUCCESS_MESSAGE, mapper.apply(builtCase));
    }


    @Override
    public ApiResponse<CaseDto> update(Long id, CaseDto caseDto) {
        Optional<Cases> optional = service.findById(id);
        if (optional.isEmpty() || !Objects.equals(optional.get().getId(), id)) {
            throw new RecordNotFoundException("Failed to find case with Id " + id);
        }
        Cases updatedCase = service.update(id, modelMapper.map(caseDto, Cases.class));

        return HelperResponse.buildApiResponse(null, null, false, 200, true, AppConstants.SUCCESS_MESSAGE, mapper.apply(updatedCase));
    }

    @Override
    public ApiResponse<CaseDto> deleteById(Long id) {
        Optional<Cases> optional = service.findById(id);
        if (optional.isEmpty()) {
            throw new RecordNotFoundException("Failed to find case with Id " + id);
        }
        service.deleteById(id);

        return HelperResponse.buildApiResponse(null, null, false, 200, true, AppConstants.SUCCESS_MESSAGE, null);
    }

    @Override
    public ApiResponse<CaseDto> findAllByCaseType(String caseType, Integer pageNo, Integer pageSize) {
        Page<Cases> pagedCases = service.findAllByCaseType(CaseType.valueOf(caseType), pageNo, pageSize);
        return HelperResponse.buildApiResponse(pagedCases, mapper, true, 200, true, AppConstants.LIST_MESSAGE, null);
    }

    @Override
    public ApiResponse<CaseDto> findAllByAssignedAgent(Long assignedAgent, Integer pageNo, Integer pageSize) {
        Optional<SystemUser> optional = systemUserService.findById(assignedAgent);

        if (optional.isEmpty()) {
            throw new RecordNotFoundException("Failed to find assigned agent with Id " + assignedAgent);
        }
        SystemUser agent = optional.get();
        Page<Cases> pagedCases = service.findAllByAssignedAgent(agent, pageNo, pageSize);

        return HelperResponse.buildApiResponse(pagedCases, mapper, true, 200, true, AppConstants.LIST_MESSAGE, null);
    }

    @Override
    public ApiResponse<CaseDto> findAllByClient(Long clientId, Integer pageNo, Integer pageSize) {
        Optional<Client> optional = clientsService.findById(clientId);

        if (optional.isEmpty()) {
            throw new RecordNotFoundException("Failed to find client with Id " + clientId);
        }
        Client client = optional.get();
        Page<Cases> pagedCases = service.findAllByClient(client, pageNo, pageSize);

        return HelperResponse.buildApiResponse(pagedCases, mapper, true, 200, true, AppConstants.LIST_MESSAGE, null);

    }

    @Override
    public ApiResponse<CaseDto> findFirstByCaseNumber(String caseNumber) {
        Optional<Cases> optional = service.findFirstByCaseNumber(caseNumber);
        return optional.map(cases -> HelperResponse.buildApiResponse(null, null, false, 200, true, AppConstants.FOUND_MESSAGE, mapper.apply(cases)))
            .orElseThrow(() -> new RecordNotFoundException("Failed to find case record with number " + caseNumber));
    }

    private List<SystemUser> getAgents() {
        Page<SystemUser> systemUsers = systemUserService.findAllByRole(RoleEnum.AGENT, Integer.parseInt(AppConstants.DEFAULT_PAGE_NUMBER), Integer.parseInt(AppConstants.DEFAULT_PAGE_SIZE));
        return new ArrayList<>(systemUsers.getContent());
    }

    private String generateCaseNumber() {
        Long lastCaseId = service.findLatestCase()
            .map(Cases::getId)
            .orElse(0L);
        Long newCaseId = lastCaseId + 1;
        return String.format("C%09d", newCaseId);
    }

    private CaseAllocation buildAllocation(Cases cases) {
        return CaseAllocation.builder()
            .caseId(cases)
            .caseType(cases.getCaseType())
            .allocationTime(LocalDateTime.now())
            .allocatedAgent(cases.getAssignedAgent().getName().concat(" ").concat(cases.getAssignedAgent().getLastName()))
            .recordStatus(RecordStatus.ACTIVE)
            .build();
    }

    private Cases buildCase(CaseDto caseDto, Client client, SystemUser assignedUser) {
        return Cases.builder()
            .caseNumber(generateCaseNumber())
            .caseType(CaseType.valueOf(caseDto.getCaseType()))
            .subject(caseDto.getSubject())
            .description(caseDto.getDescription())
            .priority(CasePriority.valueOf(caseDto.getPriority()))
            .userId(client)
            .status(caseDto.getRecordStatus())
            .assignedAgent(assignedUser)
            .build();
    }
}
