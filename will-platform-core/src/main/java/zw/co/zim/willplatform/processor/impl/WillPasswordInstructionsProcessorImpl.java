package zw.co.zim.willplatform.processor.impl;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import zw.co.zim.willplatform.dto.WillPasswordInstructionsDto;
import zw.co.zim.willplatform.dto.mapper.WillPasswordInstructionsDtoMapper;
import zw.co.zim.willplatform.exceptions.RecordNotFoundException;
import zw.co.zim.willplatform.model.Client;
import zw.co.zim.willplatform.model.WillPasswordsInstructions;
import zw.co.zim.willplatform.processor.WillPasswordInstructionsProcessor;
import zw.co.zim.willplatform.service.ClientsService;
import zw.co.zim.willplatform.service.WillPasswordsService;
import zw.co.zim.willplatform.utils.AppConstants;
import zw.co.zim.willplatform.utils.enums.RecordStatus;
import zw.co.zim.willplatform.utils.messages.request.WillPasswordInstructionsRequest;
import zw.co.zim.willplatform.utils.messages.response.basic.ApiResponse;
import zw.co.zim.willplatform.utils.messages.response.helper.HelperResponse;

import java.util.Objects;
import java.util.Optional;

@Service
public class WillPasswordInstructionsProcessorImpl implements WillPasswordInstructionsProcessor {
    private final WillPasswordsService willPasswordsService;
    private final ClientsService clientsService;
    private final ModelMapper modelMapper;
    private final WillPasswordInstructionsDtoMapper mapper;

    public WillPasswordInstructionsProcessorImpl(WillPasswordsService willPasswordsService, ClientsService clientsService, ModelMapper modelMapper, WillPasswordInstructionsDtoMapper mapper) {
        this.willPasswordsService = willPasswordsService;
        this.clientsService = clientsService;
        this.modelMapper = modelMapper;
        this.mapper = mapper;
    }

    @Override
    public ApiResponse<WillPasswordInstructionsDto> findAll(Integer pageNo, Integer pageSize) {
        Page<WillPasswordsInstructions> instructions = willPasswordsService.findAll(pageNo, pageSize);
        return HelperResponse.buildApiResponse(instructions, mapper, true, 200, true, AppConstants.LIST_MESSAGE, null);

    }

    @Override
    public ApiResponse<WillPasswordInstructionsDto> findById(Long id) {
        Optional<WillPasswordsInstructions> optional = willPasswordsService.findById(id);

        return optional.map(instruction -> HelperResponse.buildApiResponse(null, null, false, 200, true, AppConstants.FOUND_MESSAGE, mapper.apply(instruction)))
            .orElseThrow(() -> new RecordNotFoundException("Failed to find will password instructions with Id of " + id));

    }

    @Override
    public ApiResponse<WillPasswordInstructionsDto> save(WillPasswordInstructionsRequest willPasswordInstructionsRequest) {
        Optional<Client> optionalClient = clientsService.findById(willPasswordInstructionsRequest.getClientId());
        if (optionalClient.isEmpty()) {
            throw new RecordNotFoundException("Failed to find client with Id " + willPasswordInstructionsRequest.getClientId());
        }

        WillPasswordInstructionsDto recordDto = WillPasswordInstructionsDto.builder()
            .userId(optionalClient.get())
            .instructions(willPasswordInstructionsRequest.getInstructions())
            .recordStatus(RecordStatus.ACTIVE)
            .build();

        WillPasswordsInstructions instructions = modelMapper.map(recordDto, WillPasswordsInstructions.class);
        instructions = willPasswordsService.save(instructions);
        return HelperResponse.buildApiResponse(null, null, false, 201, true, AppConstants.SUCCESS_MESSAGE, mapper.apply(instructions));

    }

    @Override
    public ApiResponse<WillPasswordInstructionsDto> update(Long id, WillPasswordInstructionsDto willPasswordInstructionsDto) {
        Optional<WillPasswordsInstructions> instructions = willPasswordsService.findById(id);

        if (instructions.isEmpty() || !Objects.equals(instructions.get().getId(), id)) {
            throw new RecordNotFoundException("Failed to find password instruction record with Id " + id);
        }
        WillPasswordsInstructions updatedRecord = willPasswordsService.update(id, modelMapper.map(willPasswordInstructionsDto, WillPasswordsInstructions.class));
        WillPasswordInstructionsDto mappedDto = mapper.apply(updatedRecord);
        return HelperResponse.buildApiResponse(null, null, false, 200, true, AppConstants.SUCCESS_MESSAGE, mappedDto);

    }

    @Override
    public ApiResponse<WillPasswordInstructionsDto> deleteById(Long id) {
        Optional<WillPasswordsInstructions> instructions = willPasswordsService.findById(id);

        if (instructions.isEmpty() || !instructions.get().getId().equals(id)) {
            throw new RecordNotFoundException("Failed to find password instruction record with Id " + id);
        }
        willPasswordsService.deleteById(id);
        return HelperResponse.buildApiResponse(null, null, false, 200, true, AppConstants.SUCCESS_MESSAGE, null);

    }

    @Override
    public ApiResponse<WillPasswordInstructionsDto> findAllByUserId(Long userId, Integer pageNo, Integer pageSize) {
        Optional<Client> client = clientsService.findById(userId);

        if (client.isEmpty() || !client.get().getId().equals(userId)) {
            throw new RecordNotFoundException("Failed to find client record with Id " + userId);
        }
        Page<WillPasswordsInstructions> instructions = willPasswordsService.findAllByUserId(client.get(), pageNo, pageSize);
        return HelperResponse.buildApiResponse(instructions, mapper, true, 200, true, AppConstants.LIST_MESSAGE, null);

    }
}
