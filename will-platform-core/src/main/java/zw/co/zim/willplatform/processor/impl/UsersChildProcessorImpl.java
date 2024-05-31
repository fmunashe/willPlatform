package zw.co.zim.willplatform.processor.impl;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import zw.co.zim.willplatform.dto.UsersChildDto;
import zw.co.zim.willplatform.dto.mapper.UsersChildDtoMapper;
import zw.co.zim.willplatform.exceptions.BusinessException;
import zw.co.zim.willplatform.exceptions.RecordNotFoundException;
import zw.co.zim.willplatform.model.Client;
import zw.co.zim.willplatform.model.UsersChild;
import zw.co.zim.willplatform.processor.UsersChildProcessor;
import zw.co.zim.willplatform.service.ClientsService;
import zw.co.zim.willplatform.service.UsersChildService;
import zw.co.zim.willplatform.utils.AppConstants;
import zw.co.zim.willplatform.utils.enums.RecordStatus;
import zw.co.zim.willplatform.utils.messages.request.UsersChildRequest;
import zw.co.zim.willplatform.utils.messages.response.basic.ApiResponse;
import zw.co.zim.willplatform.utils.messages.response.helper.HelperResponse;

import java.time.LocalDate;
import java.util.Objects;
import java.util.Optional;

@Service
public class UsersChildProcessorImpl implements UsersChildProcessor {
    private final UsersChildService childService;
    private final UsersChildDtoMapper mapper;
    private final ClientsService clientsService;
    private final ModelMapper modelMapper;

    public UsersChildProcessorImpl(UsersChildService childService, UsersChildDtoMapper mapper, ClientsService clientsService, ModelMapper modelMapper) {
        this.childService = childService;
        this.mapper = mapper;
        this.clientsService = clientsService;
        this.modelMapper = modelMapper;
    }

    @Override
    public ApiResponse<UsersChildDto> findAll(Integer pageNo, Integer pageSize) {
        Page<UsersChild> usersChildPage = childService.findAll(pageNo, pageSize);
        return HelperResponse.buildApiResponse(usersChildPage, mapper, true, 200, true, AppConstants.LIST_MESSAGE, null);
    }

    @Override
    public ApiResponse<UsersChildDto> findById(Long id) {
        Optional<UsersChild> optional = childService.findById(id);

        return optional.map(child -> HelperResponse.buildApiResponse(null, null, false, 200, true, AppConstants.FOUND_MESSAGE, mapper.apply(child)))
            .orElseThrow(() -> new RecordNotFoundException("Failed to find child record with Id of " + id));

    }

    @Override
    public ApiResponse<UsersChildDto> save(UsersChildRequest usersChildRequest) {
        Optional<Client> optionalClient = clientsService.findById(usersChildRequest.getClientId());
        if (optionalClient.isEmpty()) {
            throw new RecordNotFoundException("Failed to find client with Id " + usersChildRequest.getClientId());
        }

        UsersChildDto recordDto = UsersChildDto.builder()
            .userId(optionalClient.get())
            .name(usersChildRequest.getName())
            .surname(usersChildRequest.getSurname())
            .dob(usersChildRequest.getDob())
            .trustAge(usersChildRequest.getTrustAge())
            .recordStatus(RecordStatus.ACTIVE)
            .build();

        if (recordDto.getDob().isAfter(LocalDate.now())) {
            throw new BusinessException("Date of birth cannot be in the future " + recordDto.getDob());
        }

        if (recordDto.getTrustAge() < 0) {
            throw new BusinessException("Trust age cannot be less than zero " + recordDto.getTrustAge());
        }

        UsersChild child = modelMapper.map(recordDto, UsersChild.class);
        child = childService.save(child);
        return HelperResponse.buildApiResponse(null, null, false, 201, true, AppConstants.SUCCESS_MESSAGE, mapper.apply(child));
    }

    @Override
    public ApiResponse<UsersChildDto> update(Long id, UsersChildDto usersChildDto) {
        Optional<UsersChild> child = childService.findById(id);

        if (child.isEmpty() || !Objects.equals(child.get().getId(), id)) {
            throw new RecordNotFoundException("Failed to find a child record with Id " + id);
        }
        if (usersChildDto.getDob().isAfter(LocalDate.now())) {
            throw new BusinessException("Date of birth cannot be in the future " + usersChildDto.getDob());
        }

        if (usersChildDto.getTrustAge() < 0) {
            throw new BusinessException("Trust age cannot be less than zero " + usersChildDto.getTrustAge());
        }
        UsersChild updatedRecord = childService.update(id, modelMapper.map(usersChildDto, UsersChild.class));
        UsersChildDto mappedDto = mapper.apply(updatedRecord);
        return HelperResponse.buildApiResponse(null, null, false, 200, true, AppConstants.SUCCESS_MESSAGE, mappedDto);

    }

    @Override
    public ApiResponse<UsersChildDto> deleteById(Long id) {
        Optional<UsersChild> child = childService.findById(id);

        if (child.isEmpty() || !child.get().getId().equals(id)) {
            throw new RecordNotFoundException("Failed to find a child record with Id " + id);
        }
        childService.deleteById(id);
        return HelperResponse.buildApiResponse(null, null, false, 200, true, AppConstants.SUCCESS_MESSAGE, null);

    }

    @Override
    public ApiResponse<UsersChildDto> findAllByUserId(Long clientId, Integer pageNo, Integer pageSize) {
        Optional<Client> client = clientsService.findById(clientId);

        if (client.isEmpty() || !client.get().getId().equals(clientId)) {
            throw new RecordNotFoundException("Failed to find client record with Id " + clientId);
        }
        Page<UsersChild> childPage = childService.findAllByUserId(client.get(), pageNo, pageSize);
        return HelperResponse.buildApiResponse(childPage, mapper, true, 200, true, AppConstants.LIST_MESSAGE, null);
    }
}
