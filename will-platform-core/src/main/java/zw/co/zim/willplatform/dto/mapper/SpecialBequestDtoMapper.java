package zw.co.zim.willplatform.dto.mapper;

import org.springframework.stereotype.Service;
import zw.co.zim.willplatform.dto.SpecialBequestDto;
import zw.co.zim.willplatform.model.SpecialBequest;

import java.util.function.Function;

@Service
public class SpecialBequestDtoMapper implements Function<SpecialBequest, SpecialBequestDto> {
    @Override
    public SpecialBequestDto apply(SpecialBequest specialBequest) {
        return SpecialBequestDto.builder()
            .id(specialBequest.getId())
            .userId(specialBequest.getUserId())
            .personDetails(specialBequest.getPersonDetails())
            .specialBequestInformation(specialBequest.getSpecialBequestInformation())
            .recordStatus(specialBequest.getRecordStatus())
            .createdAt(specialBequest.getCreatedAt())
            .updatedAt(specialBequest.getUpdatedAt())
            .build();
    }
}
