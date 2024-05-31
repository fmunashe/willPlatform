package zw.co.zim.willplatform.dto.mapper;

import org.springframework.stereotype.Service;
import zw.co.zim.willplatform.dto.WillCommentsDto;
import zw.co.zim.willplatform.model.WillComments;

import java.util.function.Function;

@Service
public class WillCommentsDtoMapper implements Function<WillComments, WillCommentsDto> {
    @Override
    public WillCommentsDto apply(WillComments willComments) {
        return WillCommentsDto.builder()
            .id(willComments.getId())
            .userId(willComments.getUserId())
            .comments(willComments.getComments())
            .recordStatus(willComments.getRecordStatus())
            .createdAt(willComments.getCreatedAt())
            .updatedAt(willComments.getUpdatedAt())
            .build();
    }
}
