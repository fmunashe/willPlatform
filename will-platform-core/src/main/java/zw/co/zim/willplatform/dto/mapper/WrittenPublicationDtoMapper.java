package zw.co.zim.willplatform.dto.mapper;

import org.springframework.stereotype.Service;
import zw.co.zim.willplatform.dto.LiabilitiesSubscribedWrittenPublicationDto;
import zw.co.zim.willplatform.model.LiabilitiesSubscribedWrittenPublication;

import java.util.function.Function;

@Service
public class WrittenPublicationDtoMapper implements Function<LiabilitiesSubscribedWrittenPublication, LiabilitiesSubscribedWrittenPublicationDto> {
    @Override
    public LiabilitiesSubscribedWrittenPublicationDto apply(LiabilitiesSubscribedWrittenPublication liabilitiesSubscribedWrittenPublication) {

        return LiabilitiesSubscribedWrittenPublicationDto.builder()
            .id(liabilitiesSubscribedWrittenPublication.getId())
            .userId(liabilitiesSubscribedWrittenPublication.getUserId())
            .nameOfPublication(liabilitiesSubscribedWrittenPublication.getNameOfPublication())
            .createdAt(liabilitiesSubscribedWrittenPublication.getCreatedAt())
            .updatedAt(liabilitiesSubscribedWrittenPublication.getUpdatedAt())
            .recordStatus(liabilitiesSubscribedWrittenPublication.getRecordStatus())
            .build();
    }
}
