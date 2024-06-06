package zw.co.zim.willplatform.processor.impl;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import zw.co.zim.willplatform.dto.WillCommentsDto;
import zw.co.zim.willplatform.dto.mapper.WillCommentsDtoMapper;
import zw.co.zim.willplatform.exceptions.RecordNotFoundException;
import zw.co.zim.willplatform.model.Client;
import zw.co.zim.willplatform.model.WillComments;
import zw.co.zim.willplatform.processor.WillCommentsProcessor;
import zw.co.zim.willplatform.service.ClientsService;
import zw.co.zim.willplatform.service.WillCommentsService;
import zw.co.zim.willplatform.utils.AppConstants;
import zw.co.zim.willplatform.utils.enums.RecordStatus;
import zw.co.zim.willplatform.utils.messages.request.WillCommentsRequest;
import zw.co.zim.willplatform.utils.messages.response.basic.ApiResponse;
import zw.co.zim.willplatform.utils.messages.response.helper.HelperResponse;

import java.util.Objects;
import java.util.Optional;

@Service
public class WillCommentsProcessorImpl implements WillCommentsProcessor {
    private final WillCommentsService commentsService;
    private final ClientsService clientsService;
    private final ModelMapper modelMapper;
    private final WillCommentsDtoMapper mapper;

    public WillCommentsProcessorImpl(WillCommentsService commentsService, ClientsService clientsService, ModelMapper modelMapper, WillCommentsDtoMapper mapper) {
        this.commentsService = commentsService;
        this.clientsService = clientsService;
        this.modelMapper = modelMapper;
        this.mapper = mapper;
    }

    @Override
    public ApiResponse<WillCommentsDto> findAll(Integer pageNo, Integer pageSize) {
        Page<WillComments> willComments = commentsService.findAll(pageNo, pageSize);
        return HelperResponse.buildApiResponse(willComments, mapper, true, 200, true, AppConstants.LIST_MESSAGE, null);
    }

    @Override
    public ApiResponse<WillCommentsDto> findById(Long id) {
        Optional<WillComments> optional = commentsService.findById(id);

        return optional.map(comment -> HelperResponse.buildApiResponse(null, null, false, 200, true, AppConstants.FOUND_MESSAGE, mapper.apply(comment)))
            .orElseThrow(() -> new RecordNotFoundException("Failed to find will comment with Id of " + id));

    }

    @Override
    public ApiResponse<WillCommentsDto> save(WillCommentsRequest willCommentsRequest) {
        Optional<Client> optionalClient = clientsService.findById(willCommentsRequest.getClientId());
        if (optionalClient.isEmpty()) {
            throw new RecordNotFoundException("Failed to find client with Id " + willCommentsRequest.getClientId());
        }

        WillCommentsDto recordDto = WillCommentsDto.builder()
            .userId(optionalClient.get())
            .comments(willCommentsRequest.getComments())
            .recordStatus(RecordStatus.ACTIVE)
            .build();

        WillComments comment = modelMapper.map(recordDto, WillComments.class);
        comment = commentsService.save(comment);
        return HelperResponse.buildApiResponse(null, null, false, 201, true, AppConstants.SUCCESS_MESSAGE, mapper.apply(comment));
    }

    @Override
    public ApiResponse<WillCommentsDto> update(Long id, WillCommentsDto willCommentsDto) {
        Optional<WillComments> comment = commentsService.findById(id);

        if (comment.isEmpty() || !Objects.equals(comment.get().getId(), id)) {
            throw new RecordNotFoundException("Failed to find a comment record with Id " + id);
        }
        WillComments updatedRecord = commentsService.update(id, modelMapper.map(willCommentsDto, WillComments.class));
        WillCommentsDto mappedDto = mapper.apply(updatedRecord);
        return HelperResponse.buildApiResponse(null, null, false, 200, true, AppConstants.SUCCESS_MESSAGE, mappedDto);

    }

    @Override
    public ApiResponse<WillCommentsDto> deleteById(Long id) {
        Optional<WillComments> comment = commentsService.findById(id);

        if (comment.isEmpty() || !comment.get().getId().equals(id)) {
            throw new RecordNotFoundException("Failed to find a comment record with Id " + id);
        }
        commentsService.deleteById(id);
        return HelperResponse.buildApiResponse(null, null, false, 200, true, AppConstants.SUCCESS_MESSAGE, null);

    }

    @Override
    public ApiResponse<WillCommentsDto> findAllByUserId(Long clientId, Integer pageNo, Integer pageSize) {
        Optional<Client> client = clientsService.findById(clientId);

        if (client.isEmpty() || !client.get().getId().equals(clientId)) {
            throw new RecordNotFoundException("Failed to find client record with Id " + clientId);
        }
        Page<WillComments> commentsPage = commentsService.findAllByUserId(client.get(), pageNo, pageSize);
        return HelperResponse.buildApiResponse(commentsPage, mapper, true, 200, true, AppConstants.LIST_MESSAGE, null);

    }
}
